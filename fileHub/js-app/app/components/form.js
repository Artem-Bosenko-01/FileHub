import {Component} from './component.js';
import {Button} from './button.js';

/**
 * Abstract base component to configure user forms.
 */
export class Form extends Component {
  /**
   * Adds title for form header.
   * @param {string}  value
   */
  set formHeader(value) {
    this._formHeader = value;
    this.render();
  }

  /**
   * Adds reference action, when user click on text-link.
   * @param {string}  value
   */
  set linkReference(value) {
    this._link = value;
    this.render();
  }

  /**
   * Adds specific inner message or symbol to link.
   * @param {string}  value
   */
  set linkMessage(value) {
    this._message = value;
    this.render();
  }

  /**
   * Adds title to button.
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
    this.render();
  }

  /**
   * Adds some event to form, which process on submit form.
   * @param {function} handler
   */
  set onSubmit(handler) {
    this._eventOnSubmit = handler;
    this.render();
  }

  /**
   * @inheritDoc
   */
  addEventListeners() {
    this._eventOnSubmit && this.rootElement.addEventListener('submit', this._eventOnSubmit);
  }

  /**
   * @inheritDoc
   */
  initNestedComponents() {
    const inputRoot = this.getElement('data');
    this._initInputs && this._initInputs(inputRoot);

    this.mount('button', (component) => {
      const button = new Button(component);
      button.buttonTitle = `${this._buttonTitle}`;
      return button;
    });
  }

  /**
   * Validates actual form by something rules, which kept in validate configuration.
   * @param {Validator} validator
   */
  async validateActualForm(validator) {
    const results = await validator.validate();
    const isAnyPromiseStatusReject = results.some((result) => result.status === 'rejected');
    if (isAnyPromiseStatusReject) {
      this.renderErrorMessages(results);
      return false;
    } else {
      alert('Successful validate input data');
      return true;
    }
  }

  /**
   * Adds error messages to inputs after analyzes validation results.
   * @param {PromiseRejectedResult[]} resultsOfValidation
   * @returns {void}
   */
  renderErrorMessages(resultsOfValidation) {
    resultsOfValidation
        .filter((result) => result.status === 'rejected')
        .forEach((result) => result.reason.component.errorMessage = result.reason.message);
  }

  /** @inheritDoc */
  get markup() {
    return `<form data-fh="form" onsubmit="return false">
            <header class="header">
                <h2>${this._formHeader}</h2>
            </header>
            <hr>
            <div class="data" data-fh="data"></div>
            <div class="submit-box" data-fh="submit-box">
                    <slot data-fh="button"></slot>
                    <a title="Registration" class="reference" href="${this._link}">${this._message}</a>
                </div>
        </form>`;
  }
}
