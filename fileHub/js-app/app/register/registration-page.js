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
        await this._apiService.register(email, password);
        this._onRegisteredEvent();
      } catch (error) {
        this.clearErrorMessages();
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
   * The event that calls when a user successfully registered in the FileHub application.
   * @param {function()} event
   */
  onRegistered(event) {
    this._onRegisteredEvent = event;
  }

  /**
   * Remove server error messages, which was rendered after previous response.
   * @returns {void}
   */
  clearErrorMessages() {
    const errors = this._getElements('server-error');
    [...errors].forEach(
        (error) => error.remove(),
    );
  }

  /** @inheritDoc */
  get _markup() {
    return `<header>
                <h1 title="TeamDev">
                    <a class="logo" href="https://www.teamdev.com/" target="_blank">
                        TeamDev
                    </a>
                </h1>
            </header>`;
  }
}
