import {searchElement} from '../search-element-function.js';
import {RegistrationPage} from '../../../app/register/registration-page.js';

const {module, test} = QUnit;

module('Registration page', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });
  const testDocument = {
    title: '',
  };

  test('Should add title and api services to registration page', (assert) => {
    assert.expect(2);
    const done = assert.async();

    const testApiService = {
      registration(email, password) {
        assert.ok(true, 'Should be called event on submit.');
        done();
      },
    };

    const testTitleService = {
      addTitleForPage() {
        testDocument.title = 'Registration - FileHub';
      },
    };

    new RegistrationPage(fixture, testApiService, testTitleService);

    const emailField = searchElement('inputemail-user');
    emailField.value = 'email@jajas';
    emailField.dispatchEvent(new Event('change'));

    const passwordField = searchElement('inputpassword-user');
    passwordField.value = '123456';
    passwordField.dispatchEvent(new Event('change'));

    const confirmPasswordField = searchElement('inputconfirm-password-user');
    confirmPasswordField.value = '123456';
    confirmPasswordField.dispatchEvent(new Event('change'));

    searchElement('form').dispatchEvent(new Event('submit'));

    assert.equal(testDocument.title, 'Registration - FileHub', 'Should add title to page');
  });
});
