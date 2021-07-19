import {AuthenticationPage} from '../../../app/login/authentication-page.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('Authentication page', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });
  const testDocument = {
    title: '',
  };

  test('Should add title and api services to authentication page', (assert) => {
    assert.expect(2);
    const done = assert.async();

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

    const emailField = searchElement('inputemail-user');
    emailField.value = 'email@jajas';
    emailField.dispatchEvent(new Event('change'));

    const passwordField = searchElement('inputpassword-user');
    passwordField.value = 'email@123456';
    passwordField.dispatchEvent(new Event('change'));

    searchElement('form').dispatchEvent(new Event('submit'));

    assert.equal(testDocument.title, 'Authentication - FileHub', 'Should add title to page');
  });
});
