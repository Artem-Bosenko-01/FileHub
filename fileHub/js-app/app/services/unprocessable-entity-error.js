/**
 *
 */
export class UnprocessableEntityError extends Error {
  /**
   * @constructor
   * @param {ErrorEntityDTO[]} errors
   */
  constructor(errors) {
    super('This is 422 http failed response.');
    this._errors = errors;
  }

  /**
   * Gets massive of errors after backend query.
   * @returns {ErrorEntityDTO[]}
   */
  get errors() {
    return this._errors;
  }
}

/**
 *
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
