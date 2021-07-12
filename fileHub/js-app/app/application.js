import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service.js';
import {Router} from './services/router.js';
import {RoutingConfiguration} from './services/routing-configuration.js';
import {RegistrationPage} from './register/registration-page.js';
import {ErrorPage} from './ErrorPage.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  initNestedComponents() {
    const apiService = new ApiService();
    const configuration = new RoutingConfiguration('login')
        .addRoute('login', () => {
          this.rootElement.innerHTML = '';
          const authenticationPage = new AuthenticationPage(this.rootElement);
          authenticationPage.apiService = apiService;
        })
        .addRoute('register', () => {
          this.rootElement.innerHTML = '';
          const registrationPage = new RegistrationPage(this.rootElement);
          registrationPage.apiService = apiService;
        })
        .addRoute('404', () => {
          this.rootElement.innerHTML = '';
          new ErrorPage(this.rootElement);
        });
    configuration.notFoundRoute = '404';
    new Router(configuration);
  }

  /** @inheritDoc */
  get markup() {
    return `<div></div>`;
  }
}
