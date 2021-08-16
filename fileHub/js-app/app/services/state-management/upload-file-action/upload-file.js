import {ActionInfo} from '../action-info.js';

/**
 * Contains data for uploading file to the directory..
 */
export class UploadFile extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'uploadFile';

  /**
   * @constructor
   * @param {File} file
   * @param {string} parentFolderId
   */
  constructor(file, parentFolderId) {
    super();
    this._file = file;
    this._parentFolderId = parentFolderId;
  }

  /**
   * @returns {FormData}
   */
  get file() {
    return this._file;
  }

  /**
   * @returns {string}
   */
  get parentFolderId() {
    return this._parentFolderId;
  }

  /** @inheritDoc */
  get typeName() {
    return UploadFile.typeName;
  }
}
