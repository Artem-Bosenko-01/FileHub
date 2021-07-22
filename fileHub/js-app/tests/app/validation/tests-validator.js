import {Validator} from '../../../app/validation/validator.js';
import {FormInputField} from '../../../app/components/form-input-field.js';
import {ValidationConfiguration, ValidationRule} from '../../../app/validation/validation-configuration.js';
import {
  confirmPasswordValidation,
  emailRegexpValidation,
  lengthValidation,
} from '../../../app/validation/validation-rules.js';

const {module, test} = QUnit;
let field;
module('Validator', (hooks) => {
  hooks.beforeEach(() => {
    field = new FormInputField(document.getElementById('qunit-fixture'));
  });

  test(`Resolve: Should successfully validate configuration with 1 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ValidationRule(field, () => lengthValidation('length', 6))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.isValid === true);
    assert.equal(successfulResults.length, 1, 'Should be 1 fulfilled promise status.');
  });

  test(`Resolve: Should validate configuration with 3 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ValidationRule(field, () => lengthValidation('length', 6)),
      new ValidationRule(field, () => emailRegexpValidation('validField')),
      new ValidationRule(field, () => confirmPasswordValidation('123456', '123456'))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.isValid === true);
    assert.equal(successfulResults.length, 3, 'Should be 2 fulfilled promise status.');
  });

  test(`Reject: Should validate configuration with 1 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ValidationRule(field, () => lengthValidation('short', 6))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.isValid === false);
    assert.equal(successfulResults.length, 1, 'Should be 1 fulfilled promise status.');
  });

  test(`Reject: Should validate configuration with 3 parameter `, async (assert) => {
    assert.expect(2);
    const conf = new ValidationConfiguration([new ValidationRule(field, () => lengthValidation('short', 6)),
      new ValidationRule(field, () => emailRegexpValidation('val$date')),
      new ValidationRule(field, () => confirmPasswordValidation('123456', '123654'))]);
    const results = await new Validator(conf).validate();
    assert.ok(results, 'Results of validation should be non null or undefined');
    const successfulResults = results.filter((result) => result.isValid === false);
    assert.equal(successfulResults.length, 3, 'Should be 1 fulfilled promise status.');
  });
});
