import {Component} from '../components/component.js';
import {Form} from '../components/form.js';
import {FormInputField} from '../components/form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ValidationConfiguration, ValidationRule} from '../validation/validation-configuration.js';
import {emailRegexpValidation, lengthValidation} from '../validation/validation-rules.js';
import {UserData} from '../user-data.js';

/**
 * Component for authentication page that allows to get and validate user email and password.
 */
export class AuthenticationForm extends Component {
  /**
   * Adds an listener, which will be called on submitting form.
   * @param {function(UserData)} listener
   */
  onSubmit(listener) {
    this._onSubmitAuthenticationEvent = listener;
  }

  /**
   * Adds an event, event for navigation through pages.
   * @param {function} listener
   */
  onNavigateByLink(listener) {
    this._onNavigateListener = listener;
    this._render();
  }

  /**
   * Adds server's error message to authentication form.
   * @param {string} errorMessage
   */
  addServerError(errorMessage) {
    const messageBox = document.createElement('p');
    const dataAttribute = document.createAttribute('data-fh');
    dataAttribute.value = 'server-error';
    messageBox.attributes.setNamedItem(dataAttribute);
    messageBox.classList.add('error-message');
    messageBox.innerHTML = errorMessage;
    this._getElement('data').before(messageBox);
  }

  /**
   * Adds error messages to inputs after analyzes validation results.
   * @param {ValidationResult[]} resultsOfValidation
   * @private
   * @returns {void}
   */
  _renderErrorMessages(resultsOfValidation) {
    resultsOfValidation
        .filter((result) => !result.isValid)
        .forEach((result) => result.field.addErrorMessage(result.message));
  }

  /**
   * @inheritDoc
   */
  _initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.formHeader = 'Sign In to FileHub';
    this._form.buttonTitle = 'Sign In';
    this._form.linkMessage = 'Didn\'t have an account yet?';
    this._form.onLinkClick(this._onNavigateListener);

    this._form.initInputs((container) => {
      this._emailInputField = new FormInputField(container);
      this._passwordInputField = new FormInputField(container);

      this._emailInputField.id = 'email-user';
      this._emailInputField.title = 'Email';
      this._emailInputField.inputType = 'text';
      this._emailInputField.onChange((value) => this._emailInputValue = value);

      this._passwordInputField.id = 'password-user';
      this._passwordInputField.title = 'Password';
      this._passwordInputField.inputType = 'password';
      this._passwordInputField.onChange((value) => this._passwordInputValue = value);
    });

    this._form.onSubmit(async () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();

      const authenticationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ValidationRule(this._emailInputField, () => lengthValidation(this._emailInputValue, 5)),
                new ValidationRule(this._emailInputField, () => emailRegexpValidation(this._emailInputValue)),
                new ValidationRule(this._passwordInputField, () => lengthValidation(this._passwordInputValue, 6)),
              ],
          ),
      );
      const results = await authenticationFormValidator.validate();
      const hasInvalidValue = results.some((result) => !result.isValid);
      if (hasInvalidValue) {
        this._renderErrorMessages(results);
      } else {
        this._onSubmitAuthenticationEvent && this._onSubmitAuthenticationEvent(
            new UserData(this._emailInputValue, this._passwordInputValue));
      }
    });
  }

  /**
   * @inheritDoc
   */
  get _markup() {
    return `<div class="raw"></div>`;
  }
}
