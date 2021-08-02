/**
 * Dto that saves data about file list item.
 */
export class FileListItem {
  /**
   * @typedef {object} jsonObject
   * @property {string} id
   * @property {string} name
   * @property {string} type
   * @property {string} [parentFolderId]
   * @property {number} [itemSize]
   * @property {string} [itemMimeType]
   * @property {string} [itemsAmount]
   *
   * @constructor
   * @param {jsonObject} jsonObject
   */
  constructor(jsonObject) {
    this._itemId = jsonObject.id;
    this._itemName = jsonObject.name;
    this._itemType = jsonObject.type;
    this._parentFolderId = jsonObject.parentFolderId;
    this._itemSize = jsonObject.size;
    this._itemMimeType = jsonObject.mimeType;
    this._itemsAmount = jsonObject.itemsAmount;
  }

  /** @returns {string} */
  get itemId() {
    return this._itemId;
  }

  /** @returns {string} */
  get itemName() {
    return this._itemName;
  }

  /** @returns {string} */
  get itemType() {
    return this._itemType;
  }

  /** @returns {string} */
  get itemParentFolderId() {
    return this._parentFolderId;
  }

  /** @returns {number} */
  get itemSize() {
    return this._itemSize;
  }

  /** @returns {string} */
  get itemMimeType() {
    return this._itemMimeType;
  }

  /** @returns {number} */
  get itemsAmount() {
    return this._itemsAmount;
  }
}
