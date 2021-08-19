import {Component} from './component.js';

/**
 * Line for getting data.
 */
export class InputLine extends Component {
  /**
   * Listener on submit renaming item.
   * @param {function(newName: string)} listener
   */
  onSubmit(listener) {
    this._onSubmitListener = listener;
    this._render();
  }

  /**
   * Loading status.
   * @param {boolean} value
   */
  set isLoading(value) {
    this._loadingState = value;
    this._render();
  }

  /**
   * Error message, that located under input line.
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /**
   * Value for input line.
   * @param {string} value
   */
  set value(value) {
    this._value = value;
    this._render();
  }

  /** @inheritDoc */
  _addEventListeners() {
    this._getElement('input-line').addEventListener('click', (event) => {
      event.stopPropagation();
    });
    this._getElement('input-line').addEventListener('keyup', (event) => {
      event.preventDefault();
      if (event.key === 'Enter') {
        this._onSubmitListener(this._getElement('input-line').value);
      }
    });
  }

  /** @inheritDoc */
  get _markup() {
    const loadingSymbol = `<span data-fh="loading-symbol" class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>`;
    return `<div class="input-value rename-element ${this._errorMessage && 'invalid-input-value'} ">
                 <input autofocus data-fh="input-line"
                  title="Input ${this._value}" type="email" placeholder="Input value..."
                 ${this._value && `value="${this._value}"`}>
                 ${this._loadingState ? loadingSymbol : ''}
                 ${this._errorMessage ?
        `<p data-fh="error-message" class="error-message error-rename-message" title="Error massage">${this._errorMessage}</p>` : ''}
            </div>`;
  }
}
