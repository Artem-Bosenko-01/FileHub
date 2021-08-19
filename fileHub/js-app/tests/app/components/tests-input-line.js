import {InputLine} from '../../../app/components/input-line.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('InputLine', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should render input line component', (assert) => {
    new InputLine(fixture);

    const inputLine = searchElement('input-line', fixture);
    assert.ok(inputLine, 'Should render input line');
  });

  test('Should render loading symbol near input line', (assert) => {
    const inputLine = new InputLine(fixture);

    inputLine.isLoading = true;

    const loadingSymbol = searchElement('loading-symbol', fixture);
    assert.ok(loadingSymbol, 'Should render loading symbol');
  });

  test('Should render error message under input line', (assert) => {
    const errorMessage = 'error message';
    const inputLine = new InputLine(fixture);

    inputLine.errorMessage = errorMessage;

    const errorMessageElement = searchElement('error-message', fixture);
    assert.ok(errorMessageElement, 'Should render render message');
    assert.equal(errorMessageElement.innerText, errorMessage, 'Should get error message');
  });

  test('Should set value to input line', (assert) => {
    const value = 'value';
    const inputLine = new InputLine(fixture);

    inputLine.value = value;

    const inputLineElement = searchElement('input-line', fixture);
    assert.equal(inputLineElement.value, value, 'Should set value to input');
  });

  test('Should add and call listener on submit input line', (assert) => {
    const onSubmitStep = 'submitted!';
    const inputLine = new InputLine(fixture);

    inputLine.onSubmit(() => assert.step(onSubmitStep));

    const inputLineElement = searchElement('input-line', fixture);
    inputLineElement.dispatchEvent(new KeyboardEvent('keyup', {key: 'Enter'}));

    assert.verifySteps([onSubmitStep]);
  });
});
