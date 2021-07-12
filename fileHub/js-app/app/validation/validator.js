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
   * @returns {Promise<resolve|reject>[]}
   */
  async validate() {
    const promises = [];
    this._configuration.rules.forEach((rule) => promises.push(rule.validationRule));
    return await Promise.allSettled(promises);
  }
}

