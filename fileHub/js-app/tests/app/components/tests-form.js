import {Form} from '../../../app/components/form.js';
import {FormInputField} from '../../../app/components/form-input-field.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;
let fixture;

module('Form', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should create form with header, link reference and message, buttonTitle', (assert) => {
  const header = 'header';
  const linkMessage = 'this is link message';

  assert.expect(2);
  const form = new Form(fixture);
  form.formHeader = header;
  form.linkMessage = linkMessage;

  assert.ok(searchElement('header', fixture), 'Should create header in form');
  assert.equal(searchElement('link', fixture).innerHTML, linkMessage, 'Should create header in form');
});

test('Should create form with input field', (assert) => {
  const form = new Form(fixture);
  form.initInputs((container) => {
    const inputField = new FormInputField(container);
    inputField.id = 'id';
  });

  assert.ok(searchElement('input-id', fixture), 'Should create input field in form');
});

test('Should check listener calls in form ', (assert) => {
  const step = 'step';
  const form = new Form(fixture);
  form.onSubmit(() => assert.step(step));

  searchElement('form', fixture).dispatchEvent(new Event('submit'));

  assert.verifySteps([step], 'Should successfully calls event on submit form');
});

test('Should add action on click link to form', (assert) => {
  const onClickedLinkStep = 'link was clicked';

  assert.expect(2);
  const form = new Form(fixture);
  form.onLinkClick(() => assert.step(onClickedLinkStep));

  const component = searchElement('link', fixture);
  component.dispatchEvent(new Event('click'));
  assert.verifySteps([onClickedLinkStep]);
});
