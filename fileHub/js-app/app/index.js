import {validationForm} from './validation-form.js';
import {FormGroupBox} from './components/form-group-box.js';
import {SubmitBox} from './components/submit-box.js';

const form = document.getElementsByClassName('data')[0];

const emailBox = new FormGroupBox(form);
const passwordBox = new FormGroupBox(form);
const submitBox = new SubmitBox(form);

emailBox.id = 'email-user';
emailBox.title = 'Email';
emailBox.inputType = 'email';
emailBox.onChange((message) => emailBox.errorMessage = message);

passwordBox.id = 'password-user';
passwordBox.title = 'Password';
passwordBox.inputType = 'password';

submitBox.buttonTitle = 'Sign In';
submitBox.linkReference = 'registration.html';
submitBox.linkMessage = 'Didn\'t have an account yet?';
submitBox.onClick(() => validationForm(form));
