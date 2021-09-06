/**
 * Result after validation of form.
 */
export class ValidationResult {
  /**
   * @constructor
   * @param {boolean} isValid - status of processed promise.
   * @param {FormInputField} field - field, which may haves an error message.
   * @param {string} [message] - error message.
   */
  constructor(isValid, field, message) {
    this._field = field;
    this._message = message;
    this._isValid = isValid;
  }

  /**
   * State of validation result.
   * @returns {boolean}
   */
  get isValid() {
    return this._isValid;
  }

  /**
   * Field, which was validate.
   * @returns {FormInputField}
   */
  get field() {
    return this._field;
  }

  /**
   * Error message, if it present.
   * @returns {string}
   */
  get message() {
    return this._message;
  }
}
