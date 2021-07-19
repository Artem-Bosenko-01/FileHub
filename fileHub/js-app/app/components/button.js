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
    this._iconCLasses = [];
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
   * Adds classes to button HTML tag if it necessary.
   * @param {string} iconClass
   */
  addIconClasses(iconClass) {
    this._iconCLasses.push(iconClass);
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
    this._getElement('button') && this._getElement('button')
        .addEventListener('click', this._onCLickEvent);
  }

  /** @inheritDoc */
  get _markup() {
    const expression = this._iconCLasses && this._iconCLasses.length > 0;
    const icon = `<span class="glyphicon glyphicon-${this._buttonIcon} ${expression &&
    this._iconCLasses.forEach((iconClass) => iconClass)}"></span>`;
    return `<button id="button" title="Submit" 
                data-fh="button" class="button"
                >${this._buttonIcon ? icon : ''}${this._buttonTitle ? this._buttonTitle : ''}</button>`;
  }
}
