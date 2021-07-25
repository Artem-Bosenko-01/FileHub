import {FileListItemView} from '../../../app/user-page/file-list-item-view.js';
import {FileListItem} from '../../../app/user-page/services/file-list-item.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('File list item view', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should render folder at file list', (assert) => {
    assert.expect(6);
    const itemView = new FileListItemView(fixture);
    const item = new FileListItem();
    item.itemId = 'id';
    item.itemType = 'folder';
    item.itemName = 'folder';
    item.itemsAmount = 5;
    itemView.listItemFromDto = item;

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
    const convertedSize = '63.954 KB';
    const size = 65489;
    const mimeType = 'pdf';

    const itemView = new FileListItemView(fixture);
    const item = new FileListItem();
    item.itemId = 'id';
    item.itemType = 'file';
    const name = 'file';
    item.itemName = name;
    item.itemMimeType = mimeType;
    item.itemSize = size;
    itemView.listItemFromDto = item;

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
});
