import {Component} from '../components/component.js';

/**
 * Renders element to exit the application.
 */
export class LogOut extends Component {
  /**
   * Add listener on log-out link click.
   * @param {function()} listener
   */
  onClick(listener) {
    this._onClickListener = listener;
    this._render();
  }

  /** @inheritDoc */
  _addEventListeners() {
    this._getElement('log-out-ref').addEventListener('click', (event)=>{
      this._onClickListener();
      event.preventDefault();
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<li>
                    <a data-fh="log-out-ref" title="log out" class="highlight" href="">
                        Log out <span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>`;
  }
}
