import {FormGroupBox} from '../../../app/components/form-group-box.js';

const TITLE = 'title';
const TYPE = 'password';
const ID = 'id';
const ERROR_MESSAGE = 'error';

const {module, test} = QUnit;
let formGroup;

module('Test form group box', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    formGroup = new FormGroupBox(fixture);
  },
});

test('Should create form group', (assert)=>{
  assert.expect(3);
  assert.equal(document.getElementsByClassName('get-user-data').length, 1,
      'Should add 1 div with class get-user-data');
  assert.equal(document.getElementsByClassName('label-name').length, 1,
      'Should add 1 label with class label-name');
  assert.equal(document.querySelectorAll('.input-value input').length, 1,
      'Should add 1 input field');
});

test('Should create form group with id, title and inputType', (assert)=>{
  assert.expect(3);
  formGroup.id = ID;
  formGroup.title = TITLE;
  formGroup.inputType = TYPE;

  assert.equal(document.querySelector('.label-name label').title, TITLE,
      'Should add title to label: ' + TITLE);
  assert.equal(document.querySelector('.input-value input').id, ID,
      'Should add id to input field: ' + ID);
  assert.ok(document.querySelector(`.input-value input[type="${TYPE}"]`),
      'Should add input type to input field: ' + TYPE);
});

test('Should create error message under input field', (assert)=>{
  assert.expect(2);
  formGroup.errorMessage = ERROR_MESSAGE;

  assert.ok(document.querySelector(`.input-value .error-massage`),
      'Should add error message to input-value div. ');
  assert.equal(document.querySelector(`.input-value .error-massage`).innerHTML, ERROR_MESSAGE,
      'Should add error message with message: ' + ERROR_MESSAGE);
});

test('Should ignore creating error message under input field', (assert)=>{
  formGroup.errorMessage = '';

  assert.notOk(document.querySelector(`.input-value .error-massage`),
      'Shouldn\'t add error message to input-value div. ');
});

test('Should expect successful adding event listeners', (assert)=>{
  assert.expect(3);
  formGroup.onChange((message)=>{
    formGroup.errorMessage = message;
  });
  const event = new Event('change');
  const value = 'value';
  document.querySelector('.input-value input').value = value;
  document.querySelector('.input-value input').dispatchEvent(event);

  assert.ok(document.querySelector(`.input-value .error-massage`),
      'Should add error message to input-value div. ');
  assert.equal(document.querySelector(`.input-value .error-massage`).innerHTML, value,
      'Should add error message with message: ' + value);
  assert.equal(document.querySelector('.input-value input').value, value,
      'Should save input value after event.');
});
