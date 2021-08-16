import {ActionInfo} from '../action-info.js';

/**
 *
 */
export class LogOutUser extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'logOutUser'

  /** @inheritDoc */
  get typeName() {
    return LogOutUser.typeName;
  }
}
