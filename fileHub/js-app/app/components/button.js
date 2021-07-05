import {Component} from './component.js';

/**
 * Button is an Html markup component.
 */
export class Button extends Component {
  /**
   * This is function for adding inner text or symbol to button.
   * @param {string}  value
   */
  text(value) {
    this._innerText = value;
    this.render();
  }

  /**
   * This is function for adding title to button.
   * @param {string}  value
   */
  title(value) {
    this._title = value;
    this.render();
  }

  /**
   * @returns {string} - is html markup for rendering button.
   */
  get markup() {
    return `<button id="button" title="${this._title}" class="button" name="signIn" type="button">
                ${this._innerText}</button>`;
  }
}
