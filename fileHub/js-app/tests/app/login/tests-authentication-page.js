import {AuthenticationPage} from '../../../app/login/authentication-page.js';
import {TitleService} from '../../../app/services/title-service.js';
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
    const titleService = new TitleService('FileHub', testDocument);
    new AuthenticationPage(fixture, testApiService, titleService);

    searchElement('inputemail-user').value = 'emailvvvdvdv';
    searchElement('inputpassword-user').value = '123654987';
    searchElement('form').dispatchEvent(new Event('submit'));

    assert.equal(testDocument.title, 'Authentication - FileHub', 'Should add title to page');
  });
});
