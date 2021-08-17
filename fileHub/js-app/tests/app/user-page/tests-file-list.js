import {FileList} from '../../../app/file-list/file-list.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('FileList', (hooks) => {
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
    const list = new FileList(fixture);
    list.errorMessage = 'error';

    assert.ok(searchElement('fileListItems', fixture), 'Should create file list');
    assert.equal(searchElement('file-list-error-message', fixture).innerText, errorMessage,
        'Should render error message');
  });

  test('Should render file list with loading state', (assert) => {
    assert.expect(2);
    const list = new FileList(fixture);
    list.loading = true;

    assert.ok(searchElement('fileListItems', fixture), 'Should create file list');
    assert.ok(searchElement('loading-symbol', fixture), 'Should render loading state symbol');
  });

  test('Should call navigate listener at file list', (assert) => {
    const currentDirectoryName = 'Directory';
    const id = 'id';
    const list = new FileList(fixture);
    list.fileItems = [{
      id: id,
      name: currentDirectoryName,
      type: 'folder',
      itemsAmount: 4,
    }];
    list.onFolderDoubleCLicked((folderId) => {
      assert.equal(folderId, id, 'Should get folder id to navigate event');
    });

    const folderName = searchElement('folder-name', fixture);
    folderName.dispatchEvent(new Event('click'));
  });
});

