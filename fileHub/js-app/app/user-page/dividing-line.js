import {Component} from '../components/component.js';

/**
 * The line that divides components in HTML code.
 */
export class DividingLine extends Component {
  /** @inheritDoc */
  get _markup() {
    return `<hr class="user-view-page-separate-line">`;
  }
}
