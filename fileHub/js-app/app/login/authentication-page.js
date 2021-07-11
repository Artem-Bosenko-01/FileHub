import {Component} from '../components/component.js';
import {AuthenticationForm} from './authentication-form.js';

/**
 * Creates authentication form and puts some API service for it.
 */
export class AuthenticationPage extends Component {
  /** @inheritDoc */
  initNestedComponents() {
    const form = new AuthenticationForm(this.rootElement);
    form.onSubmit(async (email, password) => {
      try {
        await this._apiService.logIn(email, password);
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
    return '<div></div>';
  }
}
