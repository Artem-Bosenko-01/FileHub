import {Component} from './component.js';
import {Form} from './form.js';
import {FormGroupBox} from './form-group-box.js';
import {formValidation} from '../form-validation.js';
import {ParameterValidation} from '../parameter-validation.js';

/**
 * This is.
 */
export class AuthenticationForm extends Component {
  initNestedComponents() {
    const form = new Form(this.rootElement);
    form.header = 'Sign In to FileHub';
    form.buttonTitle = 'Sign In';
    form.linkMessage = 'Didn\'t have an account yet?';
    form.linkReference = 'registration.html';
    form.initInputs((container) => {
      const emailBox = new FormGroupBox(container);
      const passwordBox = new FormGroupBox(container);

      emailBox.id = 'email-user';
      emailBox.title = 'Email';
      emailBox.inputType = 'email';
      emailBox.onChange((message) => emailBox.errorMessage = message);

      passwordBox.id = 'password-user';
      passwordBox.title = 'Password';
      passwordBox.inputType = 'password';

      const emailValidation = new ParameterValidation(emailBox, 'email');
      const passwordValidation = new ParameterValidation(passwordBox, 'password');
      form.formAction = formValidation([emailValidation, passwordValidation]);
    });
  }

  get markup() {
    return `<div class="raw"></div>`;
  }
}
