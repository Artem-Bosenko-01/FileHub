import {ActionInfo} from '../action-info.js';

/**
 * Contains data for fetching root folder for user main page.
 */
export class GetRootFolder extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'getRootFolder'

  /** @inheritDoc */
  get typeName() {
    return GetRootFolder.typeName;
  }
}
