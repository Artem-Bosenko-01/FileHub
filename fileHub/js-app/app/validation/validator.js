/**
 * Validate input fields using configuration.
 */
export class Validator {
  /**
   * @constructor
   * @param {ValidationConfiguration} validationConfig
   */
  constructor(validationConfig) {
    this._configuration = validationConfig;
  }

  /**
   * @typedef {Object} ValidationResult
   * @property {string} status - status of processed promise.
   * @property {FormInputField} field - field, which may haves an error message.
   * @property {string} (value|message) - if promise rejected - object will have an error message parameter.
   */
  /**
   * Validates all fields by rules from configuration.
   * @returns {ValidationResult[]}
   */
  async validate() {
    return await Promise.all(this._configuration.rules.map(async (rule) => {
      try {
        const result = await rule.validationRule();
        return {status: 'resolve', field: rule.field, value: result};
      } catch (e) {
        return {status: 'rejected', field: rule.field, message: e.message};
      }
    }));
  }
}

