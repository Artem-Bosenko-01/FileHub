import {AuthenticationPage} from '../../../app/login/authentication-page.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('AuthenticationForm', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should add title and api services to authentication page', (assert) => {
    assert.expect(2);
    const done = assert.async();

    const testDocument = {title: ''};

    const testApiService = {
      logIn(email, password) {
        assert.ok(true, 'Should be called event on submit.');
        done();
      },
    };

    const testTitleService = {
      addTitleForPage() {
        testDocument.title = 'Authentication - FileHub';
      },
    };

    new AuthenticationPage(fixture, testApiService, testTitleService);

    const emailField = searchElement('input-email-user', fixture);
    emailField.value = 'email@jajas';
    emailField.dispatchEvent(new Event('change'));

    const passwordField = searchElement('input-password-user', fixture);
    passwordField.value = 'email@123456';
    passwordField.dispatchEvent(new Event('change'));

    searchElement('form', fixture).dispatchEvent(new Event('submit'));

    assert.equal(testDocument.title, 'Authentication - FileHub', 'Should add title to page');
  });
});
