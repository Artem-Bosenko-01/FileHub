import {Validator} from '../../../app/validation/validator.js';
import {FormInputField} from '../../../app/components/form-input-field.js';
import {ParameterConfiguration, ValidationConfiguration} from '../../../app/validation/validation-configuration.js';
import {
  confirmPasswordValidation,
  lengthValidation,
  structureValidation,
} from '../../../app/validation/validation-rules.js';

const {module, test} = QUnit;
let field;
module('Validator', (hooks) => {
  hooks.beforeEach(() => {
    field = new FormInputField(document.getElementById('qunit-fixture'));
  });

  test(`Resolve: Should successfully validate configuration with 1 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ParameterConfiguration(lengthValidation(field, 'length', 6))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.status === 'fulfilled');
    assert.equal(successfulResults.length, 1, 'Should be 1 fulfilled promise status.');
  });

  test(`Resolve: Should validate configuration with 3 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ParameterConfiguration(lengthValidation(field, 'length', 6)),
      new ParameterConfiguration(structureValidation(field, 'validField')),
      new ParameterConfiguration(confirmPasswordValidation(field, '123456', '123456'))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.status === 'fulfilled');
    assert.equal(successfulResults.length, 3, 'Should be 2 fulfilled promise status.');
  });

  test(`Reject: Should validate configuration with 1 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ParameterConfiguration(lengthValidation(field, 'short', 6))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.status === 'rejected');
    assert.equal(successfulResults.length, 1, 'Should be 1 fulfilled promise status.');
  });

  test(`Reject: Should validate configuration with 3 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ParameterConfiguration(lengthValidation(field, 'short', 6)),
      new ParameterConfiguration(structureValidation(field, 'val$date')),
      new ParameterConfiguration(confirmPasswordValidation(field, '123456', '123654'))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.status === 'rejected');
    assert.equal(successfulResults.length, 3, 'Should be 1 fulfilled promise status.');
  });
});
