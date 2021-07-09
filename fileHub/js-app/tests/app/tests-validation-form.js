import {AuthenticationForm} from '../../app/components/authentication-form.js';

const {module, test} = QUnit;
let authenticationForm;
let fixture;
module('validate authentication form', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
    authenticationForm = new AuthenticationForm(fixture);
  },
});

test('Should validate authentication form with 2 empty inputs.', async (assert) => {
  /* authenticationForm.getElement('inputemail-user').value = '56194acsas';*/
  const form = authenticationForm.getElement('form');

  await form.dispatchEvent(new Event('submit'));
  console.log('fuckin Validation passed');
  assert.equal(form.querySelectorAll(`[data-fh="error-message"]`).length, 2, 'Should show 2 error messages');

  /*
  innerHTML: "\n            <header class=\"header\">\n
  <h2>Sign In to FileHub</h2>\n            </header>\n            <hr>\n
  <div class=\"data\" data-fh=\"data\"><div class=\"get-user-data\" data-fh=\"get-user-data\">\n
   <label class=\"label-name\" data-fh=\"label-name\" for=\"email-user\">Email</label>\n
   <div class=\"input-value \">\n
   <input data-fh=\"inputemail-user\" title=\"Input Email\" type=\"text\" id=\"email-user\" placeholder=\"Email\" value=\"\">\n                   \n
      </div>\n            </div><div class=\"get-user-data\" data-fh=\"get-user-data\">\n                <label class=\"label-name\" data-fh=\"label-name\" for=\"password-user\">Password</label>\n                <div class=\"input-value \">\n                   <input data-fh=\"inputpassword-user\" title=\"Input Password\" type=\"text\" id=\"password-user\" placeholder=\"Password\" value=\"\">\n                   \n                </div>\n            </div></div>\n            <div class=\"submit-box\" data-fh=\"submit-box\">\n                    <slot data-fh=\"button\"><button id=\"button\" title=\"Submit\" data-fh=\"button\" class=\"button\">Sign In</button></slot>\n                    <a title=\"Registration\" class=\"reference\" href=\"registration.html\">Didn't have an account yet?</a>\n                </div>\n        "

   */
});

/*
test('Should validate authentication form with empty password', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'email-user', 'email-someone');

  await formValidation(form[0]);

  const errorPassword = form[0].querySelector('#user-password-box');
  assert.strictEqual(
      errorPassword.querySelector('.error-message').innerHTML,
      'Data length should be more than 6 symbols',
      'Should show 1 error message under user password input element');
});

test('Should validate authentication form with empty email', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');

  await formValidation(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  assert.strictEqual(
      errorEmail.querySelector('.error-message').innerHTML,
      'Data length should be more than 5 symbols',
      'Should show 1 error message under user email input element');
});

test('Should validate authentication form with incorrect email structure', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');
  addParameterToInput(form[0], 'email-user', 'email-so#meone/');

  await formValidation(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  assert.strictEqual(
      errorEmail.querySelector('.error-message').innerHTML,
      'Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"',
      'Should show 1 error message under user email input element');
});

test('Should validate authentication form with incorrect and too short email structure', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');
  addParameterToInput(form[0], 'email-user', '#one');

  await formValidation(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  const allErrorMessages = errorEmail.querySelectorAll('.error-message');

  const messages = allErrorMessages[0].innerHTML + ';' + allErrorMessages[1].innerHTML;
  assert.strictEqual(allErrorMessages.length, 2, 'Should show 2 error messages under email input element');
  assert.strictEqual(
      messages,
      'Data should be contains a-zA-Z, 0-9 or symbols like "+._@-";Data length should be more than 5 symbols',
      'Should show 2 error messages under user email input element');
});

/!**
 * This function allows to add value to user input line.
 * @param {HTMLElement} form - is element, where necessary search input add value.
 * @param {string} elementId - is id of input element.
 * @param {string} value - is string, which assigned to input.
 *!/
export function addParameterToInput(form, elementId, value) {
  const selector = form.querySelector(`#${elementId}`);
  selector.value = value;
}
*/
