/**
 * Manages the states of the application by processing actions.
 */
export class StateManager {
  /**
   * @constructor
   * @param {object} initialState
   * @param {object} services
   * @param {ActionFactory} actionFactory
   * @param {function} mutator
   */
  constructor(initialState, services, actionFactory, mutator) {
    this._services = services;
    this._state = initialState;
    this._actions = actionFactory;
    this._mutator = mutator;
    this._eventBus = new EventTarget();
  }

  /**
   * Proxy of state.
   * @returns {object}
   */
  get state() {
    return new Proxy(this._state, {
      set() {
        throw new Error('This is readonly object. State may be changed only by state manager!');
      },
    });
  }

  /**
   * Calls executor of some action from {@link ActionFactory actions list}.
   * @param {ActionInfo} action
   */
  dispatch(action) {
    const executor = this._actions.getActionExecutor(action.typeName);
    executor.apply(action, this._services, this._state,
        (mutatorName, details) => {
          this._mutate(mutatorName, details);
        },
        (actionInfo) => this.dispatch(actionInfo));
  }

  /**
   * Adds some event for listeners on change state.
   * @param {string} fieldName
   * @param {function()} listener
   */
  onStateChanged(fieldName, listener) {
    this._eventBus.addEventListener(`stateChanged-${fieldName}`, listener);
  }

  /**
   * Remove listener on change state.
   * @param {string} fieldName
   * @param {function(state: object)} listener
   */
  removeStateChangedListener(fieldName, listener) {
    this._eventBus.removeEventListener(`stateChanged-${fieldName}`, listener);
  }

  clearState() {
    this._state = {};
  }

  /**
   * Changes application components states.
   * @param {string} mutatorName
   * @param {object} details
   * @private
   */
  _mutate(mutatorName, details) {
    const mutatedState = this._mutator(mutatorName, details, this._state);
    const previousState = this._state;
    this._state = mutatedState;
    this._dispatchStateChangedAction(previousState, mutatedState, this._eventBus);
  }

  /**
   * @param {object} previousState
   * @param {object} newState
   * @param {EventTarget} eventBus
   * @returns {void}
   * @private
   */
  _dispatchStateChangedAction(previousState, newState, eventBus) {
    const changedState = new Proxy(previousState, {
      set(target, key, newValue) {
        if (target[key] === newValue) {
          return true;
        }
        eventBus.dispatchEvent(new CustomEvent(`stateChanged-${key}`));
        return true;
      },
    });
    Object.assign(changedState, newState);
  }
}
