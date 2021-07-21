import {Component} from '../components/component.js';
import {Form} from '../components/form.js';
import {FormInputField} from '../components/form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ValidationConfiguration, ValidationRule} from '../validation/validation-configuration.js';
import {confirmPasswordValidation, emailRegexpValidation, lengthValidation} from '../validation/validation-rules.js';
import {UserData} from '../user-data.js';

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
  _initNestedComponents() {
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
      this._emailInputField.onChange((value) => this._emailInputValue = value);

      this._passwordInputField.id = 'password-user';
      this._passwordInputField.title = 'Password';
      this._passwordInputField.inputType = 'password';
      this._passwordInputField.onChange((value) => this._passwordInputValue = value);

      this._confirmPasswordInputField.id = 'confirm-password-user';
      this._confirmPasswordInputField.title = 'Confirm Password';
      this._confirmPasswordInputField.inputType = 'password';
      this._confirmPasswordInputField.onChange((value) => this._confipasswordInputValue = value);
    });

    this._form.onSubmit = async () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();
      this._confirmPasswordInputField.cleanErrorMessage();

      const registrationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ValidationRule(this._emailInputField, () => lengthValidation(this._emailInputValue, 5)),
                new ValidationRule(this._emailInputField, () => emailRegexpValidation(this._emailInputValue)),
                new ValidationRule(this._passwordInputField, () => lengthValidation(this._passwordInputValue, 6)),
                new ValidationRule(this._confirmPasswordInputField, () => confirmPasswordValidation(
                    this._passwordInputValue, this._confipasswordInputValue)),
              ],
          ));
      const results = await registrationFormValidator.validate();
      const isAnyPromiseStatusReject = results.some((result) => result.status === 'rejected');
      if (isAnyPromiseStatusReject) {
        this._renderErrorMessages(results);
      } else {
        this._onSubmitAuthenticationEvent && this._onSubmitAuthenticationEvent(
            new UserData(this._emailInputValue, this._passwordInputValue));
      }
    };
  }

  /**
   * Adds error messages to inputs after analyzes validation results.
   * @param {PromiseRejectedResult[]} resultsOfValidation
   * @private
   * @returns {void}
   */
  _renderErrorMessages(resultsOfValidation) {
    resultsOfValidation
        .filter((result) => result.status === 'rejected')
        .forEach((result) => result.field.addErrorMessage(result.message));
  }

  /**
   * Adds server's error message to authentication form.
   * @param {string} errorMessage
   */
  addServerError(errorMessage) {
    this.clearPreviousServerErrors();
    const tagP = document.createElement('p');
    const dataAttribute = document.createAttribute('data-fh');
    dataAttribute.value = 'server-error';
    tagP.attributes.setNamedItem(dataAttribute);
    tagP.classList.add('error-message');
    tagP.innerHTML = errorMessage;
    this._getElement('data').before(tagP);
  }

  clearPreviousServerErrors() {
    const error = this._getElement('server-error');
    error && error.remove();
  }
  /** @inheritDoc */
  get _markup() {
    return `<div class="raw"></div>`;
  }
}
