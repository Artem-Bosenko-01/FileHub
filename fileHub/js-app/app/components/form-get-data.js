import {Component} from './component.js';
import {Label} from './label.js';
import {Input} from './input.js';

/**
 * This is row that define special area to get some necessary user data.
 */
export class FormGetData extends Component {
  /**
   * @returns {string} - is html markup for rendering link.
   */
  get markup() {
    return `<div class="get-user-data"></div>`;
  }

  /**
   * This is function to adding label for input on get user data area.
   * @param {string} id
   * @param {string} title
   */
  addLabel(id, title) {
    const label = new Label(this.rootElement);
    label.id(id);
    label.title(title);
  }

  /**
   * This is function to adding input field on get user data area.
   * @param {string}  id
   * @param {string}  title
   * @param {string}  placeholder
   */
  addInput(id, title, placeholder) {
    const input = new Input(this.rootElement);
    input.id(id);
    input.title(title);
    input.placeholder(placeholder);
  }
}
