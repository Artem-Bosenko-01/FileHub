import {
  confirmPasswordValidation,
  emailRegexpValidation,
  lengthValidation,
} from '../../../app/validation/validation-rules.js';
import ValidationError from '../../../app/validation/validation-error.js';

const CORRECT_PASSWORD = '123456';
const INVALID_CONFIRM_PASSWORD = '123654';

const MIN_EMAIL_LENGTH = 5;

const lengthValidationErrorMessage = `Data length should be more than ${MIN_EMAIL_LENGTH} symbols`;
const emailRegexpValidationErrorMessage = 'Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"';
const passwordConfirmValidationErrorMessage = 'Passwords doesn\'t match.';

const {module, test} = QUnit;

module('Negative scenario: Validation rules.', () => {
  test.each(
      'should check email length more than 4 symbols',
      ['ema', '12', '3555'],
      (assert, email) => {
        assert.rejects(lengthValidation(email, MIN_EMAIL_LENGTH), new ValidationError(lengthValidationErrorMessage),
            `Invalid value -> ${email}`);
      });

  test.each(
      'should check email contains only that symbols: a-zA-Z 0-9 +.-_@',
      ['fvs##ds', 'dsc$dmkl', 'as!cas'],
      (assert, email) => {
        assert.rejects(emailRegexpValidation(email), new ValidationError(emailRegexpValidationErrorMessage),
            `Invalid value -> ${email}`);
      });

  test('should check that two passwords are equals',
      (assert) => {
        assert.rejects(confirmPasswordValidation(CORRECT_PASSWORD, INVALID_CONFIRM_PASSWORD),
            new ValidationError(passwordConfirmValidationErrorMessage),
            `${CORRECT_PASSWORD} not equal ${INVALID_CONFIRM_PASSWORD}`);
      });
});

module('Positive scenario: Validation rules', () => {
  test.each(
      'should check email length more than 4 symbols',
      ['emails', 'artem@gmail', 'hmail@com'],
      async (assert, email) => {
        const result = await lengthValidation(email, MIN_EMAIL_LENGTH);
        assert.equal(result, email, `"${result}" is correct email's length.`);
      });

  test.each(
      'should check email contains only that symbols: a-zA-Z 0-9 +.-_@',
      ['email@em', 'ascx.bu-ku', '+dvs546'],
      async (assert, email) => {
        const result = await emailRegexpValidation(email);
        assert.equal(result, email, `"${result}" is correct email.`);
      });

  test('should check that two passwords are equals',
      async (assert) => {
        const result = await confirmPasswordValidation('123456', CORRECT_PASSWORD);
        assert.equal(result, CORRECT_PASSWORD, `Passwords equals`);
      },
  );
});
