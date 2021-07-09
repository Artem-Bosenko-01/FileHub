import {Component} from './component.js';
import {Form} from './form.js';
import {FormGroupBox} from './form-group-box.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validationConfiguration.js';
import {confirmPasswordValidation, lengthValidation, structureValidation} from '../validation/validation-rules.js';

export class RegistrationForm extends Component {
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.header = 'Sign Up to FileHub';
    this._form.buttonTitle = 'Sign Up';
    this._form.linkMessage = 'Already have an account?';
    this._form.linkReference = 'index.html';

    this._form.initInputs((container) => {
      this._emailBox = new FormGroupBox(container);
      this._passwordBox = new FormGroupBox(container);
      this._confirmPasswordBox = new FormGroupBox(container);

      this._emailBox.id = 'email-user';
      this._emailBox.title = 'Email';
      this._emailBox.inputType = 'email';

      this._passwordBox.id = 'password-user';
      this._passwordBox.title = 'Password';
      this._passwordBox.inputType = 'password';

      this._confirmPasswordBox.id = 'confirm-password-user';
      this._confirmPasswordBox.title = 'Confirm Password';
      this._confirmPasswordBox.inputType = 'password';
    });

    this._form.onSubmit = () => {
      this._emailBox.cleanErrorMessage();
      this._passwordBox.cleanErrorMessage();
      this._confirmPasswordBox.cleanErrorMessage();

      const registrationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailBox, this._emailBox.inputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailBox, this._emailBox.inputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordBox, this._passwordBox.inputValue, 6)),
                new ParameterConfiguration(
                    confirmPasswordValidation(
                        this._confirmPasswordBox,
                        this._passwordBox.inputValue,
                        this._confirmPasswordBox.value),
                ),
              ],
          ));
      this._form.validateForm(registrationFormValidator);
    };
  }

  get markup() {
    return `<div class="raw"></div>`;
  }
}
