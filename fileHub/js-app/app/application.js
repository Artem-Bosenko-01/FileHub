import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service/api-service.js';
import {Router} from './services/router.js';
import {RegistrationPage} from './register/registration-page.js';
import {ErrorPage} from './ErrorPage.js';
import {TitleService} from './services/title-service.js';
import {FileListPage} from './file-list/file-list-page.js';
import {StateManager} from './services/state-management/state-manager.js';
import {ActionFactory} from './services/state-management/action-factory.js';
import {RouteChanged} from './services/state-management/route-changed-action/route-changed.js';
import {RoutingConfiguration} from './services/routing-configuration.js';
import {mutator} from './services/state-management/mutator/mutator.js';
import {ModalsService} from './services/modals/modals-service.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const indexRoute = 'index';
    const logInRoute = 'login';
    const registerRoute = 'register';
    const errorPageRoute = '404';
    const apiService = new ApiService(window);
    const titleService = new TitleService('FileHub', document);
    const configuration = new RoutingConfiguration(logInRoute);
    const router = new Router(window);
    const factory = new ActionFactory();
    const modalsService = new ModalsService(this.rootElement);
    const stateManager = new StateManager({}, {apiService}, factory, mutator);

    configuration.onRedirect((route) => router.redirect(route));

    configuration
        .addRoute(logInRoute, () => {
          const page = new AuthenticationPage(this.rootElement, apiService, titleService);
          page.onLoggedIn(() => router.redirect(indexRoute));
          page.onRedirectToRegistrationPage(() => router.redirect(registerRoute));
        })
        .addRoute(registerRoute, () => {
          const page = new RegistrationPage(this.rootElement, apiService, titleService);
          page.onRegistered(() => router.redirect(logInRoute));
          page.onRedirectToAuthenticationPage(() => router.redirect(logInRoute));
        })
        .addRoute(indexRoute, () => {
          new FileListPage(this.rootElement, titleService, stateManager, modalsService)
              .onNavigateToFolder((folderId) => router.redirect(`${indexRoute}/${folderId}`));
        })
        .addRoute(errorPageRoute, () => new ErrorPage(this.rootElement))
        .notFoundRoute = errorPageRoute;

    router.onRouteChanged((route) => {
      stateManager.dispatch(new RouteChanged(route));
    });

    stateManager.onStateChanged('location', () => {
      this._clearContainer();
      configuration.getPageCreatorByRoute(stateManager.state.location)();
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
