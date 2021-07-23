import {Component} from '../components/component.js';

/**
 *
 */
export class UserDetails extends Component {
  /**
   * User full name.
   * @param {string} value
   */
  set userFullName(value) {
    this._userFullName = value;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    const errorState = `<span class="error-message"><span
                    class="glyphicon glyphicon-exclamation-sign"></span> Can't load user data.</span>`;
    const expression = this._userFullName ?
        `<span class="glyphicon glyphicon-user"></span> ${this._userFullName}` : errorState;

    return `<li title="${this._userFullName ? this._userFullName : ''}"> ${expression}</li>`;
  }
}
