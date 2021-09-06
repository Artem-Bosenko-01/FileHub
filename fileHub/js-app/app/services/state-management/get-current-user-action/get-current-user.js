import {ActionInfo} from '../action-info.js';

/**
 *
 */
export default class GetCurrentUser extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'getCurrentUser';

  /** @inheritDoc */
  get typeName() {
    return GetCurrentUser.typeName;
  }
}
