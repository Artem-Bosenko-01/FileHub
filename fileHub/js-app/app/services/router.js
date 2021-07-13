/**
 * Manages user transitions through application pages.
 */
export class Router {
  /**
   * @constructor
   * @param {RoutingConfiguration} configuration
   * @param {any} window
   */
  constructor(configuration, window) {
    this._routingConfiguration = configuration;
    this._window = window;
    this.hash = this._window.location.hash;
    debugger;
    this._window.addEventListener('hashchange', () => {
      this._showPage(this._window.location.hash);
    });

    this._showPage(this.hash);
  }

  /**
     * Calls page creator from configuration by hash.
     * @param {string} hash
     * @private
     */
  _showPage(hash) {
    if (!hash || hash === '#') {
      const defaultHash = this._routingConfiguration.defaultRoute;
      this._redirect(defaultHash);
    }
    const hashBody = hash.substring(1);
    const route = this._routingConfiguration.getPageByHash(hashBody);
    route();
  }

  /**
     * Sets necessary address to hash.
     * @param {string} hash
     * @private
     */
  _redirect(hash) {
    this._window.location.hash = `#${hash}`;
  }
}
