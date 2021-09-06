import {ActionInfo} from '../action-info.js';

/**
 * Contains data for filtering items.
 */
export class SearchInfo extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'searchInfoAction';

  /**
   * @constructor
   * @param {string} line
   */
  constructor(line) {
    super();
    this._seatchLine = line;
  }

  /**
   * @returns {string}
   */
  get searchLine() {
    return this._seatchLine;
  }


  /** @inheritDoc */
  get typeName() {
    return SearchInfo.typeName;
  }
}
