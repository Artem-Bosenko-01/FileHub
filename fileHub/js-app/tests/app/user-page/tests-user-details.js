import {UserDetails} from '../../../app/file-list/user-details.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('UserDetails', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should render panel with user data', (assert) => {
    const userFullName = 'Artem Bosenko';

    const userDetails = new UserDetails(fixture);
    userDetails.userFullName = userFullName;

    assert.equal(searchElement('user-full-name', fixture).innerText, userFullName,
        'Should render user full name');
  });

  test('Should render panel with error message', (assert) => {
    const errorMessage = 'Can\'t load user data.';

    const details = new UserDetails(fixture);
    details.errorMessage = 'error';

    assert.equal(searchElement('user-full-name', fixture).innerText, errorMessage,
        'Should render user full name');
  });

  test('Should render panel with loading symbol state', (assert) => {
    const details = new UserDetails(fixture);
    details.loading = true;

    assert.ok(searchElement('loading-symbol', fixture), 'Should render loading symbol');
  });
});
