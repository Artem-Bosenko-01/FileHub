/**
 * Set of validation rules for input fields with their values.
 */
export class ValidationConfiguration {
  /**
   * @constructor
   * @param {ParameterConfiguration[]} rules
   */
  constructor(rules) {
    this._validationFields = rules;
  }

  /**
   * Gets all rules from configuration.
   * @returns {ParameterConfiguration[]}
   */
  get rules() {
    return this._validationFields;
  }
}

/**
 * Wrapper for validation rule.
 */
export class ParameterConfiguration {
  /**
   * @constructor
   * @param {Promise} validationRule
   */
  constructor(validationRule) {
    this._validators = validationRule;
  }

  /**
   * Gets rule from parameter configuration.
   * @returns {Function}
   */
  get validationRule() {
    return this._validators;
  }
}
