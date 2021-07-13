import {Form} from '../../../app/components/form.js';
import {FormInputField} from '../../../app/components/form-input-field.js';

const {module, test} = QUnit;
let fixture;

const HEADER = 'header';
const LINK_REF = 'link';
const LINK_MESSAGE = 'this is link message';

module('Form', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should create form with header, link reference and message, buttonTitle', (assert) => {
  assert.expect(3);
  const form = new Form(fixture);
  form.formHeader = HEADER;
  assert.ok(form.getElement('header'), 'Should create header in form');

  form.linkReference = LINK_REF;
  assert.ok(document.querySelector(`[data-fh="link"][href="${LINK_REF}"]`),
      'Should add reference to link in form');

  form.linkMessage = LINK_MESSAGE;
  assert.equal(form.getElement('link').innerHTML, LINK_MESSAGE, 'Should create header in form');
});

test('Should create form with input field', (assert) => {
  const form = new Form(fixture);
  form.initInputs((container) => {
    const inputField = new FormInputField(container);
    inputField.id = 'id';
  });

  assert.ok(form.getElement('inputid'), 'Should create input field in form');
});

test('Should check event calls in form ', (assert) => {
  const STEP = 'step';
  const form = new Form(fixture);
  form.onSubmit = () => assert.step(STEP);
  form.getElement('form').dispatchEvent(new Event('submit'));
  assert.verifySteps([STEP], 'Should successfully calls event on submit form');
});
