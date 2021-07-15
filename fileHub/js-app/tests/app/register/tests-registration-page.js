import {TitleService} from '../../../app/services/title-service.js';
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
    const titleService = new TitleService('FileHub', testDocument);
    const page = new RegistrationPage(fixture, testApiService, titleService);

    page.getElement('inputemail-user').value = 'emailvvvdvdv';
    page.getElement('inputpassword-user').value = '123654987';
    page.getElement('inputconfirm-password-user').value = '123654987';
    page.getElement('form').dispatchEvent(new Event('submit'));

    assert.equal(testDocument.title, 'Registration - FileHub', 'Should add title to page');
  });
});
