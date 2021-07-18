import {ClientServerError} from './client-server-error.js';
import {ServerError} from './server-error.js';
import {UnprocessableEntityError} from './unprocessable-entity-error.js';
import {ValidationErrorCase} from './validation-error-case.js';

/**
 * Allows you to interact with the main features of the application
 */
export class ApiService {
  /**
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}>}
   */
  async logIn(email, password) {
    return await this._fetch('/login', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });
  }

  /**
   *
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}
   */
  async registration(email, password) {
    return await this._fetch('/register', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });
  }

  /**
   *
   * @param {RequestInfo} url
   * @param {RequestInit} init
   * @returns {Promise<Response>}
   * @private
   */
  async _fetch(url, init) {
    return fetch(url, init)
        .then((response) => {
          if (response.ok) {
            const token = 'token';
            return {token};
          } else if (response.status === 422) {
            return new UnprocessableEntityError([new ValidationErrorCase('email', 'test-message')]);
          } else if ((response.status >= 400 && response.status <= 421) ||
              (response.status >= 423 && response.status < 500)) {
            return new ClientServerError(response.status);
          } else if (response.status === 500) {
            return new ServerError(response.status);
          }
        })
        .catch((error) => {
          throw new Error(error.message);
        });
  }
}
