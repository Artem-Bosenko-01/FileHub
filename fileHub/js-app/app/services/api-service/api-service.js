import {ClientServerError} from './client-server-error.js';
import {ServerError} from './server-error.js';
import {UnprocessableEntityError} from './unprocessable-entity-error.js';
import {ValidationErrorCase} from './validation-error-case.js';

/**
 * Allows you to interact with the main features of the application
 */
export class ApiService {
  /**
   * @constructor
   * @param {Window} window
   */
  constructor(window) {
    this._window = window;
  }

  /**
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}>}
   */
  async logIn(email, password) {
    const response = await this._fetch('/login', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });

    const responseBody = await response.json();

    if (response.ok) {
      return responseBody;
    } else if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    } else if (response.status === 500) {
      throw new ServerError(responseBody.message);
    }
  }

  /**
   *
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}
   */
  async register(email, password) {
    const response = await this._fetch('/register', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });

    const responseBody = await response.json();

    if (response.ok) {
      return responseBody;
    } else if (response.status === 422) {
      const errors = responseBody.map((responseError) =>
        new ValidationErrorCase(responseError.field, responseError.message));
      throw new UnprocessableEntityError(errors);
    } else if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    } else if (response.status === 500) {
      throw new ServerError(responseBody.message);
    }
  }

  /**
   *
   * @param {RequestInfo} url
   * @param {RequestInit} init
   * @returns {Promise<Response>}
   * @private
   */
  async _fetch(url, init) {
    return this._window.fetch(url, init)
        .then((response) => {
          return response;
        })
        .catch((error) => {
          throw new Error(error.message);
        });
  }
}
