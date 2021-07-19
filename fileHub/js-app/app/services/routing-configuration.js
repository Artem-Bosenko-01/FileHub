/**
 * Configuration for {@link Router router}, which saved list of routes and page creator for each.
 */
export class RoutingConfiguration {
  /**
   * @constructor
   * @param {string} defaultRoute
   */
  constructor(defaultRoute) {
    this._defaultHash = defaultRoute;
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
   * Default Route hash.
   * @returns {string}
   */
  get defaultRoute() {
    return this._defaultHash;
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
   * Finds page from routes list by hash.
   * @param {string} hash
   * @returns {Function}
   */
  getPageByHash(hash) {
    if (this._routes.has(hash)) {
      return this._routes.get(hash);
    } else return this._routes.get(this._error404Hash);
  }
}
