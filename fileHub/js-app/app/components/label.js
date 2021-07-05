import {Component} from './component.js';

/**
 * Label is an Html markup component. That usually saves name of {@link Input input}.
 */
export class Label extends Component {
  /**
   * This is function for adding title to label.
   * @param {string}  value
   */
  title(value) {
    this._title = value;
    this.render();
  }

  /**
   * This is function for adding input's id to label.
   * @param {string}  value
   */
  id(value) {
    this._id = value;
    this.render();
  }

  /**
   * @returns {string} - is html markup for rendering link.
   */
  get markup() {
    return `<label class="label-name" title="${this._title}" for="${this._id}">${this._title}</label>`;
  }
}

