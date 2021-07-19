import {AuthenticationForm} from '../../../app/login/authentication-form.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('Authentication form', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create authentication form', (assert) => {
    assert.expect(4);
    new AuthenticationForm(fixture);
    const header = searchElement('header').innerHTML;
    assert.equal(header, 'Sign In to FileHub', 'Should check form title');
    const LINK_REF = '#register';
    assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
        'Should check reference link in form');
    const linkMessage = searchElement('link').innerHTML;
    assert.equal(linkMessage, 'Didn\'t have an account yet?', 'Should check link message in form');
    const buttonTitle = searchElement('button').innerHTML;
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
    fixture.querySelector('[data-fh="inputemail-user"]').value = 'email@la';
    fixture.querySelector('[data-fh="inputpassword-user"]').value = '123456';
    form.onSubmit((credentials) => {
      assert.ok(credentials, 'Should get correct credentials after validation.');
      done();
    });
    const component = searchElement('form');
    component.dispatchEvent(new Event('submit'));
  });
});
