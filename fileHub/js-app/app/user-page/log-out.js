import {Component} from '../components/component.js';

/**
 *
 */
export class LogOut extends Component {
  /** @inheritDoc */
  get _markup() {
    return `<li>
                    <a title="log out" class="highlight" href="#">
                        Log out <span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>`;
  }
}
