/**
 * Object that needs for saves data about error which calls when response status equals 422.
 */
export class ErrorEntityDTO {
  /**
	 * @constructor
	 * @param {string} field
	 * @param {string} message
	 */
  constructor(field, message) {
    this._field = field;
    this._message = message;
  }

  /**
	 * Gets field where needs show error.
	 * @returns {string}
	 */
  get field() {
    return this._field;
  }

  /**
	 * Gets message of error.
	 * @returns {string}
	 */
  get message() {
    return this._message;
  }
}
