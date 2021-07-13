/**
 * Allows you to interact with the main features of the application
 */
export class ApiService {
  /**
   * @param {string} email
   * @param {string} password
   * @returns {Promise<Response>}>}
   */
  logIn(email, password) {
    return this._fetch('/login', {
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
  registration(email, password) {
    return this._fetch('/register', {
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
  _fetch(url, init) {
    return window.fetch(url, init)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('500');
          }
        })
        .catch(() => {
          throw new Error('404');
        });
  }
}
