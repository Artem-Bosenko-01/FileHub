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
   *
   * @returns {string}
   */
  get field() {
    return this._field;
  }

  /**
   *
   * @returns {string}
   */
  get message() {
    return this._message;
  }
}
