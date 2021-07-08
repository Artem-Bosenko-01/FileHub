export class ParameterValidation {
  /**
   * @param {FormGroupBox} component
   * @param {string} typeFormField
   */
  constructor(component, typeFormField) {
    this._component = component;
    this._typeFormField = typeFormField;
  }
  set confirmPassword(value) {
    this._confirmPasswordComponent = value;
  }

  get component() {
    return this._component;
  }

  get parameterValidationType() {
    return this._typeFormField;
  }

  get confirmPassword() {
    return this._confirmPasswordComponent;
  }
}
