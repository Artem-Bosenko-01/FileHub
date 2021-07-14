/**
 *
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
