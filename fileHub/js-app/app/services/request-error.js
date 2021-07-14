/**
 *
 */
export class RequestError extends Error {
  /**
   * @constructor
   * @param {string} message
   */
  constructor(message) {
    super('400: ' + message);
  }
}
