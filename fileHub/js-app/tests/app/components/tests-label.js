import {Label} from '../../../app/components/label.js';

const TITLE = 'This is label';
const FOR_ID = 'Input';

const {module, test} = QUnit;
let fixture;

module('Test label', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should check create 1 label', (assert) => {
  new Label(fixture);
  assert.strictEqual(fixture.getElementsByClassName('label-name').length, 1,
      'Should create 1 new label');
});

test('Should check create 1 label with inner text', (assert) => {
  const label = new Label(fixture);
  label.title(TITLE);
  assert.strictEqual(fixture.getElementsByClassName('label-name')[0].innerHTML, TITLE,
      'Should create new label with inner text: ' + TITLE);
});

test('Should check create 1 label with title', (assert) => {
  const label = new Label(fixture);
  label.title(TITLE);
  assert.strictEqual(fixture.getElementsByClassName('label-name')[0].title, TITLE,
      'Should create new label with title: ' + TITLE);
});

test('Should check create 1 label with title and id of input simultaneously', (assert) => {
  assert.expect(2);
  const label = new Label(fixture);
  label.title(TITLE);
  label.id(FOR_ID);
  assert.strictEqual(fixture.getElementsByClassName('label-name')[0], FOR_ID,
      'Should create new label with id of input-: ' + FOR_ID);
  assert.strictEqual(fixture.getElementsByClassName('label-name')[0].title, TITLE,
      'Should create new label with title: ' + TITLE);
});
