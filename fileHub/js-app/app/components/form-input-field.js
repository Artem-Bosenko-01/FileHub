import {Component} from './component.js';

/**
 * Field that allows to receive user data.
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
   * Id to input field.
   * @param {string}  value
   */
  set id(value) {
    this._id = value;
    this._render();
  }

  /**
   * Title and name to label.
   * @param {string}  value
   */
  set title(value) {
    this._title = value;
    this._render();
  }

  /**
   * Value to input.
   * @param {string} value
   */
  set value(value) {
    this._valueInput = value;
    this._render();
  }

  /**
   * Special type of input string to input field.
   * @param {string}  value
   */
  set inputType(value) {
    this._inputType = value;
    this._render();
  }

  /**
   * List of error messages under input field.
   * @param {string[]} messages
   */
  set errorMessages(messages) {
    this._errorMessages = messages;
    this._render();
  }

  /**
   * Error message under input field.
   * @param {string} message
   */
  addErrorMessage(message) {
    this._errorMessages.push(message);
    this._render();
  }

  /**
   * Remove error messages from input field box.
   */
  cleanErrorMessage() {
    this._errorMessages = [];
    this._render();
  }

  /**
   * Actual input field value.
   * @returns {string|undefined}
   */
  get inputValue() {
    return this._valueInput;
  }

  /**
   * Adds some event for listeners on change value of input.
   * @param {function} callback
   */
  onChange(callback) {
    this._onChangeAction = callback;
  }

  /** @inheritDoc */
  _addEventListeners() {
    this._getElement(`input${this._id}`)
        .addEventListener('change', (evt) => {
          this._onChangeAction && this._onChangeAction(evt.target.value);
          this._valueInput = evt.target.value;
          this._getElement(`input${this._id}`).value = evt.target.value;
        });
  }

  /** @inheritDoc */
  get _markup() {
    let messages;
    this._errorMessages ? messages = this._errorMessages : messages = [];
    const errorMessages = messages
        .map((error) => error && `<p data-fh="error-message" class="error-message">${error}</p>`)
        .join('');

    return `<div class="get-user-data" data-fh="get-user-data">
                <label class="label-name" data-fh="label-name" for="${this._id}">${this._title}</label>
                <div class="input-value ${errorMessages ? 'invalid-input-value' : ''}">
                   <input data-fh="input${this._id}" title="${this._title}" type="${this._inputType}" 
                   id="${this._id}" placeholder="${this._title}" value="${this._valueInput ? this._valueInput : ''}">
                   ${errorMessages ? errorMessages : ''}
                </div>
            </div>`;
  }
}
