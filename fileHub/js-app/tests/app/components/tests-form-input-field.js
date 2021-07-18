import {FormInputField} from '../../../app/components/form-input-field.js';

const TITLE = 'title';
const TYPE = 'password';
const ID = 'id';
const ERROR_MESSAGE = 'error';

const {module, test} = QUnit;
let inputField;

module('Form input field', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    inputField = new FormInputField(fixture);
  },
});

test('Should create form input field', (assert) => {
  assert.expect(3);
  assert.ok(inputField.getElement('get-user-data'),
      'Should add 1 div with class get-user-data');
  assert.ok(inputField.getElement('label-name'),
      'Should add 1 label with class label-name');
  assert.ok(inputField.getElement('inputundefined'),
      'Should add 1 input field');
});


test('Should create form group with id, title and inputType', (assert) => {
  assert.expect(3);
  inputField.id = ID;
  inputField.title = TITLE;
  inputField.inputType = TYPE;

  assert.equal(inputField.getElement(`input${ID}`).title, TITLE,
      'Should add title to label: ' + TITLE);
  assert.equal(inputField.getElement(`input${ID}`).id, ID,
      'Should add id to input field: ' + ID);
  assert.ok(document.querySelector(`.input-value input[type="${TYPE}"]`),
      'Should add input type to input field: ' + TYPE);
});

test('Should create error message under input field', (assert) => {
  assert.expect(2);
  inputField.errorMessage = ERROR_MESSAGE;

  assert.ok(inputField.getElement(`error-message`),
      'Should add error message to input-value div. ');
  assert.equal(inputField.getElement(`error-message`).innerHTML, ERROR_MESSAGE,
      'Should add error message with message: ' + ERROR_MESSAGE);
});

test('Should ignore creating error message under input field', (assert) => {
  inputField.errorMessage = '';

  assert.notOk(inputField.getElement(`error-message`),
      'Shouldn\'t add error message to input-value div. ');
});

test('Should call change event listener', (assert) => {
  assert.expect(2);
  const step = 'This step on event change input value';
  inputField.id = ID;
  inputField.onChange((message) => {
    assert.step(step);
  });
  const event = new Event('change');
  inputField.getElement(`input${ID}`).dispatchEvent(event);
  assert.verifySteps([step]);
});

