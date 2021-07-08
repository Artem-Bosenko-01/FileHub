import {Component} from './component.js';
import {Button} from './button.js';

export class Form extends Component {
  set header(value) {
    this._formHeader = value;
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

  set buttonTitle(value) {
    this._buttonTitle = value;
  }

  /**
   * @param{function(HTMLElement)} initializer
   */
  initInputs(initializer) {
    this._initInputs = initializer;
    this.render();
  }

  set onSubmit(handler) {
    this._eventOnSubmit = handler;
    this.render();
  }


  addEventListeners() {
    this._eventOnSubmit && this.rootElement.addEventListener('submit', this._eventOnSubmit);
  }

  initNestedComponents() {
    const inputRoot = this.getElement('data');
    this._initInputs && this._initInputs(inputRoot);

    this.mount('button', (component) => {
      const button = new Button(component);
      button.buttonTitle = `${this._buttonTitle}`;
    });
  }

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
