/**
 * Custom exception for fail validate user data.
 **/
export default class ValidationError extends Error {
  /**
   * Constructor for getting data of exception.
   * @constructor
   * @param {string} message - specific message about incorrect data format.
   * */
  constructor(message) {
    super(message);
  }
}
