import {RegistrationForm} from '../../../app/register/registration-form.js';

const {module, test} = QUnit;

module('Registration form', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(4);
    const form = new RegistrationForm(fixture);
    const header = form.getElement('header').innerHTML;
    assert.equal(header, 'Sign Up to FileHub', 'Should check form title');
    const LINK_REF = '#login';
    assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
        'Should check reference link in form');
    const linkMessage = form.getElement('link').innerHTML;
    assert.equal(linkMessage, 'Already have an account?', 'Should check link message in form');
    const buttonTitle = form.getElement('button').innerHTML;
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
    form.getElement('inputemail-user').value = 'email@la';
    form.getElement('inputpassword-user').value = '123456';
    form.getElement('inputconfirm-password-user').value = '123456';
    form.onSubmit((credentials) => {
      assert.ok(credentials, 'Should get correct credentials after validation.');
      done();
    });
    form.getElement('form').dispatchEvent(new Event('submit'));
  });
});
