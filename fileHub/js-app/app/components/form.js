import {Component} from './component.js';
import {Button} from './button.js';

/**
 * Base component to configure user forms.
 */
export class Form extends Component {
  /**
   * Title for form header.
   * @param {string}  value
   */
  set formHeader(value) {
    this._formHeader = value;
    this._render();
  }

  /**
   * Reference action, when user click on text-link.
   * @param {function}  event
   */
  onLinkClick(event) {
    this._onLinkClickEvent = event;
    this._render();
  }

  /**
   * Specific inner message or symbol to link.
   * @param {string}  value
   */
  set linkMessage(value) {
    this._message = value;
    this._render();
  }

  /**
   * Title to button.
   * @param {string} value
   */
  set buttonTitle(value) {
    this._buttonTitle = value;
  }

  /**
   * Initializes input fields on form for getting user's data.
   * @param{function(HTMLElement)} initializer
   */
  initInputs(initializer) {
    this._initInputs = initializer;
    this._render();
  }

  /**
   * Some event to form, which process on submit form.
   * @param {function} handler
   */
  onSubmit(handler) {
    this._eventOnSubmit = handler;
    this._render();
  }

  /**
   * @inheritDoc
   */
  _addEventListeners() {
    this._eventOnSubmit && this.rootElement.addEventListener('submit', this._eventOnSubmit);

    this._getElement('link').addEventListener('click', (event) => {
      this._onLinkClickEvent();
      event.preventDefault();
    });
  }

  /**
   * @inheritDoc
   */
  _initNestedComponents() {
    const inputRoot = this._getElement('data');
    this._initInputs && this._initInputs(inputRoot);

    this._mount('button', (slotElement) => {
      const button = new Button(slotElement);
      button.buttonName = 'submit-button';
      button.buttonTitle = `${this._buttonTitle}`;
      return button;
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<form data-fh="form" onsubmit="return false">
            <header class="header">
                <h2 data-fh="header">${this._formHeader}</h2>
            </header>
            <hr>
            <div class="data" data-fh="data"></div>
            <div class="submit-box" data-fh="submit-box">
                    <slot data-fh="button"></slot>
                    <a data-fh="link" class="reference" href="">${this._message}</a>
                </div>
        </form>`;
  }
}
