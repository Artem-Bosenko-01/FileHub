import {Component} from './component.js';

/**
 * Input is an Html markup component. This is allows user to enter data at specific field.
 */
export class Input extends Component {
  /**
   * This is function for adding title to input.
   * @param {string}  value
   */
  title(value) {
    this._title = value;
    this.render();
  }

  /**
   * This is function for adding id to input.
   * @param {string}  value
   */
  id(value) {
    this._id = value;
    this.render();
  }

  /**
   * This is function for adding special message, that are shows when input field will be empty.
   * @param {string}  value
   */
  placeholder(value) {
    this._placeholder = value;
    this.render();
  }

  /**
   *
   * @returns {string} - is html markup for rendering link.
   */
  get markup() {
    return `<div class="input-value">
                <input title="Input ${this._title}" type="text" id="${this._id}" placeholder="${this._placeholder}">
            </div>`;
  }
}
