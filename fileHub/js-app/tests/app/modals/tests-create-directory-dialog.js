import {CreateFolderDialog} from '../../../app/modals/create-directory-dialog.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('CreateFolderDialog', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });
  test('Should render dialog window for creating folder', (assert) => {
    new CreateFolderDialog(fixture);

    const fadeForModal = searchElement('dialog', fixture);
    const header = searchElement('modal-header', fixture);
    const submitButton = searchElement('create-new-folder-button', fixture);
    const inputLabel = searchElement('label-name', fixture);
    const inputField = searchElement('input-create-new-folder', fixture);

    assert.ok(fadeForModal, 'Should get fade for modal');
    assert.equal(header.innerText, 'Create New Directory', 'Should render header message');
    assert.equal(submitButton.innerText, 'Create', 'Should render submit button with title');
    assert.equal(inputLabel.innerText, `Enter directory Name`, 'Should label for input');
    assert.equal(inputField.tagName, `INPUT`, 'Should render input tag name');
  });

  test('Should render loading state on button at modal window', (assert) => {
    const window = new CreateFolderDialog(fixture);
    window.creatingInProgress = true;

    const submitButton = searchElement('create-new-folder-button', fixture);
    assert.equal(submitButton.innerHTML, '<span class="glyphicon glyphicon-repeat loading"></span>Create',
        'Should render button to submit creating with loading symbol');
  });

  test('Should render error message at modal window', (assert) => {
    const errorMessage = 'error message';
    const window = new CreateFolderDialog(fixture);
    window.errorMessage = errorMessage;

    const innerTextAtBody = searchElement('error-message', fixture);
    assert.equal(innerTextAtBody.innerText, errorMessage, 'Should render error message');
  });

  test('Should call listener when create folder dialog submitted', (assert) => {
    const value = 'on submit step';
    const window = new CreateFolderDialog(fixture);
    const inputField = searchElement('input-create-new-folder', fixture);

    window.onSubmit((inputName) => {
      assert.equal(inputName, value, 'Should get value of input field');
    });
    inputField.value = value;
    inputField.dispatchEvent(new Event('change'));
    const submitButton = searchElement('create-new-folder-button', fixture);

    submitButton.dispatchEvent(new Event('click'));
  });

  test('Should call listener when create folder dialog closed', (assert) => {
    const onCLoseStep = 'on submit step';
    const window = new CreateFolderDialog(fixture);
    window.onClose(() => assert.step(onCLoseStep));
    const closeButton = searchElement('close-button', fixture);
    closeButton.dispatchEvent(new Event('click'));

    assert.verifySteps([onCLoseStep]);
  });
});
