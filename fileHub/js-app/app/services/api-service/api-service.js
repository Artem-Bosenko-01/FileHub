import {ClientServerError} from './client-server-error.js';
import {ServerError} from './server-error.js';
import {UnprocessableEntityError} from './unprocessable-entity-error.js';
import {ValidationErrorCase} from './validation-error-case.js';
import {FileListItem} from '../../file-list-item.js';
import {UnauthorizedError} from './unauthorized-error.js';
import {UserModel} from '../../user-model.js';

/**
 * Allows you to interact with the main features of the application. Sends requests to backend.
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
   *
   * @param {function()} callback
   */
  onNavigateAfterError(callback) {
    this._window.addEventListener('unauthorizedUserError', callback);
  }

  /**
   * Authenticates user in FileHub application.
   * @param {string} email
   * @param {string} password
   * @returns {Promise<void, UnauthorizedError|ClientServerError|ServerError>}>}
   */
  async logIn(email, password) {
    const response = await this._fetch('/login', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });
    if (response.status === 401) {
      throw new UnauthorizedError();
    }
    this._checkResponseOnClientOrServerError(response);
    const responseBody = await response.json();
    this._window.localStorage.setItem('token', responseBody.token);
  }

  /**
   * Registers user in FileHub application.
   * @param {string} email
   * @param {string} password
   * @returns {Promise<void, UnprocessableEntityError|ClientServerError|ServerError>}
   */
  async register(email, password) {
    const response = await this._fetch('/register', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    });

    if (response.status === 422) {
      const errorMessages = await response.json();
      const errors = errorMessages.errors.map((responseError) =>
        new ValidationErrorCase(responseError.field, responseError.message));
      throw new UnprocessableEntityError(errors);
    }
    this._checkResponseOnClientOrServerError(response);
  }

  /**
   * Gets folder by folderId.
   * @param {string} folderId
   * @returns {Promise<FileListItem, ClientServerError|ServerError>}
   */
  async getFolder(folderId) {
    const response = await this._fetch(`/folder/${folderId}`, {
      method: 'GET',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
    const responseBody = await response.json();

    return new FileListItem(responseBody.folder);
  }

  /**
   * Gets root folder.
   * @returns {Promise<FileListItem, ClientServerError|ServerError>}
   */
  async getRootFolder() {
    const response = await this._fetch(`/root-folder`, {
      method: 'GET',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
    const responseBody = await response.json();

    return new FileListItem(responseBody.folder);
  }

  /**
   * Gets folder content by folder id.
   * @param {string} folderId
   * @returns {Promise<FileListItem[], ClientServerError|ServerError>}
   */
  async getFolderContent(folderId) {
    const response = await this._fetch(`/folder/${folderId}/content`, {
      method: 'GET',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
    const responseBody = await response.json();

    const deserializedItems = responseBody.items.map((item) => new FileListItem(item));
    return deserializedItems;
  }

  /**
   * Gets info about current user.
   * @returns {Promise<UserModel, ClientServerError|ServerError>}
   */
  async getCurrentUser() {
    const response = await this._fetch('/user', {
      method: 'GET',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
    const body = await response.json();
    return new UserModel(body);
  }

  /**
   * Deletes folder from database.
   * @param {string} id
   * @returns {Promise<void, ClientServerError|ServerError>}
   */
  async deleteFolder(id) {
    const response = await this._fetch(`/folder/${id}`, {
      method: 'DELETE',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
  }

  /**
   * Deletes file from database.
   * @param {string} id
   * @returns {Promise<void, ClientServerError|ServerError>}
   */
  async deleteFile(id) {
    const response = await this._fetch(`/file/${id}`, {
      method: 'DELETE',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
  }

  /**
   * Uploads file to the application.
   * @param {File} file
   * @param {string} parentFolderId
   * @returns {Promise<void, ClientServerError|ServerError>}
   */
  async uploadFile(file, parentFolderId) {
    const uploadedFile = new FormData();
    uploadedFile.append('file', file);
    const response = await this._fetch(`/folder/${parentFolderId}/file`, {
      method: 'POST',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
      body: uploadedFile,
    });

    this._checkResponseOnClientOrServerError(response);
  }

  /**
   * Downloads file to the application.
   * @param {string} fileId
   * @returns {Promise<Blob, ClientServerError|ServerError>}
   */
  async downloadFile(fileId) {
    const response = await this._fetch(`/file/${fileId}`, {
      method: 'GET',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
    });

    this._checkResponseOnClientOrServerError(response);
    return await response.json();
  }

  /**
   * Creates new folder.
   * @param {string} folderName
   * @param {string} parentFolderId
   * @returns {Promise<object, ClientServerError|ServerError>}
   */
  async createFolder(folderName, parentFolderId) {
    const response = await this._fetch(`/folder/${parentFolderId}/folder`, {
      method: 'POST',
      headers: new Headers({'Authorization': `Bearer ${this._getToken()}`}),
      body: JSON.stringify({
        name: folderName,
        itemsAmount: 0,
        type: 'folder',
      }),
    });

    this._checkResponseOnClientOrServerError(response);
    return await response.json();
  }

  /**
   * Stopped user active session.
   * @returns {Promise<void, ClientServerError|ServerError>}
   */
  async logOut() {
    const token = this._getToken();
    this._removeToken();
    const response = await this._fetch(`/logOut`, {
      method: 'POST',
      headers: new Headers({'Authorization': `Bearer ${token}`}),
    });

    this._checkResponseOnClientOrServerError(response);
  }

  /**
   * @param {RequestInfo} url
   * @param {RequestInit} init
   * @returns {Promise<Response>}
   * @throws Error
   * @private
   */
  async _fetch(url, init) {
    try {
      return await this._window.fetch(url, init);
    } catch (error) {
      throw new Error(error.message);
    }
  }

  /**
   * Handling response on 4** or 500 code status.
   * @param {Response} response
   * @throws {ServerError}
   * @throws {ClientServerError}
   * @private
   */
  _checkResponseOnClientOrServerError(response) {
    if (response.status === 401) {
      this._window.dispatchEvent(new CustomEvent('unauthorizedUserError'));
    }

    if (response.status === 500) {
      throw new ServerError();
    }

    if ((response.status >= 400 && response.status < 500)) {
      throw new ClientServerError(response.status);
    }
  }

  _getToken() {
    return this._window.localStorage.getItem('token');
  }

  _removeToken() {
    this._window.localStorage.removeItem('token');
  }
}
