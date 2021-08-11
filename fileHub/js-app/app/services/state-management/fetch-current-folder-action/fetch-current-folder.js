import {ActionInfo} from '../action-info.js';

/**
 * Contains data for fetching current folder for user main page.
 */
export default class FetchCurrentFolder extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'fetchCurrentFolderAction';

  /** @inheritDoc */
  get typeName() {
    return FetchCurrentFolder.typeName;
  }
}
