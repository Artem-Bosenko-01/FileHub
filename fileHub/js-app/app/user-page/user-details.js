import {Component} from '../components/component.js';

/**
 * Component that renders information about user.
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

  /**
   *
   * @param {boolean} value
   */
  set loading(value) {
    this._loadingFetchingUserData = value;
    this._render();
  }

  /**
   *
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    if (this._loadingFetchingUserData) {
      return `<li data-fh="user-full-name">
                 <span data-fh="loading-symbol" class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>
              </li>`;
    }

    if (this._userFullName) {
      return `<li data-fh="user-full-name"><span class="glyphicon glyphicon-user"></span> ${this._userFullName}</li>`;
    }

    if (this._errorMessage) {
      return `<li data-fh="user-full-name">
                 <span class="error-message"><span class="glyphicon glyphicon-exclamation-sign">
                 </span> Can't load user data.</span>
              </li>`;
    }

    return `<div></div>`;
  }
}
