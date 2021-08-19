import {Component} from '../components/component.js';

/**
 * Component for {@link FileListPage page} that contains information about all subscribers on state change at
 * {@link StateManager state manager}.
 */
export class StateBasedComponent extends Component {
  /** @inheritDoc */
  _init(titleService, stateManager) {
    this._titleService = titleService;
    this._titleService.addTitleForPage('Main Page');
    this._stateManager = stateManager;
    this._subscribers = new Map();
  }

  /**
   * @param {string} field
   * @param {function(state: Object)} listener
   */
  _onStateChangedListener(field, listener) {
    this._subscribers.set(field, listener);
    this._stateManager.onStateChanged(field, listener);
  }

  /**
   * Delete all listeners on state.
   */
  deleteAllSubscribersOnChangedState() {
    this._subscribers.forEach((value, key) => {
      this._stateManager.removeStateChangedListener(key, value);
    });
    this._subscribers = new Map();
  }
}
