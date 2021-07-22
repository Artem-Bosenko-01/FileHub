import {Component} from './component.js';

/**
 * Improved HTML button tag.
 */
export class Button extends Component {
  /**
   * @constructor
   * @param {HTMLElement} parentElement
   */
  constructor(parentElement) {
    super(parentElement);
    this._iconClasses = [];
  }

  /**
   * Inner text to button.
   * @param {string}  value - button title.
   */
  set buttonTitle(value) {
    this._buttonTitle = value;
    this._render();
  }

  /**
   * Special icon to the button by indication class of symbol in value field.
   * @param {string}  value - class of symbol.
   */
  set buttonIcon(value) {
    this._buttonIcon = value;
    this._render();
  }

  /**
   *
   * @param {string[]} value
   */
  set iconClasses(value) {
    this._iconClasses = value;
    this._render();
  }

  /**
   * Adds classes to button HTML tag if it necessary.
   * @param {string} iconClass
   */
  addIconClass(iconClass) {
    this._iconClasses.push(iconClass);
    this._render();
  }

  /**
   * Adds some event for listeners on click button.
   * @param {function} event
   */
  onClick(event) {
    this._onCLickEvent = event;
  }

  /** @inheritDoc */
  _addEventListeners() {
    const button = this._getElement('button');
    if (button) {
      button.addEventListener('click', this._onCLickEvent);
    }
  }

  /** @inheritDoc */
  get _markup() {
    const expression = this._iconClasses && this._iconClasses.length > 0;
    const icon = `<span class="glyphicon glyphicon-${this._buttonIcon} ${expression &&
    this._iconClasses.join(' ')}"></span>`;
    return `<button id="button" title="Submit" 
                data-fh="button" class="button"
                >${this._buttonIcon ? icon : ''}${this._buttonTitle ? this._buttonTitle : ''}</button>`;
  }
}
