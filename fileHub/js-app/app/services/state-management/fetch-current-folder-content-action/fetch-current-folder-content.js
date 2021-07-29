import {ActionInfo} from '../action-info.js';

/**
 *
 */
export class FetchCurrentFolderContent extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'fetchCurrentFolderContent';

  /** @inheritDoc */
  get typeName() {
    return FetchCurrentFolderContent.typeName;
  }
}
