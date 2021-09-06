import {ActionInfo} from '../action-info.js';

/**
 * Contains information about item that needs to download.
 */
export class DownloadFile extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'downloadItemAction';

  /**
   * @constructor
   * @param {string} fileId
   */
  constructor(fileId) {
    super();
    this._fileId = fileId;
  }

  /**
   * @returns {FileListItem}
   */
  get fileId() {
    return this._fileId;
  }

  /** @inheritDoc */
  get typeName() {
    return DownloadFile.typeName;
  }
}
