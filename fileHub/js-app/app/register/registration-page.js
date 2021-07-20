import {Component} from '../components/component.js';
import {RegistrationForm} from './registration-form.js';

/**
 * Creates registration form and puts some API service for it.
 */
export class RegistrationPage extends Component {
  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {ApiService} apiService
   * @param {TitleService}titleService
   */
  _init(apiService, titleService) {
    this._apiService = apiService;
    this._titleService =titleService;
    this._titleService.addTitleForPage('Registration');
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const form = new RegistrationForm(this.rootElement);
    form.onSubmit(async (credentials) => {
      const {email, password} = credentials;
      try {
        const response = await this._apiService.register(email, password);
        alert(`${response.email}\n${response.password}`);
      } catch (error) {
        if (error.errors) {
          const exception = error.errors[0];
          form.addServerError(`field: ${exception.field}\nmessage: ${exception.message}`);
        } else {
          form.addServerError(error.message);
        }
      }
    });
  }

  /** @inheritDoc */
  get _markup() {
    return ' <header>\n' +
        '        <h1 title="TeamDev">\n' +
        '            <a class="logo" href="https://www.teamdev.com/" target="_blank">\n' +
        '                TeamDev\n' +
        '            </a>\n' +
        '        </h1>\n' +
        '    </header>';
  }
}
