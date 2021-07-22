import {ValidationResult} from './validation-result.js';

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
   * Validates all fields by rules from configuration.
   * @returns {ValidationResult[]}
   */
  async validate() {
    return await Promise.all(this._configuration.rules.map(async (rule) => {
      try {
        await rule.validator();
        return new ValidationResult(true, rule.field);
      } catch (e) {
        return new ValidationResult(false, rule.field, e.message);
      }
    }));
  }
}

