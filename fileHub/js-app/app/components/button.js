import {Component} from './component.js';

/**
 * Improved HTML button tag.
 */
export class Button extends Component {
  /**
   * Adds inner text to button.
   * @param {string}  value - button title.
   */
  set buttonTitle(value) {
    this._buttonTitle = value;
    this.render();
  }

  /**
   * Adds a special icon to the button by indication class of symbol in value field.
   * @param {string}  value - class of symbol.
   */
  set buttonIcon(value) {
    this._buttonIcon = value;
    this.render();
  }

  /** @inheritDoc */
  get markup() {
    const icon = `<span class="glyphicon glyphicon-${this._buttonIcon} 
                        ${this._buttonIcon === 'repeat' ? 'loading': ''}"></span>`;
    return `<button id="button" title="Submit" data-fh="button" class="button">
                ${this._buttonIcon ? icon : ''} ${this._buttonTitle ? this._buttonTitle : ''}
            </button>`;
  }
}
