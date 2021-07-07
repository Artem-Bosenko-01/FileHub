import {SubmitBox} from '../../../app/components/submit-box.js';

const TITLE = 'title';
const REFERENCE = 'inner.html';
const MESSAGE = 'message';

const {module, test} = QUnit;
let submitBox;

module('Test submit box', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    submitBox = new SubmitBox(fixture);
  },
});
test('Should create submit box', (assert)=>{
  assert.expect(3);
  assert.equal(document.getElementsByClassName('submit-box').length, 1,
      'Should add 1 div with class submit-box');
  assert.equal(document.getElementsByClassName('button').length, 1,
      'Should add 1 button with class button');
  assert.equal(document.getElementsByClassName('reference').length, 1,
      'Should add 1 link with class reference');
});

test('Should create submit box with button title and link with action and message', (assert)=>{
  assert.expect(3);
  submitBox.buttonTitle = TITLE;
  submitBox.linkReference = REFERENCE;
  submitBox.linkMessage = MESSAGE;

  assert.equal(document.querySelector('.button').innerHTML, TITLE,
      'Should add inner text to button: ' + TITLE);
  assert.ok(document.querySelector(`a[href="${REFERENCE}"]`),
      'Should add action to link: ' + REFERENCE);
  assert.equal(document.querySelector(`.reference`).innerHTML, MESSAGE,
      'Should add inner message to  link: ' + MESSAGE);
});

/* test('Should adding event listener on button click', (assert)=>{
  submitBox.onClick(()=> document.createElement('form').classList.add('test'));
  const event = new Event('click');
  document.querySelector('.button').dispatchEvent(event);

  assert.ok(document.querySelector(`h6`),
      'Should add action to link: ' + REFERENCE);
});*/
