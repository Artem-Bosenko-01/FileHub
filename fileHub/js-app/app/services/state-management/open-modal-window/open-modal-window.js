import {ActionInfo} from '../action-info.js';

/**
 * Contains information for opening modal window.
 */
export class OpenModalWindow extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'openModalWindow'

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
    return OpenModalWindow.typeName;
  }
}
