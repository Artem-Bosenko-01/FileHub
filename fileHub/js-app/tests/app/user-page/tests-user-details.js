import {UserDetails} from '../../../app/user-page/user-details.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('User details', (hooks) => {
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

    new UserDetails(fixture);

    assert.equal(searchElement('user-full-name', fixture).innerText, errorMessage,
        'Should render user full name');
  });
});
