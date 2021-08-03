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
   * Changes application components states.
   * @param {string} mutatorName
   * @param {object} details
   * @private
   */
  _mutate(mutatorName, details) {
    const mutatedState = this._mutator(mutatorName, details, this._state);
    const previousState = this._state;
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

        const updateTargetValue = Reflect.set(target, key, newValue);
        if (updateTargetValue) {
          eventBus.dispatchEvent(new CustomEvent(`stateChanged-${key}`, {
            detail: {state: target},
          }));
        }
        return true;
      },
    });
    Object.assign(changedState, newState);
  }

  /**
   * Proxy of state.
   * @returns {object}
   */
  get state() {
    return this._state;
  }

  /**
   * Calls executor of some action from {@link ActionFactory actions list}.
   * @param {ActionInfo} action
   */
  dispatch(action) {
    const executor = this._actions.getActionExecutor(action.typeName);
    executor.apply(action, this._services, this._state, (mutatorName, details) => {
      this._mutate(mutatorName, details);
    });
  }

  /**
   * Adds some event for listeners on change state.
   * @param {string} fieldName
   * @param {function({CustomEventInit})} listener
   */
  onStateChanged(fieldName, listener) {
    this._eventBus.addEventListener(`stateChanged-${fieldName}`, (e) => listener(e.detail.state));
  }
}
