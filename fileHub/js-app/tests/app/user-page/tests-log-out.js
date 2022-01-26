import {LogOut} from '../../../app/file-list/log-out.js';
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

  test('Should add and call listener on log out link click', (assert)=>{
    const fixture = document.getElementById('qunit-fixture');
    const logOutStep = 'log out';

    const logOut = new LogOut(fixture);
    logOut.onClick(()=> assert.step(logOutStep));

    const link = searchElement('log-out-ref', fixture);
    link.click();
    assert.verifySteps([logOutStep]);
  });
});
