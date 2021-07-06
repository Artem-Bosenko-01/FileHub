import {validationForm} from './validation-form.js';
import {FormGroupBox} from './components/form-group-box.js';
import {SubmitBox} from './components/submit-box.js';

const form = document.getElementsByClassName('data')[0];

const emailBox = new FormGroupBox(form);
const passwordBox = new FormGroupBox(form);
const confirmPasswordBox = new FormGroupBox(form);
const submitButtonBox = new SubmitBox(form);

emailBox.id = 'email-user';
emailBox.title = 'Email';
emailBox.inputType = 'email';

passwordBox.id = 'password-user';
passwordBox.title = 'Password';
passwordBox.inputType = 'password';

confirmPasswordBox.id = 'confirm-password-user';
confirmPasswordBox.title = 'Confirm Password';
confirmPasswordBox.inputType = 'password';

submitButtonBox.buttonTitle = 'Sign Up';
submitButtonBox.linkReference = 'index.html';
submitButtonBox.linkMessage = 'Are you already an account?';
submitButtonBox.onClick(() => validationForm(form));
