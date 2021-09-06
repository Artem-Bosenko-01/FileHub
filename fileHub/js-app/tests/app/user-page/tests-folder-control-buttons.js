import {FolderControlButtons} from '../../../app/file-list/folder-control-buttons.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('FolderControlButtons', () => {
  test('Should render two buttons with icons', (assert) => {
    assert.expect(2);
    const fixture = document.getElementById('qunit-fixture');

    new FolderControlButtons(fixture);

    assert.ok(searchElement('upload-button', fixture), 'Should render button for uploading files');
    assert.ok(searchElement('create-new-dir-button', fixture),
        'Should render button for creating a new directory');
  });

  test('Should set loading upload status to upload button', (assert) => {
    const buttonLoadingIcon = `<span class="glyphicon glyphicon-repeat loading"></span>`;
    const fixture = document.getElementById('qunit-fixture');

    const controlButtons = new FolderControlButtons(fixture);
    controlButtons.loadingUploadFile = true;
    const button = searchElement('upload-button', fixture);
    const disabledStatus = button.attributes.item(0).name;

    assert.equal(button.innerHTML, buttonLoadingIcon, 'Should render loading icon');
    assert.equal(disabledStatus, 'disabled', 'Should get disabled button status');
  });

  test('Should add listener on click upload button', (assert) => {
    const fixture = document.getElementById('qunit-fixture');
    const uploadButtonClickStep = 'click upload button';

    const controlButtons = new FolderControlButtons(fixture);
    controlButtons.onUploadButtonClick(() => assert.step(uploadButtonClickStep));
    const button = searchElement('upload-button', fixture);
    button.dispatchEvent(new Event('click'));

    assert.verifySteps([uploadButtonClickStep]);
  });


  test('Should set loading status to create new folder button', (assert) => {
    const buttonLoadingIcon = `<span class="glyphicon glyphicon-repeat loading"></span>`;
    const fixture = document.getElementById('qunit-fixture');

    const controlButtons = new FolderControlButtons(fixture);
    controlButtons.loadingCreateNewFolderFile = true;
    const button = searchElement('create-new-dir-button', fixture);
    const disabledStatus = button.attributes.item(0).name;

    assert.equal(button.innerHTML, buttonLoadingIcon, 'Should render loading icon');
    assert.equal(disabledStatus, 'disabled', 'Should get disabled button status');
  });

  test('Should add listener on click create new folder button', (assert) => {
    const fixture = document.getElementById('qunit-fixture');
    const createFolderButtonClickStep = 'click create new folder button';

    const controlButtons = new FolderControlButtons(fixture);
    controlButtons.onCreateNewFolderButtonClick(() => assert.step(createFolderButtonClickStep));
    const button = searchElement('create-new-dir-button', fixture);
    button.dispatchEvent(new Event('click'));

    assert.verifySteps([createFolderButtonClickStep]);
  });
});
