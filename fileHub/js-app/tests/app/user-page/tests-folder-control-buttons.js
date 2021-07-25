import {FolderControlButtons} from '../../../app/user-page/folder-control-buttons.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('Folder control buttons', () => {
  test('Should render two buttons with icons', (assert) => {
    assert.expect(2);
    const fixture = document.getElementById('qunit-fixture');

    new FolderControlButtons(fixture);

    assert.ok(searchElement('upload-button', fixture), 'Should render button for uploading files');
    assert.ok(searchElement('create-new-dir-button', fixture),
        'Should render button for creating a new directory');
  });
});
