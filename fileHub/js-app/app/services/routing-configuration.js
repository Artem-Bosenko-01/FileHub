/**
 * Configuration for {@link Router router}, which saved list of routes and page creator for each.
 */
export class RoutingConfiguration {
  /**
   * @constructor
   * @param {string} defaultRoute
   */
  constructor(defaultRoute) {
    this._routes = new Map();
    this._defaultRoute = defaultRoute;
  }

  /**
   * Route for page, which creates when redirects to not existed routes.
   * @param {string} route
   */
  set notFoundRoute(route) {
    this._error404Hash = route;
  }

  /**
   * New route and page creator for it.
   * @param {string} route
   * @param {function} pageInit
   * @returns {RoutingConfiguration}
   */
  addRoute(route, pageInit) {
    this._routes.set(route, pageInit);
    return this;
  }

  /**
   * Event that calls when configuration will redirect user to necessary url.
   * @param {function(route: string)} listener
   */
  onRedirect(listener) {
    this._onRedirect = listener;
  }

  /**
   * Finds page from routes list by route.
   * @param {string} route
   * @returns {Function}
   */
  getPageCreatorByRoute(route) {
    if (!route) {
      this._onRedirect(this._defaultRoute);
    }

    if (this._routes.has(route)) {
      return this._routes.get(route);
    } else {
      return this._routes.get(this._error404Hash);
    }
  }
}
