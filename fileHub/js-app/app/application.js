import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service/api-service.js';
import {Router} from './services/router.js';
import {RoutingConfiguration} from './services/routing-configuration.js';
import {RegistrationPage} from './register/registration-page.js';
import {ErrorPage} from './ErrorPage.js';
import {TitleService} from './services/title-service.js';
import {FileListPage} from './user-page/file-list-page.js';
import {StateManager} from './services/state-management/state-manager.js';
import {ActionFactory} from './services/state-management/action-factory.js';
import {HashChanged} from './services/state-management/hash-changed-action/hash-changed.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const apiService = new ApiService(window);
    const titleService = new TitleService('FileHub', document);
    const configuration = new RoutingConfiguration('login');
    const router = new Router(configuration, window);

    const factory = new ActionFactory();
    const stateManager = new StateManager({}, {apiService, router}, factory);
    router.onHashChanged((urlEvent) => {
      const url = urlEvent.newURL.split('#');
      stateManager.dispatch(new HashChanged(url[1]));
    });

    stateManager.onStateChanged('location', ({location}) => {
      this._clearContainer();

      switch (location.pageRoute) {
        case '': {
          router.redirect('login');
          break;
        }
        case 'login':
          return new AuthenticationPage(this.rootElement, apiService, titleService);

        case 'register':
          return new RegistrationPage(this.rootElement, apiService, titleService);

        case 'index':
          return new FileListPage(this.rootElement, titleService, stateManager);

        default:
          return new ErrorPage(this.rootElement);
      }
    });
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
