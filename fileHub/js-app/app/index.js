import {validationForm} from './validation-form.js';
import {FormGroupBox} from './components/form-group-box.js';
import {SubmitBox} from './components/submit-box.js';

const form = document.getElementsByClassName('data')[0];

const emailBox = new FormGroupBox(form);
const passwordBox = new FormGroupBox(form);
const submitButtonBox = new SubmitBox(form);

emailBox.id = 'email-user';
emailBox.title = 'Email';
emailBox.inputType = 'email';
emailBox.onChange((message) => emailBox.errorMessage = message);

passwordBox.id = 'password-user';
passwordBox.title = 'Password';
passwordBox.inputType = 'password';

submitButtonBox.buttonTitle = 'Sign In';
submitButtonBox.linkReference = 'registration.html';
submitButtonBox.linkMessage = 'Didn\'t have an account yet?';
submitButtonBox.onClick(() => validationForm(form));
