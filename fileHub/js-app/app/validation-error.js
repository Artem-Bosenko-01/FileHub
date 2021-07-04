/**
 * This is custom exception for fail validate user data.
 **/
export default class ValidationError extends Error {
  /**
  * This is constructor for getting data of exception.
  * @constructor
  * @param {string} idElement - is id of element, which should have an error message.
  * @param {string} message - is specific message about incorrect data format.
  * */
  constructor(idElement, message) {
    super(message);
    this.idElement = idElement;
  }
}
