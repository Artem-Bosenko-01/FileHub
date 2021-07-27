import {ActionInfo} from "./action-info.js";

/**
 * Contains data for deleting a file from the directory.
 */
export default class DeleteFile extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'deleteFileAction';

  /**
   * @constructor
   * @param {string} fileId
   */
  constructor(fileId) {
    super();
    this._fileId = fileId;
  }

  /** @returns {string} */
  get fileId() {
    return this._fileId;
  }

  /** @inheritDoc */
  get typeName() {
    return DeleteFile.typeName
  }
}