import {AuthenticationForm} from '../../../app/login/authentication-form.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('AuthenticationForm', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(3);
    new AuthenticationForm(fixture);
    const header = searchElement('header', fixture).innerHTML;
    const linkMessage = searchElement('link', fixture).innerHTML;
    const buttonTitle = searchElement('submit-button', fixture).innerHTML;

    assert.equal(header, 'Sign In to FileHub', 'Should check form title');
    assert.equal(linkMessage, 'Didn\'t have an account yet?', 'Should check link message in form');
    assert.equal(buttonTitle, 'Sign In', 'Should check title of button in form');
  });

  test('Should create authentication form with inputs', (assert) => {
    assert.expect(2);
    new AuthenticationForm(fixture);
    const labels = document.querySelectorAll(`[data-fh="label-name"]`);
    const inputs = document.querySelectorAll(`[data-fh="get-user-data"] input`);

    assert.equal(labels.length, 2, 'Should be 2 labels');
    assert.equal(inputs.length, 2, 'Should be 2 inputs field');
  });

  test('Should call event after successful validation form', (assert) => {
    assert.expect(3);
    const done = assert.async();
    const form = new AuthenticationForm(fixture);
    const emailField = searchElement('input-email-user', fixture);
    const passwordField = searchElement('input-password-user', fixture);
    const emailValue = 'email@jajas';
    const passwordValue = 'email@123456';
    emailField.value = emailValue;
    emailField.dispatchEvent(new Event('change'));
    passwordField.value = passwordValue;
    passwordField.dispatchEvent(new Event('change'));

    form.onSubmit((credentials) => {
      const {email, password} = credentials;
      assert.ok(credentials, 'Should get correct credentials after validation.');
      assert.equal(email, emailValue, 'Should get email.');
      assert.equal(password, passwordValue, 'Should get password.');
      done();
    });

    const component = searchElement('form', fixture);
    component.dispatchEvent(new Event('submit'));
  });

  test('Should call event redirect to register page', (assert) => {
    const onClickLinkStep = 'Link was clicked';
    const form = new AuthenticationForm(fixture);
    form.onNavigateByLink(() => {
      assert.step(onClickLinkStep);
    });

    const component = searchElement('link', fixture);
    component.dispatchEvent(new Event('click'));
    assert.verifySteps([onClickLinkStep]);
  });
});
