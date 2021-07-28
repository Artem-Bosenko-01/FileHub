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

    this._showPage(hash);
  }

  /**
   *
   * @param {function} listener
   */
  onHashChanged(listener) {
    this._listener = listener;
    this._window.addEventListener('hashchange', (url) => listener(url));
  }

  /**
   * Calls page creator from configuration by hash.
   * @param {string} hash
   * @private
   */
  _showPage(hash) {
    this._listener && this._window.dispatchEvent(new Event('hashchange', this._listener(hash)));
    /* if (!hash || hash === '#') {
      const defaultHash = this._routingConfiguration.defaultRoute;
      hash = this.redirect(defaultHash);
    }
    const hashBody = hash.substring(1);
    const pageCreator = this._routingConfiguration.getPageByHash(hashBody);
    pageCreator();*/
  }

  /**
   * Sets necessary address to hash.
   * @param {string} hash
   * @returns {string} new route
   */
  redirect(hash) {
    const newHash = `#${hash}`;
    this._window.location.hash = newHash;
    return newHash;
  }
}
