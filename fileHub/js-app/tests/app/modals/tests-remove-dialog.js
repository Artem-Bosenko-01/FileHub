import {RemoveDialogWindow} from '../../../app/modals/remove-dialog.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('RemoveDialogWindow', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });
  const modelName = 'name';
  const removingModel = {
    name: modelName,
  };

  test('Should render remove dialog window', (assert) => {
    new RemoveDialogWindow(fixture, removingModel);

    const fadeForModal = searchElement('dialog', fixture);
    const header = searchElement('modal-header', fixture);
    const deleteButton = searchElement('delete-button', fixture);
    const message = searchElement('inner-text-body', fixture);

    assert.ok(fadeForModal, 'Should get fade for modal');
    assert.equal(header.innerText, 'Delete Item', 'Should render header message');
    assert.equal(deleteButton.innerText, 'Delete', 'Should render delete button with title');
    assert.equal(message.innerText, `Are you sure want to delete "${modelName}" item?`,
        'Should render message at body');
  });

  test('Should render loading state on button at modal window', (assert) => {
    const window = new RemoveDialogWindow(fixture, removingModel);
    window.deletingInProgress = true;

    const deleteButton = searchElement('delete-button', fixture);
    assert.equal(deleteButton.innerHTML, '<span class="glyphicon glyphicon-repeat loading"></span>Delete',
        'Should render delete button with loading symbol');
  });

  test('Should render error message at modal window', (assert) => {
    const errorMessage = 'error message';
    const window = new RemoveDialogWindow(fixture, removingModel);
    window.errorMessage = errorMessage;

    const innerTextAtBody = searchElement('inner-text-body', fixture);
    assert.equal(innerTextAtBody.innerText, errorMessage, 'Should render error message');
  });

  test('Should call listener when remove dialog submitted', (assert) => {
    const onSubmitStep = 'on submit step';
    const window = new RemoveDialogWindow(fixture, removingModel);
    window.onSubmit(() => assert.step(onSubmitStep));
    const deleteButton = searchElement('delete-button', fixture);
    deleteButton.dispatchEvent(new Event('click'));

    assert.verifySteps([onSubmitStep]);
  });

  test('Should call listener when remove dialog closed', (assert) => {
    const onCLoseStep = 'on submit step';
    const window = new RemoveDialogWindow(fixture, removingModel);
    window.onClose(() => assert.step(onCLoseStep));
    const closeButton = searchElement('close-button', fixture);
    closeButton.dispatchEvent(new Event('click'));

    assert.verifySteps([onCLoseStep]);
  });
});
