import {ActionInfo} from '../action-info.js';

/**
 * Contains data for logging out users.
 */
export class LogOutUser extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'logOutUser'

  /** @inheritDoc */
  get typeName() {
    return LogOutUser.typeName;
  }
}
