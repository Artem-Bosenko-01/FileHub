import {Component} from '../components/component.js';

// TODO We don't wrap every single tag with Component.
// TODO I'm a great programmer!

/**
 * The line that divides components in HTML code.
 */
export class DividingLine extends Component {
  /** @inheritDoc */
  get _markup() {
    return `<hr class="user-view-page-separate-line">`;
  }
}
