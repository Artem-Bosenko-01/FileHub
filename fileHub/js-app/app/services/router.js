/**
 * Manages user transitions through application pages.
 */
export class Router {
  /**
     * @constructor
     * @param {RoutingConfiguration} configuration
     */
  constructor(configuration) {
    this._routingConfiguration = configuration;
    const location = window.location;
    this.hash = location.hash;
    window.addEventListener('hashchange', () => {
      this._showPage(location.hash);
    });

    if (!this.hash) {
      const defaultHash = this._routingConfiguration.defaultRoute;
      this._redirect(defaultHash);
    }
    this._showPage(this.hash);
  }

  /**
     * Calls page creator from configuration by hash.
     * @param {string} hash
     * @private
     */
  _showPage(hash) {
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
    window.location.hash = `#${hash}`;
  }
}
