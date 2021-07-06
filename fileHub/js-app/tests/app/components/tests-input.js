import {Input} from '../../../app/components/input.js';

const TITLE = 'value';
const TEXT = 'input field';

const {module, test} = QUnit;
let fixture;

module('Test input field', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should check create 1 input field', (assert) => {
  new Input(fixture);
  assert.strictEqual(fixture.querySelectorAll('.input-value input').length, 1,
      'Should create 1 new input field');
});

test('Should check create 1 input field with title', (assert) => {
  const input = new Input(fixture);
  input.title(TITLE);
  assert.strictEqual(fixture.querySelector('.input-value input').title, 'Input ' + TITLE,
      'Should create new input field with inner text: ' + 'Input ' + TITLE);
});

test('Should check create 1 input field with id', (assert) => {
  const input = new Input(fixture);
  input.id(TITLE);
  assert.strictEqual(fixture.querySelector('.input-value input').id, TITLE,
      'Should create new input field with id: ' + TITLE);
});

test('Should check create 1 input field with placeholder', (assert) => {
  const input = new Input(fixture);
  input.placeholder(TEXT);
  assert.strictEqual(fixture.querySelector('.input-value input').placeholder, TEXT,
      'Should create new input field with placeholder: ' + TEXT);
});

test('Should check create 1 input field with value', (assert) => {
  new Input(fixture);
  fixture.querySelector('.input-value input').value = TEXT;
  assert.strictEqual(fixture.querySelector('.input-value input').value, TEXT,
      'Should create new input field with value: ' + TEXT);
});

test('Should check create 1 input field with id, title and placeholder', (assert) => {
  assert.expect(3);
  const input = new Input(fixture);
  input.id(TITLE);
  assert.strictEqual(fixture.querySelector('.input-value input').id, TITLE,
      'Should create new input field with id: ' + TITLE);

  input.placeholder(TEXT);
  assert.strictEqual(fixture.querySelector('.input-value input').placeholder, TEXT,
      'Should create new input field with placeholder: ' + TEXT);
  input.title(TITLE);
  assert.strictEqual(fixture.querySelector('.input-value input').title, 'Input ' + TITLE,
      'Should create new input field with inner text: ' + 'Input ' + TITLE);
});
