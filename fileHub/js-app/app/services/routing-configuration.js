/**
 *
 */
export class RoutingConfiguration {
  /**
   *
   * @param {string} defaultHashRoute
   */
  constructor(defaultHashRoute) {
    this._defaultHash = defaultHashRoute;
    this._routes = new Map();
  }

  /**
   *
   * @param {string} hash
   */
  set notFoundHash(hash) {
    this._error404Hash = hash;
  }

  get defaultRoutingHash() {
    return this._defaultHash;
  }


  /**
   *
   * @param hash
   * @param pageInit
   * @returns {RoutingConfiguration}
   */
  addRoute(hash, pageInit) {
    this._routes.set(hash, pageInit);
    return this;
  }

  /**
   * Finds page from routes list by hash.
   * @param {string} hash
   * @returns {Component}
   */
  getPageByHash(hash) {
    if (this._routes.get(hash)) {
      return this._routes.get(hash);
    } else return this._routes.get(this._error404Hash);
  }
}
