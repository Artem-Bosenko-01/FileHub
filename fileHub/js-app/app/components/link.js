import {Component} from './component.js';

/**
 * Link is an Html markup component.
 */
export class Link extends Component {
  /**
   * This is function for adding specific inner message or symbol to link.
   * @param {string}  value
   */
  message(value) {
    this._message = value;
    this.render();
  }

  /**
   * This is function for adding title to link.
   * @param {string}  value
   */
  title(value) {
    this._title = value;
    this.render();
  }

  /**
   * This is function for adding reference action, when user click on text-link.
   * @param {string}  value
   */
  reference(value) {
    this._reference = value;
    this.render();
  }

  /**
   * @returns {string} - is html markup for rendering link.
   */
  get markup() {
    return `<a title="${this._title}" class="reference" href="${this._reference}">${this._message}</a>`;
  }
}
