import {ActionInfo} from '../action-info.js';

/**
 * Contains information about item that needs be deleted.
 */
export class DeleteItem extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'deleteItemAction';

  /**
   * @constructor
   * @param {FileListItem} item
   */
  constructor(item) {
    super();
    this._item = item;
  }

  /**
   * @returns {FileListItem}
   */
  get item() {
    return this._item;
  }

  /** @inheritDoc */
  get typeName() {
    return DeleteItem.typeName;
  }
}
