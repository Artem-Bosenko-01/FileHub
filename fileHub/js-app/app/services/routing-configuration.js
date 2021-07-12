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

  set notFoundHash(hash) {
    this._error404Hash = hash;
  }

  get defaultRoutingHash() {
    return this._defaultHash;
  }


  addRoute(hash, pageInit) {
    this._routes.set(hash, pageInit);
    return this;
  }

  getPageByHash(hash) {
    if (this._routes.get(hash)) {
      return this._routes.get(hash);
    } else return this._routes.get(this._error404Hash);
  }
}
