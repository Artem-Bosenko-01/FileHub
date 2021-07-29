import {ActionInfo} from '../action-info.js';

/**
 *
 */
export class GetRootFolder extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'getRootFolder'

  /**
   * @constructor
   * @param {function(hash: string)} redirect
   */
  constructor(redirect) {
    super();
    this._redirect = redirect;
  }

  /**
   * Function for redirecting.
   * @returns {function(hash: string)}
   */
  get redirect() {
    return this._redirect;
  }

  /** @inheritDoc */
  get typeName() {
    return GetRootFolder.typeName;
  }
}
