import {mutator} from './mutator/mutator.js';

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

    const eventBus = this._eventBus;
    this._state = new Proxy(this._eventBus, {
      set(target, key, newValue) {
        if (target[key] === newValue) {
          return true;
        }

        const updateTargetValue = Reflect.set(target, key, newValue);
        if (updateTargetValue) {
          eventBus.dispatchEvent(new CustomEvent(`stateChanged-${key}`, {
            detail: {state: target},
          }));
          return true;
        }
      },
    });
  }

  /**
   * Changes application components states.
   * @param {string} mutatorName
   * @param {object} details
   * @private
   */
  _mutate(mutatorName, details) {
    const state = this._state;
    const mutatedState = mutator(mutatorName, details, state);
    this._state = Object.assign(this._state, mutatedState);
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
    executor.apply(action, this._services, this._state, (mutatorName, details) => {
      this._mutate(mutatorName, details);
    });
  }

  /**
   * // TODO Utilize articles
   * Adds an event listener on a change state.
   * @param {string} fieldName
   * @param {function({CustomEventInit})} listener
   */
  onStateChanged(fieldName, listener) {
    this._eventBus.addEventListener(`stateChanged-${fieldName}`, (e) => listener(e.detail.state));
  }
}
