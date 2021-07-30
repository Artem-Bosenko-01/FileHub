import {ClientServerError} from './client-server-error.js';
import {ServerError} from './server-error.js';
import {UnprocessableEntityError} from './unprocessable-entity-error.js';
import {ValidationErrorCase} from './validation-error-case.js';
import {FileListItem} from '../../user-page/services/file-list-item.js';

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

    this._checkResponseOnClientError(response, responseBody);

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

    this._checkResponseOnClientError(response, responseBody);

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
    this._checkResponseOnClientError(response, responseBody);

    return this._convertItemsFromJson(responseBody.folder);
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
    this._checkResponseOnClientError(response, responseBody);

    return this._convertItemsFromJson(responseBody.folder);
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
    this._checkResponseOnClientError(response, responseBody);
    const content = [];
    responseBody.items.forEach(
        (itemJson) => {
          content.push(this._convertItemsFromJson(itemJson));
        },
    );
    return content;
  }

  /**
   * Gets info about current user.
   * @returns {Promise<object|ClientServerError|ServerError>}
   */
  async getCurrentUser() {
    const response = await this._fetch('/user', {
      method: 'GET',
    });

    const responseBody = await response.json();
    this._checkResponseOnClientError(response, responseBody);

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

  /**
   * Checking response on 4** status.
   * @param {Response} response
   * @param {any} responseBody
   * @private
   */
  _checkResponseOnClientError(response, responseBody) {
    if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(responseBody.message);
    }
  }

  /**
   * Convert item from json object to {@link FileListItem standard format}.
   * @param {object} responseBody
   * @returns {FileListItem}
   * @private
   */
  _convertItemsFromJson(responseBody) {
    const item = new FileListItem();
    item.itemId = responseBody.id;
    item.itemName = responseBody.name;
    item.itemType = responseBody.type;
    item.itemsAmount = responseBody.itemsAmount;
    item.parentFolderId = responseBody.parentFolderId;
    item.itemMimeType = responseBody.mimeType;
    item.itemSize = responseBody.size;
    return item;
  }
}
