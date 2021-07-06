import {Button} from '../../../app/components/button.js';

const TITLE = 'Press me';
const INNER_TEXT = 'Sign in';

const {module, test} = QUnit;
let fixture;

module('Test button', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should check create 1 button', (assert) => {
  new Button(fixture);
  assert.strictEqual(fixture.getElementsByClassName('button').length, 1,
      'Should create 1 new button');
});

test('Should check create 1 button with inner text', (assert) => {
  const button = new Button(fixture);
  button.text(INNER_TEXT);
  assert.strictEqual(fixture.getElementsByClassName('button')[0].innerHTML, INNER_TEXT,
      'Should create new button with inner text: ' + INNER_TEXT);
});

test('Should check create 1 button with inner text and icon', (assert) => {
  const button = new Button(fixture);
  const icon = '<span class="glyphicon glyphicon-repeat loading"></span>';
  button.text(INNER_TEXT + icon);
  assert.strictEqual(fixture.getElementsByClassName('button')[0].innerHTML, INNER_TEXT + icon,
      'Should create new button with inner text and icon: ' + icon);
});

test('Should check create 1 button with title', (assert) => {
  const button = new Button(fixture);
  button.title(TITLE);
  assert.strictEqual(fixture.getElementsByClassName('button')[0].title, TITLE,
      'Should create new button with title: ' + TITLE);
});

test('Should check create 1 button with title and inner text simultaneously', (assert) => {
  assert.expect(2);
  const button = new Button(fixture);
  button.title(TITLE);
  button.text(INNER_TEXT);
  assert.strictEqual(fixture.getElementsByClassName('button')[0].innerHTML, INNER_TEXT,
      'Should create new button with inner text-: ' + INNER_TEXT);
  assert.strictEqual(fixture.getElementsByClassName('button')[0].title, TITLE,
      'Should create new button with title: ' + TITLE);
});
