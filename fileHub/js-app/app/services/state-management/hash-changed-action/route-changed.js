import {ActionInfo} from '../action-info.js';

/**
 * Contains data about url params.
 */
export class RouteChanged extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'hashChanged';

  /**
   * @constructor
   * @param {string} url
   */
  constructor(url) {
    super();
    const urlParams = url.split('/');
    this._staticParam = urlParams[0];
    this._dynamicParam = urlParams[1];
  }

  /** @returns {string} */
  get staticParam() {
    return this._staticParam;
  }

  /** @returns {string} */
  get dynamicParam() {
    return this._dynamicParam;
  }

  /** @inheritDoc */
  get typeName() {
    return RouteChanged.typeName;
  }
}
