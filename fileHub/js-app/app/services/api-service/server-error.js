/**
 * Error that throws when response status equals 500.
 */
export class ServerError extends Error {
  /**
   * @constructor
   * @param {string} message
   */
  constructor(message) {
    super('500: ' + message);
  }
}
