import {ActionInfo} from '../action-info.js';

/**
 *
 */
export class GetRootFolder extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'getRootFolder'

  /** @inheritDoc */
  get typeName() {
    return GetRootFolder.typeName;
  }
}
