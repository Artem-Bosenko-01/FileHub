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
  constructor(fieldId, validationRule) {
    this._field = fieldId;
    this._validators = validationRule;
  }

  get fieldId() {
    return this._field;
  }

  get validationRule() {
    return this._validators;
  }
}
