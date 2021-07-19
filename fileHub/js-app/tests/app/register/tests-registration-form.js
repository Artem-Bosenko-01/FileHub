import {RegistrationForm} from '../../../app/register/registration-form.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('Registration form', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(4);
    new RegistrationForm(fixture);
    const header = searchElement('header').innerHTML;
    assert.equal(header, 'Sign Up to FileHub', 'Should check form title');
    const LINK_REF = '#login';
    assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
        'Should check reference link in form');
    const linkMessage = searchElement('link').innerHTML;
    assert.equal(linkMessage, 'Already have an account?', 'Should check link message in form');
    const buttonTitle = searchElement('button').innerHTML;
    assert.equal(buttonTitle, 'Sign Up', 'Should check title of button in form');
  });

  test('Should create authentication form with inputs', (assert) => {
    assert.expect(2);
    new RegistrationForm(fixture);
    const labels = document.querySelectorAll(`[data-fh="label-name"]`);
    assert.equal(labels.length, 3, 'Should be 3 labels');

    const inputs = document.querySelectorAll(`[data-fh="get-user-data"] input`);
    assert.equal(inputs.length, 3, 'Should be 3 inputs field');
  });

  test('Should be calls event after successful validation form', (assert) => {
    const done = assert.async();
    const form = new RegistrationForm(fixture);

    const emailField = searchElement('inputemail-user');
    emailField.value = 'email@jajas';
    emailField.dispatchEvent(new Event('change'));

    const passwordField = searchElement('inputpassword-user');
    passwordField.value = '123456';
    passwordField.dispatchEvent(new Event('change'));

    const confirmPasswordField = searchElement('inputconfirm-password-user');
    confirmPasswordField.value = '123456';
    confirmPasswordField.dispatchEvent(new Event('change'));

    form.onSubmit((credentials) => {
      assert.ok(credentials, 'Should get correct credentials after validation.');
      done();
    });
    searchElement('form').dispatchEvent(new Event('submit'));
  });
});
