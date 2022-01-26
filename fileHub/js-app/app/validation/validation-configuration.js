/**
 * Set of validation rules for input fields with their values.
 */
export class ValidationConfiguration {
  /**
   * @constructor
   * @param {ValidationRule[]} rules
   */
  constructor(rules) {
    this._validationRules = rules;
  }

  /**
   * Gets all rules from configuration.
   * @returns {ValidationRule[]}
   */
  get rules() {
    return this._validationRules;
  }
}

/**
 * Wrapper for validation rule.
 */
export class ValidationRule {
  /**
   * @constructor
   * @param {FormInputField} field
   * @param {function(): Promise} validationRule
   */
  constructor(field, validationRule) {
    this._validator = validationRule;
    this._field = field;
  }

  /**
   * Rule from parameter configuration.
   * @returns {Function}
   */
  get validator() {
    return this._validator;
  }

  /**
   * Field which have a rule.
   * @returns {FormInputField}
   */
  get field() {
    return this._field;
  }
}
