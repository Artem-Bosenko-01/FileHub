/**
 * Abstract base contains info for some {@link ActionExecutor executor}.
 */
export class ActionInfo {
  /**
   * name of action type.
   * @type {string}
   */
  static typeName = '';

  /**
   * @constructor
   * @param {object} services
   * @param {object} state
   */
  constructor(services, state) {
    this._services = services;
    this._state = state;
  }

  /** @returns {Object} */
  get services() {
    return this._services;
  }

  /** @returns {Object} */
  get state() {
    return this._state;
  }

  /** @returns {string} */
  get typeName() {
    return ActionInfo.typeName
  }
}