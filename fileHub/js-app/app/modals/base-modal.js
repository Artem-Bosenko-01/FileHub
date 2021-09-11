import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 * Base component for modal window rendering.
 */
export class BaseModalWindow extends Component {
  /**
   * Add listener on close button click.
   * @param {function} listener
   */
  onCloseButtonCLick(listener) {
    this._closeModalEvent = listener;
    this._render();
  }

  /**
   * Error message
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /**
   * Status of loading.
   * @param {boolean} value
   */
  set isLoading(value) {
    this._isLoading = value;
    this._render();
  }

  /**
   * Inner text for modal window.
   * @param {string} string
   */
  set innerTextForBody(string) {
    this._innerTextForBody = string;
    this._render();
  }

  /**
   * Adds initializer for submit button.
   * @param {function(parentElement: HTMLElement)} callback
   */
  initSubmitButton(callback) {
    this._submitButtonInitializer = callback;
    this._render();
  }

  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('close-button', (slotElement) => {
      const closeButton = new Button(slotElement);
      closeButton.buttonName = 'close-button';
      closeButton.buttonIcon = 'remove';
      closeButton.buttonClasses = ['button-shut-down'];
      closeButton.onClick(this._closeModalEvent);
      return closeButton;
    });

    this._mount('cancel-button', (slotElement) => {
      const cancelButton = new Button(slotElement);
      cancelButton.buttonName = 'cancel-button';
      cancelButton.buttonTitle = 'Cancel';
      cancelButton.buttonClasses = ['button-cancel'];
      cancelButton.onClick(this._closeModalEvent);
      return cancelButton;
    });

    this._submitButtonInitializer && this._mount('submit-button', (slotElement) => {
      return this._submitButtonInitializer(slotElement);
    });
  }

  /** @inheritDoc */
  _init(header) {
    this._header = header;
  }

  /** @inheritDoc */
  get _markup() {
    let modalBody;
    this._errorMessage ? modalBody = `<p data-fh="inner-text-body" class="error-message">${this._errorMessage}</p>` :
        modalBody = `<p data-fh="inner-text-body">${this._innerTextForBody}</p>`;

    return `<div data-fh="modal-body" class="modal-window">
        <div class="main">
            <div class="raw modal-raw">
                <header class="header">
                    <h2 data-fh="modal-header" class="modal-header">${this._header}</h2>
                    <slot data-fh="close-button"></slot>
                </header>
               <div class="data">
                ${modalBody}
                <div class="submit-box submit-modal-box ${this._isLoading && 'submit-loading-modal-box'}">
                    <slot data-fh="cancel-button"></slot>
                    <slot data-fh="submit-button"></slot>
                </div>
            </div>
            </div>
        </div>
    </div>
    `;
  }
}
