import {ActionInfo} from '../action-info.js';

/**
 * Contains data for fetching current folder content for user main page.
 */
export class FetchCurrentFolderContent extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'fetchCurrentFolderContent';

  /** @inheritDoc */
  get typeName() {
    return FetchCurrentFolderContent.typeName;
  }
}
