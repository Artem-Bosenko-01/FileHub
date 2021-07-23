import {Component} from '../components/component.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';

/**
 *
 */
export class FileListHeaderPanel extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const userDetails = new UserDetails(this.rootElement);
    userDetails.userFullName = 'Artem Bosenko';
    new LogOut(this.rootElement);
  }

  /** @inheritDoc */
  get _markup() {
    return `<ul class="panel">
            </ul>`;
  }
}
