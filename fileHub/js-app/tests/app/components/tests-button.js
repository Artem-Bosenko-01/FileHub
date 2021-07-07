import {Button} from '../../../app/components/button.js';

const TITLE = 'title';

const {module, test} = QUnit;
let button;

module('Button', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    button = new Button(fixture);
  },
});

test('Should create button', (assert) => {
  assert.ok(button.getElement('button'),
      'Should add 1 button with class button');
});

test('Should create button with title', (assert) => {
  button.buttonTitle = TITLE;

  assert.equal(button.getElement('button').innerHTML, TITLE,
      'Should add inner text to button: ' + TITLE);
});

test('Should adding event listener on button click', (assert) => {
  const STEP = 'This step on event button';

  button.onClick(() => assert.step(STEP));
  button.getElement('button').dispatchEvent(new Event('click'));

  assert.verifySteps([STEP]);
});
