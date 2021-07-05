import {Component} from './component.js';
import {Button} from './button.js';
import {Link} from './link.js';

/**
 * This is area, where shows instruments for some form actions: submit input fields or click link.
 */
export class SubmitBox extends Component {
  /**
   * @returns {string} - is html markup for rendering link.
   */
  get markup() {
    return `<div class="submit-box"></div>`;
  }

  /**
   * This is function to adding button on submit area.
   * @param {string}  title
   * @param {string}  text
   */
  addButton(title, text) {
    const button = new Button(this.rootElement);
    button.title(title);
    /* button.text('<span class="glyphicon glyphicon-repeat loading"></span>');*/
    button.text(text);
  }

  /**
   * This is function to adding link on submit area.
   * @param {string}  title
   * @param {string}  message - is string, that will be showing like link message.
   * @param {string}  reference - is page, which will be opened if user clicked on link.
   */
  addLink(title, message, reference) {
    const link = new Link(this.rootElement);
    link.title(title);
    link.message(message);
    link.reference(reference);
  }
}
