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
   * This is function for adding some event for listeners on click button.
   * @param {function} callback
   */
  onClick(callback) {
    this._onClickAction = callback;
  }

  /**
   * This is overriding of basic function of {@link Component abstract component}.
   */
  addEventListeners() {
    this.getElement('button').addEventListener('click', (evt) => {
      this._onClickAction(evt);
    });
  }

  /**
   * @returns {string} - is html markup for submit box.
   */
  get markup() {
    return `<button id="button" title="Submit" data-fh="button" 
                class="button" type="submit">${this._titleButton}</button>`;
  }
}
