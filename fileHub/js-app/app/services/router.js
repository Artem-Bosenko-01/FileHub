/**
 *
 */
export class Router {
  /**
   * @constructor
   * @param {RoutingConfiguration} configuration
   */
  constructor(configuration) {
    this._routingConfiguration = configuration;
    const location = window.location;
    window.addEventListener('hashchange', () => {
      this.hash = location.hash;
      this._showPage(this.hash);
    });

    if (!this.hash) {
      const defaultHash = this._routingConfiguration.defaultRoutingHash;
      this._redirect(defaultHash);
    }
    this._showPage(this.hash);
  }

  _showPage(hash) {
    if (hash) {
      const hashBody = hash.substring(1);
      const route = this._routingConfiguration.getPageByHash(hashBody);
      route();
    }
  }

  _redirect(hash) {
    window.location.hash = `#${hash}`;
  }
}
