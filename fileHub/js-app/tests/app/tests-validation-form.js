import {validationForm} from '../../app/validation-form.js';

const {module, test} = QUnit;
let fixture;

module('form validation', {
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

test('Should validate authentication form', async (assert) => {
  const form = fixture.getElementsByTagName('form');
  const selector = form[0].querySelector('.get-user-data .input-value input');
  selector.value = 'email-someone';
  await validationForm(form[0]);

  const errorMassages = form[0].getElementsByClassName('error-massage');
  assert.equal(errorMassages.length, 1, 'Should show 1 error messages');
});
