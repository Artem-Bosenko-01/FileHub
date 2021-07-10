import {Component} from './component.js';
import {Form} from './form.js';
import {FormInputField} from './form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validation-configuration.js';
import {confirmPasswordValidation, lengthValidation, structureValidation} from '../validation/validation-rules.js';

/**
 * Component for registration page that allows to get and
 * validate user email and password and check equals of input's password and input's confirm password.
 */
export class RegistrationForm extends Component {
  /** @inheritDoc */
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.formHeader = 'Sign Up to FileHub';
    this._form.buttonTitle = 'Sign Up';
    this._form.linkMessage = 'Already have an account?';
    this._form.linkReference = 'index.html';

    this._form.initInputs((container) => {
      this._emailInputField = new FormInputField(container);
      this._passwordInputField = new FormInputField(container);
      this._confirmPasswordInputField = new FormInputField(container);

      this._emailInputField.id = 'email-user';
      this._emailInputField.title = 'Email';
      this._emailInputField.inputType = 'email';

      this._passwordInputField.id = 'password-user';
      this._passwordInputField.title = 'Password';
      this._passwordInputField.inputType = 'password';

      this._confirmPasswordInputField.id = 'confirm-password-user';
      this._confirmPasswordInputField.title = 'Confirm Password';
      this._confirmPasswordInputField.inputType = 'password';
    });

    this._form.onSubmit = () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();
      this._confirmPasswordInputField.cleanErrorMessage();

      const registrationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailInputField, this._emailInputField.inputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailInputField, this._emailInputField.inputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordInputField, this._passwordInputField.inputValue, 6)),
                new ParameterConfiguration(
                    confirmPasswordValidation(
                        this._confirmPasswordInputField,
                        this._passwordInputField.inputValue,
                        this._confirmPasswordInputField.value),
                ),
              ],
          ));
      this._form.validateActualForm(registrationFormValidator);
    };
  }

  /** @inheritDoc */
  get markup() {
    return `<div class="raw"></div>`;
  }
}
