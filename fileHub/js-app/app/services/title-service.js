/**
 * Adds title name to application pages.
 */
export class TitleService {
  /**
   * @constructor
   * @param {string} applicationName
   */
  constructor(applicationName) {
    this._applicationName = applicationName;
  }

  /**
   * Adds page name to service.
   * @param {string} name
   */
  addTitleForPage(name) {
    this._pageName = name;
    document.title = `${this._pageName} - ${this._applicationName}`;
  }
}
