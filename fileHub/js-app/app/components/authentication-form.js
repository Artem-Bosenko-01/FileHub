import {Component} from './component.js';
import {Form} from './form.js';
import {FormInputField} from './form-input-field.js';
import {Validator} from '../validation/validator.js';
import {ParameterConfiguration, ValidationConfiguration} from '../validation/validationConfiguration.js';
import {lengthValidation, structureValidation} from '../validation/validation-rules.js';

/**
 * Component for authentication page that allows to get and validate user email and password.
 */
export class AuthenticationForm extends Component {
  /**
   * @inheritDoc
   */
  initNestedComponents() {
    this._form = new Form(this.rootElement);
    this._form.formHeader = 'Sign In to FileHub';
    this._form.buttonTitle = 'Sign In';
    this._form.linkMessage = 'Didn\'t have an account yet?';
    this._form.linkReference = 'registration.html';

    this._form.initInputs((container) => {
      this._emailInputField = new FormInputField(container);
      this._passwordInputField = new FormInputField(container);

      this._emailInputField.id = 'email-user';
      this._emailInputField.title = 'Email';
      this._emailInputField.inputType = 'text';

      this._passwordInputField.id = 'password-user';
      this._passwordInputField.title = 'Password';
      this._passwordInputField.inputType = 'text';
    });

    this._form.onSubmit = async () => {
      this._emailInputField.cleanErrorMessage();
      this._passwordInputField.cleanErrorMessage();

      const authenticationFormValidator = new Validator(
          new ValidationConfiguration(
              [
                new ParameterConfiguration(
                    lengthValidation(this._emailInputField, this._emailInputField.inputValue, 5)),
                new ParameterConfiguration(
                    structureValidation(this._emailInputField, this._emailInputField.inputValue)),
                new ParameterConfiguration(
                    lengthValidation(this._passwordInputField, this._passwordInputField.inputValue, 6)),
              ],
          ),
      );
      await this._form.validateActualForm(authenticationFormValidator);
    };
  }

  /**
   * @inheritDoc
   */
  get markup() {
    return `<div class="raw"></div>`;
  }
}
