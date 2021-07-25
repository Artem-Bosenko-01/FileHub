import {FileList} from '../../../app/user-page/file-list.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('File list', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });
  test('Should render file list with files', (assert) => {
    assert.expect(2);
    const testFileItem = {};
    const fileList = new FileList(fixture);
    fileList.fileItems = [testFileItem];

    assert.ok(searchElement('fileListItems', fixture), 'Should create file list');
    assert.ok(searchElement('folder-marker', fixture), 'Should create line in file list');
  });

  test('Should render empty file list with message', (assert) => {
    assert.expect(2);
    const emptyFileListMessage = 'There are no files/directories created yet.';
    const fileList = new FileList(fixture);
    fileList.fileItems = [];

    assert.ok(searchElement('fileListItems', fixture), 'Should create file list');
    assert.equal(searchElement('empty-file-list-message', fixture).innerText, emptyFileListMessage,
        'Should create line in file list');
  });

  test('Should render file list with error message', (assert) => {
    assert.expect(2);
    const errorMessage = 'Can\'t load directory data.';
    new FileList(fixture);

    assert.ok(searchElement('fileListItems', fixture), 'Should create file list');
    assert.equal(searchElement('file-list-error-message', fixture).innerText, errorMessage,
        'Should render error message');
  });
});

