import {validationForm} from '../../app/validation-form.js';

const {module, test} = QUnit;
let fixture;

module('authentication form validation', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
    fixture.innerHTML = '<form id="form" class="data">\n' +
'            <div id="user-email-box" class="get-user-data">\n' +
'                <div class="label-name">\n' +
'                    <label title="Email" for="email-user">Email</label>\n' +
'                </div>\n' +
'                <div class="input-value">\n' +
'                    <input title="Input email" type="text" id="email-user" placeholder="Email">\n' +
'                </div>\n' +
'            </div>\n' +
'            <div id="user-password-box" class="get-user-data">\n' +
'                <div class="label-name">\n' +
'                    <label title="Password" for="password-user">Password</label>\n' +
'                </div>\n' +
'                <div class="input-value">\n' +
'                    <input title="Input password" type="password" id="password-user" placeholder="Password">\n' +
'                </div>\n' +
'            </div>\n' +
'            <div class="submit-box">\n' +
'                <button id="button" title="Submit" class="button" name="signIn" type="button">Sign In</button>\n' +
'                <a title="Registration" class="reference" href="registration.html">Don\'t have an account yet?</a>\n' +
'            </div>\n' +
'        </form>';
  },
});

test('Should validate authentication form with 2 empty inputs.', async (assert) => {
  const form = fixture.getElementsByTagName('form');

  await validationForm(form[0]);

  const errorMassages = form[0].getElementsByClassName('error-massage');
  assert.equal(errorMassages.length, 2, 'Should show 2 error messages');
});

test('Should validate authentication form with empty password', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'email-user', 'email-someone');

  await validationForm(form[0]);

  const errorPassword = form[0].querySelector('#user-password-box');
  assert.strictEqual(
      errorPassword.querySelector('.error-massage').innerHTML,
      'Data length should be more than 6 symbols',
      'Should show 1 error message under user password input element');
});

test('Should validate authentication form with empty email', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');

  await validationForm(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  assert.strictEqual(
      errorEmail.querySelector('.error-massage').innerHTML,
      'Data length should be more than 5 symbols',
      'Should show 1 error message under user email input element');
});

test('Should validate authentication form with incorrect email structure', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');
  addParameterToInput(form[0], 'email-user', 'email-so#meone/');

  await validationForm(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  assert.strictEqual(
      errorEmail.querySelector('.error-massage').innerHTML,
      'Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"',
      'Should show 1 error message under user email input element');
});

test('Should validate authentication form with incorrect and too short email structure', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  addParameterToInput(form[0], 'password-user', 'password123');
  addParameterToInput(form[0], 'email-user', '#one');

  await validationForm(form[0]);

  const errorEmail = form[0].querySelector('#user-email-box');
  const allErrorMessages = errorEmail.querySelectorAll('.error-massage');

  const messages = allErrorMessages[0].innerHTML + ';' + allErrorMessages[1].innerHTML;
  assert.strictEqual(allErrorMessages.length, 2, 'Should show 2 error messages under email input element');
  assert.strictEqual(
      messages,
      'Data length should be more than 5 symbols;Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"',
      'Should show 2 error messages under user email input element');
});

/**
 * This function allows to add value to user input line.
 * @param {HTMLElement} form - is element, where necessary search input add value.
 * @param {string} elementId - is
 * @param {string} value
 */
export function addParameterToInput(form, elementId, value) {
  const selector = form.querySelector(`#${elementId}`);
  selector.value = value;
}
