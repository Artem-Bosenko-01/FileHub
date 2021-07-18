import {Button} from '../../../app/components/button.js';

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
  const title = 'title';
  button.buttonTitle = title;

  assert.equal(button.getElement('button').innerHTML, title,
      'Should add inner text to button: ' + title);
});

test('Should adding event listener on button click', (assert) => {
  const step = 'This step on event button';
  assert.expect(2);
  button.onClick(() => assert.step(step));
  button.getElement('button').dispatchEvent(new Event('click'));

  assert.verifySteps([step]);
});
