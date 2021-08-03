/**
 * Manages user transitions through application pages.
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
   * @param {function(hash: string)} listener
   */
  onHashChanged(listener) {
    this._window.addEventListener('hashchange', (event) => {
      const url = event.target.location.hash.substring(1);
      listener(url);
    });
  }

  /**
   * Hash that means where user located now.
   * @returns {string}
   */
  get route() {
    return this._window.location.hash;
  }

  /**
   * Sets necessary address to route.
   * @param {string} route
   * @returns {string} new route
   */
  redirect(route) {
    const newRoute = `#${route}`;
    this._window.location.hash = newRoute;
    return newRoute;
  }
}
