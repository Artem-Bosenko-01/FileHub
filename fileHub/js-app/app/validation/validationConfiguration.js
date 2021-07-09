export class ValidationConfiguration {
  /**
   *
   * @param {ParameterConfiguration[]} rules
   */
  constructor(rules) {
    this._validationFields = rules;
  }

  get rules() {
    return this._validationFields;
  }
}


export class ParameterConfiguration {
  constructor(validationRule) {
    this._validators = validationRule;
  }

  get validationRule() {
    return this._validators;
  }
}
