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

