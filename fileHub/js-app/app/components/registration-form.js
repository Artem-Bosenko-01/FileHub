import {Component} from './component.js';
import {Form} from './form.js';
import {FormGroupBox} from './form-group-box.js';

export class RegistrationForm extends Component {
  initNestedComponents() {
    const form = new Form(this.rootElement);
    form.header = 'Sign Up to FileHub';
    form.buttonTitle = 'Sign Up';
    form.linkMessage = 'Already have an account?';
    form.linkReference = 'index.html';

    form.initInputs((container)=>{
      const emailBox = new FormGroupBox(container);
      const passwordBox = new FormGroupBox(container);
      const confirmPasswordBox = new FormGroupBox(container);

      emailBox.id = 'email-user';
      emailBox.title = 'Email';
      emailBox.inputType = 'email';

      passwordBox.id = 'password-user';
      passwordBox.title = 'Password';
      passwordBox.inputType = 'password';

      confirmPasswordBox.id = 'confirm-password-user';
      confirmPasswordBox.title = 'Confirm Password';
      confirmPasswordBox.inputType = 'password';
    });
  }

  get markup() {
    return `<div class="raw"></div>`;
  }
}
