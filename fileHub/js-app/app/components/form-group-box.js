import {Component} from './component.js';

/**
 * This is row that define special area to get some necessary user data.
 */
export class FormGroupBox extends Component {
  /**
   * This is function for adding id to input field.
   * @param {string}  value
   */
  set id(value) {
    this._id = value;
    this.render();
  }

  /**
   * This is function for adding title and name to label.
   * @param {string}  value
   */
  set title(value) {
    this._title = value;
    this.render();
  }

  /**
   * This is function for adding special type of input string to input field.
   * @param {string}  value
   */
  set inputType(value) {
    this._inputType = value;
    this.render();
  }

  /**
   * This is is function for adding error message under input field.
   * @param {string} message
   */
  set errorMessage(message) {
    this._errorMessage = message;
    this.render();
  }

  /**
   * This is function for adding some event for listeners on change value of input.
   * @param {function} callback
   */
  onChange(callback) {
    this._onChangeAction = callback;
  }

  /**
   * This is overriding of basic function of {@link Component abstract component}.
   */
  addEventListeners() {
    this.getElement('input')
        .addEventListener('change', (evt) => {
          this._onChangeAction(evt.target.value);
        });
  }

  /**
   * @returns {string} - is html markup for form group component.
   */
  get markup() {
    const error = `<p data-fh="error-massage" class="error-massage">${this._errorMessage}</p>`;
    this._valueInput ? this._valueInput = this.getElement('input').value : this._valueInput = ' ';

    return `<div class="get-user-data" data-fh="get-user-data">
                <label class="label-name" data-fh="label-name" for="${this._id}">${this._title}</label>
                <div class="input-value ${this._errorMessage ? 'invalid-input-value' : ''}">
                   <input data-fh="input" title="Input ${this._title}" type="${this._inputType}" id="${this._id}" 
                   placeholder="${this._title}" value="${this._valueInput}">
                   ${this._errorMessage ? error : ''}
                </div>
            </div>`;
  }
}
