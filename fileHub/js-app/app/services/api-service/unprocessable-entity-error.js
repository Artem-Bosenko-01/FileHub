import {ValidationErrorCase} from './validation-error-case.js';

/**
 * Error that throws when response status equals 422.
 */
export class UnprocessableEntityError extends Error {
  /**
   * @constructor
   * @param {ValidationErrorCase[]} errors
   */
  constructor(errors) {
    super('This is 422 http failed response.');
    this._errors = errors;
  }

  /**
   * Gets massive of errors after backend query.
   * @returns {ValidationErrorCase[]}
   */
  get errors() {
    return this._errors;
  }
}
