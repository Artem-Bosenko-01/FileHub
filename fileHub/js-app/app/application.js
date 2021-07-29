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
import {HashChanged} from './services/state-management/hash-changed-action/hash-changed.js';
import {RoutingConfiguration} from './services/routing-configuration.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const apiService = new ApiService(window);
    const titleService = new TitleService('FileHub', document);
    const configuration = new RoutingConfiguration();
    const router = new Router(window);
    const factory = new ActionFactory();
    const stateManager = new StateManager({}, {apiService}, factory);

    configuration.onRedirect((hash) => router.redirect(hash));

    configuration
        .addRoute('login', () => {
          const page = new AuthenticationPage(this.rootElement, apiService, titleService);
          page.onLoggedIn(() => router.redirect('index'));
        })
        .addRoute('register', () => {
          const page = new RegistrationPage(this.rootElement, apiService, titleService);
          page.onRegistered(() => router.redirect('login'));
        })
        .addRoute('index', () => {
          const page = new FileListPage(this.rootElement, titleService, stateManager);
          page.onRedirect((hash) => router.redirect(hash));
        })
        .addRoute('404', () => new ErrorPage(this.rootElement))
        .notFoundRoute = '404';

    router.onHashChanged((urlEvent) => {
      const url = urlEvent.newURL.split('#');
      stateManager.dispatch(new HashChanged(url[1]));
    });

    stateManager.onStateChanged('location', ({location}) => {
      this._clearContainer();
      const pageCreator = configuration.getPageByHash(location);
      pageCreator();
    });

    const locationHash = router.hash.substring(1);
    stateManager.dispatch(new HashChanged(locationHash));
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
