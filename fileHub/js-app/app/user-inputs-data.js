/**
 * DTO for carrying user's data from inputs field to server.
 */
export class UsersInputsData {
  /**
   * @constructor
   * @param {string} email
   * @param {string} password
   */
  constructor(email, password) {
    this._email = email;
    this._password = password;
  }

  /**
   * @returns {string} email.
   */
  get email() {
    return this._email;
  }

  /**
   * @returns {string} password.
   */
  get password() {
    return this._password;
  }
}
