/**
 * Configuration for {@link Router router}, which contains list of routes and page creators for each of them.
 * // TODO Correct tense please
 */
export class RoutingConfiguration {
  /**
   * @constructor
   */
  constructor() {
    this._routes = new Map();
  }

  /**
   * Route for page, which creates when redirects to not existed routes.
   * @param {string} hash
   */
  set notFoundRoute(hash) {
    this._error404Hash = hash;
  }

  /**
   * New route's hash and page creator for it.
   * @param {string} hash
   * @param {function} pageInit
   * @returns {RoutingConfiguration}
   */
  addRoute(hash, pageInit) {
    this._routes.set(hash, pageInit);
    return this;
  }

  /**
   * Event that calls when configuration will redirect user to necessary url.
   * @param {function(hash: string)} listener
   */
  onRedirect(listener) {
    this._onRedirect = listener;
  }

  // TODO PageSupplier / Provider / Creator
  /**
   * Finds page from routes list by hash.
   * @param {string} hash
   * @returns {Function}
   */
  getPageByHash(hash) {
    if (!hash) {
      this._onRedirect('login');
    }

    // TODO Extract specific cases

    if (this._routes.has(hash)) {
      return this._routes.get(hash);
    } else {
      return this._routes.get(this._error404Hash);
    }
  }
}
