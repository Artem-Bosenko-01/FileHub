import {AuthenticationForm} from '../../../app/login/authentication-form.js';

const {module, test} = QUnit;

module('Authentication form', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(4);
    const form = new AuthenticationForm(fixture);
    const header = form.getElement('header').innerHTML;
    assert.equal(header, 'Sign In to FileHub', 'Should check form title');
    const LINK_REF = '#register';
    assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
        'Should check reference link in form');
    const linkMessage = form.getElement('link').innerHTML;
    assert.equal(linkMessage, 'Didn\'t have an account yet?', 'Should check link message in form');
    const buttonTitle = form.getElement('button').innerHTML;
    assert.equal(buttonTitle, 'Sign In', 'Should check title of button in form');
  });

  test('Should create authentication form with inputs', (assert) => {
    assert.expect(2);
    new AuthenticationForm(fixture);
    const labels = document.querySelectorAll(`[data-fh="label-name"]`);
    assert.equal(labels.length, 2, 'Should be 2 labels');

    const inputs = document.querySelectorAll(`[data-fh="get-user-data"] input`);
    assert.equal(inputs.length, 2, 'Should be 2 inputs field');
  });

  test('Should be calls event after successful validation form', (assert) => {
    const done = assert.async();
    const form = new AuthenticationForm(fixture);
    form.getElement('inputemail-user').value = 'email@la';
    form.getElement('inputpassword-user').value = '123456';
    form.onSubmit((credentials) => {
      assert.ok(credentials, 'Should get correct credentials after validation.');
      done();
    });
    const component = form.getElement('form');
    component.dispatchEvent(new Event('submit'));
  });
});
