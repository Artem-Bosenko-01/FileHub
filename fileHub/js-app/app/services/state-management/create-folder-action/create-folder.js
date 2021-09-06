import {ActionInfo} from '../action-info.js';

/**
 * Execute creating a folder with inputted name.
 */
export class CreateFolder extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'createNewFolder';

  /**
   * @constructor
   * @param {string} folderName
   * @param {string} parentFolderId
   */
  constructor(folderName, parentFolderId) {
    super();
    this._folderName = folderName;
    this._parentFolderId = parentFolderId;
  }

  /**
   * @returns {string}
   */
  get parentFolderId() {
    return this._parentFolderId;
  }

  /**
   * @returns {string}
   */
  get folderName() {
    return this._folderName;
  }

  /** @inheritDoc */
  get typeName() {
    return CreateFolder.typeName;
  }
}
