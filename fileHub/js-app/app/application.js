import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service/api-service.js';
import {Router} from './services/router.js';
import {RegistrationPage} from './register/registration-page.js';
import {ErrorPage} from './ErrorPage.js';
import {TitleService} from './services/title-service.js';
import {FileListPage} from './user-page/file-list-page.js';
import {StateManager} from './services/state-management/state-manager.js';
import {ActionFactory} from './services/state-management/action-factory.js';
import {RouteChanged} from './services/state-management/hash-changed-action/route-changed.js';
import {RoutingConfiguration} from './services/routing-configuration.js';
import {mutator} from './services/state-management/mutator/mutator.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const apiService = new ApiService(window);
    const titleService = new TitleService('FileHub', document);
    const configuration = new RoutingConfiguration('login');
    const router = new Router(window);
    const factory = new ActionFactory();
    const stateManager = new StateManager({}, {apiService}, factory, mutator);

    configuration.onRedirect((route) => router.redirect(route));

    configuration
        .addRoute('login', () => {
          const page = new AuthenticationPage(this.rootElement, apiService, titleService);
          page.onLoggedIn(() => router.redirect('index'));
          page.onRedirectToRegistrationPage(() => router.redirect('register'));
        })
        .addRoute('register', () => {
          const page = new RegistrationPage(this.rootElement, apiService, titleService);
          page.onRegistered(() => router.redirect('login'));
          page.onRedirectToAuthenticationPage(() => router.redirect('login'));
        })
        .addRoute('index', () => {
          new FileListPage(this.rootElement, titleService, stateManager)
              .onLinkClicked((folderId) => router.redirect(`index/${folderId}`));
        })
        .addRoute('404', () => new ErrorPage(this.rootElement))
        .notFoundRoute = '404';

    router.onRouteChanged((hash) => {
      stateManager.dispatch(new RouteChanged(hash));
    });

    stateManager.onStateChanged('location', ({location}) => {
      this._clearContainer();
      configuration.getPageCreatorByRoute(location)();
    });

    const locationRoute = router.route;
    stateManager.dispatch(new RouteChanged(locationRoute));
  }

  /**
   * Clear previous HTML elements from index.html
   * @private
   */
  _clearContainer() {
    this.rootElement.innerHTML = '';
  }

  /** @inheritDoc */
  get _markup() {
    return `<div></div>`;
  }
}
