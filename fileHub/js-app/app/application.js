import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service.js';
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
  initNestedComponents() {
    const apiService = new ApiService();
    const titleService = new TitleService('FileHub');
    const configuration = new RoutingConfiguration('login')
        .addRoute('login', () => {
          this.rootElement.innerHTML = '';
          new AuthenticationPage(this.rootElement, apiService, titleService);
        })
        .addRoute('register', () => {
          this.rootElement.innerHTML = '';
          new RegistrationPage(this.rootElement, apiService, titleService);
        })
        .addRoute('404', () => {
          this.rootElement.innerHTML = '';
          new ErrorPage(this.rootElement);
        });
    configuration.notFoundRoute = '404';
    new Router(configuration, window);
  }

  /** @inheritDoc */
  get markup() {
    return `<div></div>`;
  }
}
