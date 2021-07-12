import {Component} from '../components/component.js';
import {Form} from '../components/form.js';
import {FormInputField} from '../components/form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validation-configuration.js';
import {lengthValidation, structureValidation} from '../validation/validation-rules.js';

/**
 * Component for authentication page that allows to get and validate user email and password.
 */
export class AuthenticationForm extends Component {
  /**
   * Adds some event, which will be called on submitting form.
   * @param {function} event
   */
  onSubmit(event) {
    this._onSubmitAuthenticationEvent = event;
  }

  /**
   * @inheritDoc
   */
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.formHeader = 'Sign In to FileHub';
    this._form.buttonTitle = 'Sign In';
    this._form.linkMessage = 'Didn\'t have an account yet?';
    this._form.linkReference = '#register';

    this._form.initInputs((container) => {
      this._emailInputField = new FormInputField(container);
      this._passwordInputField = new FormInputField(container);

      this._emailInputField.id = 'email-user';
      this._emailInputField.title = 'Email';
      this._emailInputField.inputType = 'text';

      this._passwordInputField.id = 'password-user';
      this._passwordInputField.title = 'Password';
      this._passwordInputField.inputType = 'password';
    });

    this._form.onSubmit = async () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();

      const emailInputValue = this._emailInputField.inputValue;
      const passwordInputValue = this._passwordInputField.inputValue;

      const authenticationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailInputField, emailInputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailInputField, emailInputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordInputField, passwordInputValue, 6)),
              ],
          ),
      );
      if (await this._form.validateActualForm(authenticationFormValidator)) {
        this._onSubmitAuthenticationEvent && this._onSubmitAuthenticationEvent(emailInputValue, passwordInputValue);
      }
    };
  }

  /**
   * Adds server's error message to authentication form.
   * @param {string} errorMessage
   */
  addServerError(errorMessage) {
    alert(errorMessage);
  }

  /**
   * @inheritDoc
   */
  get markup() {
    return `<div class="raw"></div>`;
  }
}
