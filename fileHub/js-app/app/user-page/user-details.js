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

  /** @inheritDoc */
  get _markup() {
    let userDetailsBody;
    if (this._userFullName === 'loading') {
      userDetailsBody = `<li title="User full name">
                            <span class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>
                        </li>`;
    } else {
      const errorState = `<span class="error-message"><span
                    class="glyphicon glyphicon-exclamation-sign"></span> Can't load user data.</span>`;
      userDetailsBody = this._userFullName ?
          `<span class="glyphicon glyphicon-user"></span> ${this._userFullName}` : errorState;
    }

    return `<li data-fh="user-full-name"> ${userDetailsBody}</li>`;
  }
}
