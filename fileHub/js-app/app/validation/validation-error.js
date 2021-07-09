/**
 * Custom exception for fail validate user data.
 **/
export default class ValidationError extends Error {
  /**
   * Constructor for getting data of exception.
   * @constructor
   * @param {FormInputField} component - component, which should have an error message.
   * @param {string} message - specific message about incorrect data format.
   * */
  constructor(component, message) {
    super(message);
    this.component = component;
  }
}
