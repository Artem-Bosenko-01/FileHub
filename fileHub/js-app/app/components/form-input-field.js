import {Component} from './component.js';

/**
 * This is field field that allows to receive user data.
 */
export class FormInputField extends Component {
  /**
   * @inheritDoc
   * @constructor
   */
  constructor(parentElement) {
    super(parentElement);
    this._errorMessages = [];
  }
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
    this._errorMessages.push(message);
    this.render();
  }

  /**
   * Remove error messages from input field box.
   */
  cleanErrorMessage() {
    this._errorMessages = [];
    this.render();
  }

  /**
   * Get an actual input field value.
   * @returns {string|undefined}
   */
  get inputValue() {
    return this._valueInput;
  }

  /**
   * This is function for adding some event for listeners on change value of input.
   * @param {function} callback
   */
  onChange(callback) {
    this._onChangeAction = callback;
  }

  /** @inheritDoc */
  addEventListeners() {
    this.getElement(`input${this._id}`)
        .addEventListener('change', (evt) => {
          this._onChangeAction && this._onChangeAction(evt.target.value);
        });
  }

  /** @inheritDoc */
  get markup() {
    let errorMessages;
    if (this._errorMessages) {
      errorMessages = this._errorMessages
          .map((error) => `<p data-fh="error-message" class="error-message">${error}</p>`)
          .join('');
    }

    const getInput = this.getElement(`input${this._id}`);
    getInput ? this._valueInput = getInput.value : this._valueInput = '';

    return `<div class="get-user-data" data-fh="get-user-data">
                <label class="label-name" data-fh="label-name" for="${this._id}">${this._title}</label>
                <div class="input-value ${errorMessages ? 'invalid-input-value' : ''}">
                   <input data-fh="input${this._id}" title="Input ${this._title}" type="${this._inputType}" 
                   id="${this._id}" placeholder="${this._title}" value="${this._valueInput}">
                   ${errorMessages ? errorMessages : ''}
                </div>
            </div>`;
  }
}
