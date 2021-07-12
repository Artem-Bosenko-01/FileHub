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
          const authenticationPage = new AuthenticationPage(this.rootElement);
          authenticationPage.apiService = apiService;
        })
        .addRoute('register', () => {
          const registrationPage = new RegistrationPage(this.rootElement);
          registrationPage.apiService = apiService;
        })
        .addRoute('404', () => new ErrorPage(this.rootElement));
    configuration.notFoundHash = '404';
    new Router(configuration);
  }

  /** @inheritDoc */
  get markup() {
    return '<header>\n' +
        '              <h1 title="TeamDev">\n' +
        '                  <a class="logo" href="https://www.teamdev.com/" target="_blank">\n' +
        '                      TeamDev\n' +
        '                  </a>\n' +
        '              </h1>\n' +
        '           </header>';
  }
}
