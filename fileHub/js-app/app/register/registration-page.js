import {Component} from '../components/component.js';
import {UnprocessableEntityError} from '../services/api-service/unprocessable-entity-error.js';
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
    this._titleService = titleService;
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
        this.clearPreviousServerErrors();
        if (error instanceof UnprocessableEntityError) {
          error.errors.forEach(
              (error) => {
                form.addServerError(`field: ${error.field}\nmessage: ${error.message}`);
              },
          );
        } else {
          form.addServerError(error.message);
        }
      }
    });
  }

  /**
   * Remove server error messages, which was rendered after previous response.
   * @returns {void}
   */
  clearPreviousServerErrors() {
    const errors = this._getElements('server-error');
    if (errors) {
      [...errors].forEach(
          (error) => error.remove(),
      );
    }
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
