import {FileListBody} from '../../../app/user-page/file-list-body.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('File list body', () => {
  test('Should render file list body', (assert) => {
    assert.expect(3);
    const folderName = 'Folder name';
    const fileListItemTest = {};
    const currentFolder = {
      itemName: folderName,
    };
    const fixture = document.getElementById('qunit-fixture');

    const listBody = new FileListBody(fixture);
    listBody.fileListItems = [fileListItemTest];
    listBody.currentFolder = currentFolder;

    assert.ok(searchElement('file-list-body', fixture), 'Should render tag for file list body');
    const listItems = fixture.querySelectorAll('[data-fh="folder-marker"]');
    assert.equal(listItems.length, 1, 'Should render table with 2 list items');
    assert.equal(searchElement('current-dir', fixture).innerText, folderName,
        'Should add name of current folder');
  });
});
