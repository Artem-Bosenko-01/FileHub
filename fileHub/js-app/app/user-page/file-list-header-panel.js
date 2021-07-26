import {Component} from '../components/component.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';

/**
 * Component that rendered at header at user main page.
 */
export class FileListHeaderPanel extends Component {
  /**
   * Full user name.
   * @param {string} value
   */
  set userFullName(value) {
    this._userFullName = value;
    this._render();
  }
  /** @inheritDoc */
  _initNestedComponents() {
    const userDetails = new UserDetails(this.rootElement);
    userDetails.userFullName = this._userFullName;
    new LogOut(this.rootElement);
  }

  /** @inheritDoc */
  get _markup() {
    return `<ul data-fh="user-panel" class="panel">
            </ul>`;
  }
}
