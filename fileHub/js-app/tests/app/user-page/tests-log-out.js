import {LogOut} from '../../../app/user-page/log-out.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('LogOut', () => {
  test('Should render link with message: \'log out\'', (assert)=>{
    assert.expect(2);
    const fixture = document.getElementById('qunit-fixture');

    new LogOut(fixture);

    const link = searchElement('log-out-ref', fixture);
    assert.ok(link, 'Should render link');
    assert.equal(link.innerText, 'Log out', 'Should render message in link');
  });
});
