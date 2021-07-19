import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service/api-service.js';
import {Router} from './services/router.js';
import {RoutingConfiguration} from './services/routing-configuration.js';
import {RegistrationPage} from './register/registration-page.js';
import {ErrorPage} from './ErrorPage.js';
import {TitleService} from './services/title-service.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const apiService = new ApiService();
    const titleService = new TitleService('FileHub', document);
    const configuration = new RoutingConfiguration('login')
        .addRoute('login', () => {
          this._clearContainer();
          new AuthenticationPage(this.rootElement, apiService, titleService);
        })
        .addRoute('register', () => {
          this._clearContainer();
          new RegistrationPage(this.rootElement, apiService, titleService);
        })
        .addRoute('404', () => {
          this._clearContainer();
          new ErrorPage(this.rootElement);
        });
    configuration.notFoundRoute = '404';
    new Router(configuration, window);
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
