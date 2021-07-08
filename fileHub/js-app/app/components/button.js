import {Component} from './component.js';

/**
 * This is area, where shows instruments for some form actions: submit input fields or click link.
 */
export class Button extends Component {
  /**
   * This is function for adding inner text or symbol to button.
   * @param {string}  value
   */
  set buttonTitle(value) {
    this._titleButton = value;
    this.render();
  }

  /**
   * @returns {string} - is html markup for submit box.
   */
  get markup() {
    return `<button id="button" title="Submit" data-fh="button" 
                class="button">${this._titleButton}</button>`;
  }
}
