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
   * @typedef {Object} token
   *
   * Authenticates user in FileHub application.
   * @param {string} email
   * @param {string} password
   * @returns {Promise<token, ClientServerError|ServerError>}>}
   */
  async logIn(email, password) {
    const response = await this._fetch('/login', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });

    const responseBody = await response.json();

    if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    }
    return responseBody.token;
  }

  /**
   *
   * @typedef {Object} userData
   * @property {string} email
   * @property {string} password
   *
   * Registers user in FileHub application.
   * @param {string} email
   * @param {string} password
   * @returns {Promise<userData, UnprocessableEntityError|ClientServerError|ServerError>}
   */
  async register(email, password) {
    const response = await this._fetch('/register', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });

    const responseBody = await response.json();

    if (response.status === 422) {
      const errors = responseBody.map((responseError) =>
        new ValidationErrorCase(responseError.field, responseError.message));
      throw new UnprocessableEntityError(errors);
    }

    if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    }

    return responseBody;
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
        .then(async (response) => {
          if (response.status === 500) {
            throw new ServerError();
          }
          return response;
        })
        .catch((error) => {
          throw new Error(error.message);
        });
  }
}
