import {FileListBody} from '../../../app/user-page/file-list-body.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('File list body', () => {
  test('Should render file list body', (assert) => {
    assert.expect(2);
    const fixture = document.getElementById('qunit-fixture');

    new FileListBody(fixture);

    assert.ok(searchElement('file-list-body', fixture), 'Should render tag for file list body');
    const listItems = fixture.querySelectorAll('[data-fh="folder-marker"]');
    assert.equal(listItems.length, 2, 'Should render table with 2 list items');
  });
});
