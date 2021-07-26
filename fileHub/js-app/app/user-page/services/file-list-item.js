/**
 * Dto that saves data about file list item.
 */
export class FileListItem {
  /** @param {string} value */
  set itemId(value) {
    this._itemId = value;
  }

  /** @param {string} value */
  set itemName(value) {
    this._itemName = value;
  }

  /** @param {string} value */
  set itemType(value) {
    this._itemType = value;
  }

  /** @param {string} value */
  set parentFolderId(value) {
    this._parentFolderId = value;
  }

  /** @param {number}  value */
  set itemSize(value) {
    this._itemSize = value;
  }

  /** @param {string} value */
  set itemMimeType(value) {
    this._itemMimeType = value;
  }

  /** @param {number} value */
  set itemsAmount(value) {
    this._itemsAmount = value;
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
