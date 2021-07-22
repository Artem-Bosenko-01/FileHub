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
    const hash = this._window.location.hash;
    this._window.addEventListener('hashchange', () => {
      this._showPage(this._window.location.hash);
    });

    this._showPage(hash);
  }

  /**
   * Calls page creator from configuration by hash.
   * @param {string} hash
   * @private
   */
  _showPage(hash) {
    if (!hash || hash === '#') {
      const defaultHash = this._routingConfiguration.defaultRoute;
      hash = this._redirect(defaultHash);
    }
    const hashBody = hash.substring(1);
    const pageCreator = this._routingConfiguration.getPageByHash(hashBody);
    pageCreator();
  }

  /**
   * Sets necessary address to hash.
   * @param {string} hash
   * @returns {string} new route
   * @private
   */
  _redirect(hash) {
    const newHash = `#${hash}`;
    this._window.location.hash = newHash;
    return newHash;
  }
}
