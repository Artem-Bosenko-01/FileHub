/**
 * Dto for carrying user's full name and id.
 */
export class UserModel {
  /**
   * @constructor
   * @param {object} jsonObject
   */
  constructor(jsonObject) {
    this._userId = jsonObject.id;
    this._userFullName = jsonObject.loginName;
  }

  /** @returns {string} */
  get id() {
    return this._userId;
  }

  /** @returns {string} */
  get name() {
    return this._userFullName;
  }
}
