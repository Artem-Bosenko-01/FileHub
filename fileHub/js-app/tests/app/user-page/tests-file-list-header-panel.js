import {searchElement} from '../search-element-function.js';
import {FileListHeaderPanel} from '../../../app/user-page/file-list-header-panel.js';

const {module, test} = QUnit;

module('User panel at header', () => {
  test('Should render user panel', (assert) => {
    assert.expect(2);
    const userFullName = 'Artem Bosenko';
    const fixture = document.getElementById('qunit-fixture');

    const panel = new FileListHeaderPanel(fixture);
    panel.userFullName = userFullName;

    assert.ok(searchElement('user-panel', fixture), 'Should render user panel at header');
    assert.equal(searchElement('user-full-name', fixture).innerText, userFullName,
        'Should render user full name');
  });
});
