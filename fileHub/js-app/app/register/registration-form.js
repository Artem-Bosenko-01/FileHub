import {Component} from '../components/component.js';
import {Form} from '../components/form.js';
import {FormInputField} from '../components/form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validation-configuration.js';
import {confirmPasswordValidation, lengthValidation, structureValidation} from '../validation/validation-rules.js';
import {UsersInputsData} from '../user-inputs-data.js';

/**
 * Component for registration page that allows to get and
 * validate user email and password and check equals of input's password and input's confirm password.
 */
export class RegistrationForm extends Component {
  /**
   * Adds some event, which will be called on submitting form.
   * @param {function} event
   */
  onSubmit(event) {
    this._onSubmitAuthenticationEvent = event;
  }

  /** @inheritDoc */
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.formHeader = 'Sign Up to FileHub';
    this._form.buttonTitle = 'Sign Up';
    this._form.linkMessage = 'Already have an account?';
    this._form.linkReference = '#login';

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

    this._form.onSubmit = async () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();
      this._confirmPasswordInputField.cleanErrorMessage();

      const emailInputValue = this._emailInputField.inputValue;
      const passwordInputValue = this._passwordInputField.inputValue;

      const registrationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailInputField, emailInputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailInputField, emailInputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordInputField, passwordInputValue, 6)),
                new ParameterConfiguration(
                    confirmPasswordValidation(
                        this._confirmPasswordInputField,
                        passwordInputValue,
                        this._confirmPasswordInputField.inputValue),
                ),
              ],
          ));
      const results = await registrationFormValidator.validate();
      const isAnyPromiseStatusReject = results.some((result) => result.status === 'rejected');
      if (isAnyPromiseStatusReject) {
        this.renderErrorMessages(results);
      } else {
        alert('Successful validate input data');
        this._onSubmitAuthenticationEvent && this._onSubmitAuthenticationEvent(
            new UsersInputsData(emailInputValue, passwordInputValue));
      }
    };
  }

  /**
   * Adds error messages to inputs after analyzes validation results.
   * @param {PromiseRejectedResult[]} resultsOfValidation
   * @returns {void}
   */
  renderErrorMessages(resultsOfValidation) {
    resultsOfValidation
        .filter((result) => result.status === 'rejected')
        .forEach((result) => result.reason.component.errorMessage = result.reason.message);
  }

  /**
   * Adds server's error message to authentication form.
   * @param {string} errorMessage
   */
  addServerError(errorMessage) {
    alert(errorMessage);
  }

  /** @inheritDoc */
  get markup() {
    return `<div class="raw"></div>`;
  }
}
