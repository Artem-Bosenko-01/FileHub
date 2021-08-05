import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 *
 */
export class BaseModalWindow extends Component {
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
      closeButton.onClick(() => alert('closed'));
      return closeButton;
    });

    this._mount('cancel-button', (slotElement) => {
      const cancelButton = new Button(slotElement);
      cancelButton.buttonName = 'cancel-button';
      cancelButton.buttonTitle = 'Cancel';
      cancelButton.buttonClasses = ['button-cancel'];
      return cancelButton;
    });

    this._submitButtonInitializer && this._mount('submit-button', (slotElement) => {
      this._submitButtonInitializer(slotElement);
    });
  }

  /** @inheritDoc */
  _init(header) {
    this._header = header;
  }

  /** @inheritDoc */
  get _markup() {
    return `<div class="modal-window">
        <div class="main">
            <div class="raw modal-raw">
                <header class="header">
                    <h2 class="modal-header">${this._header}</h2>
                    <slot data-fh="close-button"></slot>
                </header>
               <div class="data">
                ${this._innerTextForBody ? `<p>${this._innerTextForBody}</p>` : ''}
                <div class="submit-box submit-modal-box">
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
