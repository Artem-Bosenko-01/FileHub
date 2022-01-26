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
   * Action that calls when route in url changes.
   * @param {function(route: string)} listener
   */
  onRouteChanged(listener) {
    this._window.addEventListener('hashchange', (event) => {
      const url = event.target.location.hash.substring(1);
      listener(url);
    });
  }

  /**
   * Route that means where user located now.
   * @returns {string}
   */
  get route() {
    return this._window.location.hash.substring(1);
  }

  /**
   * Sets necessary address to route.
   * @param {string} route
   */
  redirect(route) {
    this._window.location.hash = route;
  }
}
