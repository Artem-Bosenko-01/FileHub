import {FileListItemView} from '../../../app/file-list/file-list-item-view/file-list-item-view.js';
import {FileListItem} from '../../../app/file-list-item.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;
const LOADING_SYMBOL = '<span class="glyphicon glyphicon-repeat loading"></span>';

module('FileListItemView', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should render folder at file list', (assert) => {
    assert.expect(6);
    const itemJson = {
      id: 'id',
      type: 'folder',
      name: 'folder',
      itemsAmount: 5,
    };
    const listItem = new FileListItem(itemJson);

    new FileListItemView(fixture, listItem);
    assert.ok(searchElement('folder-marker', fixture).innerHTML,
        'Should render special icon for folder marker');
    assert.ok(fixture.querySelector('[class="glyphicon glyphicon-folder-close"]'),
        'Should render special icon for folder');
    assert.equal(searchElement('folder-name', fixture).innerText, 'folder',
        'Should render folder name');
    assert.equal(searchElement('type', fixture).innerText, 'Folder',
        'Should render type: \'Folder\'');
    assert.equal(searchElement('size', fixture).innerText, 5,
        'Should render amount of inner items in folder');
    assert.ok(searchElement('upload-button', fixture), 'Should add upload button for folder item');
  });

  test('Should render file at file list', (assert) => {
    assert.expect(6);
    const convertedMimeType = 'Pdf Document';
    const convertedSize = '64 KB';
    const size = 65489;
    const mimeType = 'application/pdf';
    const name = 'file';

    const itemJson = {
      id: 'id',
      type: 'file',
      name: name,
      size: size,
      mimeType: mimeType,
    };
    const item = new FileListItem(itemJson);
    new FileListItemView(fixture, item);

    assert.notOk(searchElement('folder-marker', fixture).innerHTML,
        'Shouldn\'t render special icon for folder marker');
    assert.ok(fixture.querySelector('[class="glyphicon glyphicon-book"]'),
        'Should render special icon file using it mime type');
    assert.equal(searchElement('name', fixture).innerText, name,
        'Should render file name');
    assert.equal(searchElement('type', fixture).innerText, convertedMimeType,
        'Should render type using file mime type');
    assert.equal(searchElement('size', fixture).innerText, convertedSize,
        'Should render file size');
    assert.ok(searchElement('download-button', fixture), 'Should add download button for file item');
  });

  test('Should add and call listener on delete button click', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'file',
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.onDeleteButtonClick((listItem)=> assert.deepEqual(item, listItem,
        'Should get item on click'));
    const deleteButton = searchElement('delete-button', fixture);
    deleteButton.click();
  });

  test('Should add and call listener on folder name click', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'folder',
      itemsAmount: 54,
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.onFolderNameDoubleCLicked((itemId)=> assert.deepEqual(item.id, itemId,
        'Should get item id on click'));
    const folderNameLink = searchElement('folder-name', fixture);
    folderNameLink.dispatchEvent(new Event('dblclick'));
  });

  test('Should add and call listener on upload button click', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'folder',
      itemsAmount: 54,
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.onUploadButtonClick((listItem)=> assert.deepEqual(item, listItem,
        'Should get item on click'));
    const uploadButton = searchElement('upload-button', fixture);
    uploadButton.click();
  });

  test('Should set loading status to upload button', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'folder',
      itemsAmount: 54,
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.isLoadingUploadFile = true;
    const uploadButton = searchElement('upload-button', fixture);
    assert.equal(uploadButton.innerHTML, LOADING_SYMBOL, 'Should render loading symbol');
  });

  test('Should add and call listener on download button click', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'file',
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.onDownloadButtonClick((listItem)=> assert.deepEqual(item, listItem,
        'Should get item on click'));
    const downloadButton = searchElement('download-button', fixture);
    downloadButton.click();
  });

  test('Should set loading status to download button', (assert) => {
    const itemJson = {
      id: 'id',
      type: 'file',
    };
    const item = new FileListItem(itemJson);
    const itemView = new FileListItemView(fixture, item);

    itemView.isLoadingDownloadFile = true;
    const downloadButton = searchElement('download-button', fixture);
    assert.equal(downloadButton.innerHTML, LOADING_SYMBOL, 'Should render loading symbol');
  });
});
