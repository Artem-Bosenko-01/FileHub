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

    this._checkResponseOnClientOrServerError(response, responseBody);

    return responseBody.token;
  }

  /**
   *
   * @typedef {Object} UserData
   * @property {string} email
   * @property {string} password
   *
   * Registers user in FileHub application.
   * @param {string} email
   * @param {string} password
   * @returns {Promise<UserData, UnprocessableEntityError|ClientServerError|ServerError>}
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

    this._checkResponseOnClientOrServerError(response, responseBody);

    return responseBody;
  }

  /**
   * Gets item dto with folder type.
   * @param {string} folderId
   * @returns {Promise<FileListItem|ClientServerError|ServerError>}
   */
  async getFolder(folderId) {
    const response = await this._fetch(`/folder/:${folderId}`, {
      method: 'GET',
      body: JSON.stringify(folderId),
    });

    const responseBody = await response.json();
    this._checkResponseOnClientOrServerError(response, responseBody);

    return responseBody.folder;
  }

  /**
   * Gets item dto with folder type.
   * @returns {Promise<FileListItem|ClientServerError|ServerError>}
   */
  async getRootFolder() {
    const response = await this._fetch(`/root-folder`, {
      method: 'GET',
    });

    const responseBody = await response.json();
    this._checkResponseOnClientOrServerError(response, responseBody);

    return responseBody.folder;
  }

  /**
   * Gets folder content by folder id.
   * @param {string} folderId
   * @returns {Promise<FileListItem[]|ClientServerError|ServerError>}
   */
  async getFolderContent(folderId) {
    const response = await this._fetch(`/folder/:${folderId}/content`, {
      method: 'GET',
      body: JSON.stringify(folderId),
    });

    const responseBody = await response.json();
    this._checkResponseOnClientOrServerError(response, responseBody);

    return responseBody.items;
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
          return response;
        })
        .catch((error) => {
          throw new Error(error.message);
        });
  }

  /**
   * Checking response on 4** status.
   * @param {Response} response
   * @param {any} responseBody
   * @private
   */
  _checkResponseOnClientOrServerError(response, responseBody) {
    if (response.status === 500) {
      throw new ServerError();
    }

    if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    }
  }
}
