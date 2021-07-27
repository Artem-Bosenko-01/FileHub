/**
 * Manages the states of the application by processing actions.
 */
export class StateManager {
  /**
   * @constructor
   * @param {object} initialState
   * @param {object} services
   * @param {ActionFactory} actionFactory
   */
  constructor(initialState, services, actionFactory) {
    this._initialState = initialState;
    this._services = services;

    this._eventBus = new EventTarget();
    this._actions = actionFactory;

    this._state = new Proxy(this._eventBus, {
      set(target, key, newValue) {
        if (target[key] === newValue) {
          return;
        }

        const updateTargetValue = Reflect.set(target, key, newValue);
        if (updateTargetValue) {
          this._eventBus.dispatchEvent(new CustomEvent('stateChanged', {
            detail: {state: target},
          }));
        }
      },
    },
    );
  }

  /**
   * Proxy of state.
   * @returns {EventTarget}
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
    executor.apply(action);
  }

  /**
   * Adds some event for listeners on change state.
   * @param {function({CustomEventInit})} listener
   */
  onStateChanged(listener) {
    this._eventBus.addEventListener('stateChanged', ({details}) => listener(details.state));
  }
}
