import {FileListItemView} from '../../../app/file-list/file-list-item-view/file-list-item-view.js';
import {FileListItem} from '../../../app/file-list-item.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

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
    const mimeType = 'pdf';
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
});
