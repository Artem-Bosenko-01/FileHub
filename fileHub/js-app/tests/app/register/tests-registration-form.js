import {RegistrationForm} from '../../../app/register/registration-form.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('RegistrationForm', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(4);
    new RegistrationForm(fixture);
    const header = searchElement('header', fixture).innerHTML;
    assert.equal(header, 'Sign Up to FileHub', 'Should check form title');
    const LINK_REF = '#login';
    assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
        'Should check reference link in form');
    const linkMessage = searchElement('link', fixture).innerHTML;
    assert.equal(linkMessage, 'Already have an account?', 'Should check link message in form');
    const buttonTitle = searchElement('submit-button', fixture).innerHTML;
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

  test('Should call event after successful validation form', (assert) => {
    assert.expect(3);
    const done = assert.async();
    const passwordValue = '123456';
    const emailValue = 'email@jajas';
    const confirmPasswordValue = '123456';

    const form = new RegistrationForm(fixture);
    const emailField = searchElement('input-email-user', fixture);
    const passwordField = searchElement('input-password-user', fixture);
    const confirmPasswordField = searchElement('input-confirm-password-user', fixture);

    emailField.value = emailValue;
    emailField.dispatchEvent(new Event('change'));

    passwordField.value = passwordValue;
    passwordField.dispatchEvent(new Event('change'));

    confirmPasswordField.value = confirmPasswordValue;
    confirmPasswordField.dispatchEvent(new Event('change'));

    form.onSubmit((credentials) => {
      const {email, password} = credentials;
      assert.ok(credentials, 'Should get correct credentials after validation.');
      assert.equal(email, emailValue, 'Should get email.');
      assert.equal(password, passwordValue, 'Should get password.');
      done();
    });
    searchElement('form', fixture).dispatchEvent(new Event('submit'));
  });
});
