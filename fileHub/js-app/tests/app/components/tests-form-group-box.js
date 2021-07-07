import {FormGroupBox} from '../../../app/components/form-group-box.js';

const TITLE = 'title';
const TYPE = 'password';
const ID = 'id';
const ERROR_MESSAGE = 'error';

const {module, test} = QUnit;
let formGroup;

module('Form group box', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    formGroup = new FormGroupBox(fixture);
  },
});

test('Should create form group', (assert)=>{
  assert.expect(3);
  assert.ok(formGroup.getElement('get-user-data'),
      'Should add 1 div with class get-user-data');
  assert.ok(formGroup.getElement('label-name'),
      'Should add 1 label with class label-name');
  assert.ok(formGroup.getElement('input'),
      'Should add 1 input field');
});

test('Should create form group with id, title and inputType', (assert)=>{
  assert.expect(3);
  formGroup.id = ID;
  formGroup.title = TITLE;
  formGroup.inputType = TYPE;

  assert.equal(formGroup.getElement('input').title, 'Input ' + TITLE,
      'Should add title to label: ' + TITLE);
  assert.equal(formGroup.getElement('input').id, ID,
      'Should add id to input field: ' + ID);
  assert.ok(document.querySelector(`.input-value input[type="${TYPE}"]`),
      'Should add input type to input field: ' + TYPE);
});

test('Should create error message under input field', (assert)=>{
  assert.expect(2);
  formGroup.errorMessage = ERROR_MESSAGE;

  assert.ok(formGroup.getElement(`error-massage`),
      'Should add error message to input-value div. ');
  assert.equal(formGroup.getElement(`error-massage`).innerHTML, ERROR_MESSAGE,
      'Should add error message with message: ' + ERROR_MESSAGE);
});

test('Should ignore creating error message under input field', (assert)=>{
  formGroup.errorMessage = '';

  assert.notOk(formGroup.getElement(`error-massage`),
      'Shouldn\'t add error message to input-value div. ');
});

test('Should expect successful adding event listeners', (assert)=>{
  assert.expect(3);
  formGroup.onChange((message)=>{
    formGroup.errorMessage = message;
  });
  const event = new Event('change');
  const value = 'value';
  formGroup.getElement('input').value = value;
  formGroup.getElement('input').dispatchEvent(event);

  assert.ok(formGroup.getElement(`error-massage`),
      'Should add error message to input-value div. ');
  assert.equal(formGroup.getElement(`error-massage`).innerHTML, value,
      'Should add error message with message: ' + value);
  assert.equal(formGroup.getElement('input').value, value,
      'Should save input value after event.');
});
