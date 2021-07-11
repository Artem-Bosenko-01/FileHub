import {Component} from './components/component.js';
import {AuthenticationPage} from './login/authentication-page.js';
import {ApiService} from './services/api-service.js';

/**
 * Entry point of FileHub application.
 */
export class Application extends Component {
  /** @inheritDoc */
  initNestedComponents() {
    const apiService = new ApiService();
    const authenticationPage = new AuthenticationPage(this.rootElement);
    authenticationPage.apiService = apiService;
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
