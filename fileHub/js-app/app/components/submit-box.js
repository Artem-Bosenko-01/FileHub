import {Component} from './component.js';

/**
 * This is area, where shows instruments for some form actions: submit input fields or click link.
 */
export class SubmitBox extends Component {
  /**
     * This is function for adding inner text or symbol to button.
     * @param {string}  value
     */
  set buttonTitle(value) {
    this._titleButton = value;
    this.render();
  }

  /**
     * This is function for adding reference action, when user click on text-link.
     * @param {string}  value
     */
  set linkReference(value) {
    this._link = value;
    this.render();
  }

  /**
     * This is function for adding specific inner message or symbol to link.
     * @param {string}  value
     */
  set linkMessage(value) {
    this._message = value;
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
    this.getElement('.button').addEventListener('click', (evt) => {
      this._onClickAction(evt);
    });
  }

  /**
     * @returns {string} - is html markup for rendering link.
     */
  get markup() {
    return `<div class="submit-box">
                <button id="button" title="Submit" class="button" type="submit">${this._titleButton}</button>
                <a title="Registration" class="reference" href="${this._link}">${this._message}</a>
            </div>`;
  }
}
