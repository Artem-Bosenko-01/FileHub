/**
 * Error with status codes that indicate that there was likely an error in the request
 * which prevented the server from being able to process it.
 */
export class ClientServerError extends Error {
  /**
   * @constructor
   * @param {string} message
   */
  constructor(message) {
    super('400: ' + message);
  }
}
