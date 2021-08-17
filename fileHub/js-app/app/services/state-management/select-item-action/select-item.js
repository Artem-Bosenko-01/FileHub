import {ActionInfo} from '../action-info.js';

/**
 * Contains data for selecting item.
 */
export class SelectItem extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'selectItemAction';

  /**
   * @constructor
   * @param {string} itemId
   */
  constructor(itemId) {
    super();
    this._itemId = itemId;
  }

  /**
   * @returns {string}
   */
  get id() {
    return this._itemId;
  }

  /** @inheritDoc */
  get typeName() {
    return SelectItem.typeName;
  }
}
