import {ActionInfo} from '../action-info.js';

/**
 * Contains data for renaming item.
 */
export class RenameItem extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'renameItemAction';

  /**
   * @constructor
   * @param {FileListItem} item
   * @param {string} newName
   */
  constructor(item, newName) {
    super();
    this._item = item;
    this._newName = newName;
  }

  /**
   * @returns {FileListItem}
   */
  get item() {
    return this._item;
  }

  /**
   * @returns {string}
   */
  get newName() {
    return this._newName;
  }

  /** @inheritDoc */
  get typeName() {
    return RenameItem.typeName;
  }
}
