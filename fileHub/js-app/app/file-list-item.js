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
  get id() {
    return this._itemId;
  }

  /** @returns {string} */
  get name() {
    return this._itemName;
  }

  /** @returns {string} */
  get type() {
    return this._itemType;
  }

  /** @returns {string} */
  get parentFolderId() {
    return this._parentFolderId;
  }

  /** @returns {number} */
  get size() {
    return this._itemSize;
  }

  /** @returns {string} */
  get mimeType() {
    return this._itemMimeType;
  }

  /** @returns {number} */
  get itemsAmount() {
    return this._itemsAmount;
  }
}
