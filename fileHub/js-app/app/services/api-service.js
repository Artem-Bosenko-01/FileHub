import {ServerError} from './server-error.js';
import {RequestError} from './request-error.js';

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
    }).catch((error)=>{
      console.log(error.message);
    });
  }

  /**
   *
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}
   */
  registration(email, password) {
    return this._fetch('/register', {
      method: 'POST',
      body: JSON.stringify({email, password}),
    }).catch((error)=>{
      return;
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
    return await fetch(url, init)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new ServerError('500');
          }
        })
        .catch((error) => {
          throw new RequestError(error.message);
        });
  }
}
