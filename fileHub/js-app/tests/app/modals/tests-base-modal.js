import {BaseModalWindow} from '../../../app/modals/base-modal.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('BaseModalWindow', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should create modal window with header and inner body text', (assert) => {
    const innerText = 'Inner text';
    const headerText = 'header';

    const modalWindow = new BaseModalWindow(fixture, headerText);
    modalWindow.innerTextForBody = innerText;
    const header = searchElement('modal-header', fixture);
    const body = searchElement('inner-text-body', fixture);

    assert.equal(body.innerText, innerText, 'Should render modal with inner text at body');
    assert.equal(header.innerText, headerText, 'Should render header');
  });

  test('Should create modal window with loading state on submit box', (assert) => {
    const modalWindow = new BaseModalWindow(fixture, 'header');
    modalWindow.isLoading = true;
    const loadingClass = fixture.querySelector(`[class="submit-box submit-modal-box submit-loading-modal-box"]`);

    assert.ok(loadingClass, 'Should get loading class for modal window');
  });

  test('Should create modal window with error message at body', (assert) => {
    const errorMessage = 'error message';

    const modalWindow = new BaseModalWindow(fixture, 'header');
    modalWindow.errorMessage = errorMessage;
    const body = searchElement('inner-text-body', fixture);

    assert.equal(body.innerText, errorMessage, 'Should render modal with error message');
  });

  test('Should call listener when close button clicked', (assert) => {
    const onCloseButtonClickStep = 'click!';

    const modalWindow = new BaseModalWindow(fixture, 'header');
    modalWindow.onCloseButtonCLick(() => assert.step(onCloseButtonClickStep));

    searchElement('close-button', fixture).dispatchEvent(new Event('click'));
    assert.verifySteps([onCloseButtonClickStep]);
  });

  test('Should get argument for initSubmitButton method', (assert) => {
    const modalWindow = new BaseModalWindow(fixture, 'header');
    modalWindow.initSubmitButton((parentElement) => {
      assert.equal(parentElement.getAttribute('data-fh'), 'submit-button');
      return {rootElement: 'id'};
    });
  });

  test('Should call initializer for input field in modal body', (assert) => {
    const modalWindow = new BaseModalWindow(fixture, 'header');
    modalWindow.initFormInputField((parentElement) => {
      assert.equal(parentElement.getAttribute('data-fh'), 'modal-body-initializer');
      return {rootElement: 'id'};
    });
  });
});
