/**
 * Manages user's transitions through the application pages.
 */
export class Router {
  /**
   * @constructor
   * @param {any} window
   */
  constructor(window) {
    this._window = window;
  }

  /**
   * Action that calls when hash in url changes.
   * @param {function(event: HashChangeEvent)} listener
   */
  onHashChanged(listener) {
    this._window.addEventListener('hashchange', (event) => listener(event));
  }

  /**
   * Hash that means where user located now.
   * @returns {string}
   */
  get hash() {
    return this._window.location.hash;
  }

  /**
   * Sets necessary address to hash.
   * @param {string} hash
   * @returns {string} new route
   */
  redirect(hash) {
    // TODO Drop out returns
    const newHash = `#${hash}`;
    this._window.location.hash = newHash;
    return newHash;
  }
}
