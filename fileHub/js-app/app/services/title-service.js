/**
 * Adds title name to application pages.
 */
export class TitleService {
  /**
   * @constructor
   * @param {string} applicationName
   * @param {Document} document
   */
  constructor(applicationName, document) {
    this._applicationName = applicationName;
    this._document = document;
  }

  /**
   * Adds page name to service.
   * @param {string} name
   */
  addTitleForPage(name) {
    this._pageName = name;
    this._document.title = `${this._pageName} - ${this._applicationName}`;
  }
}
