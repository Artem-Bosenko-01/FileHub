import {Component} from '../components/component.js';
import {RegistrationForm} from './registration-form.js';

/**
 * Creates registration form and puts some API service for it.
 */
export class RegistrationPage extends Component {
  /** @inheritDoc */
  initNestedComponents() {
    const form = new RegistrationForm(this.rootElement);
    form.onSubmit(async (email, password) => {
      try {
        await this._apiService.registration(email, password);
        alert(`${email}\n${password}`);
      } catch (error) {
        form.addServerError(error.message);
      }
    });
  }

  /**
   * Adds api service to page
   * @param {ApiService} service
   */
  set apiService(service) {
    this._apiService = service;
  }

  /** @inheritDoc */
  get markup() {
    return ' <header>\n' +
        '        <h1 title="TeamDev">\n' +
        '            <a class="logo" href="https://www.teamdev.com/" target="_blank">\n' +
        '                TeamDev\n' +
        '            </a>\n' +
        '        </h1>\n' +
        '    </header>';
  }
}
