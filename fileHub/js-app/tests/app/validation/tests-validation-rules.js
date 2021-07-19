import {
  confirmPasswordValidation,
  lengthValidation,
  emailRegexpValidation,
} from '../../../app/validation/validation-rules.js';

const CORRECT_PASSWORD = '123456';
const INVALID_CONFIRM_PASSWORD = '123654';

const MIN_EMAIL_LENGTH = 5;
const MIN_PASSWORD_LENGTH = 6;

const {module, test} = QUnit;

module('Negative scenery: Validation rules.', () => {
  test.each(
      'should check email length more than 4 symbols',
      ['ema', '12', '3555'],
      (assert, email) => {
        assert.rejects(lengthValidation(email, MIN_EMAIL_LENGTH), `Invalid value -> ${email}`);
      });

  test.each(
      'should check email contains only that symbols: a-zA-Z 0-9 +.-_@',
      ['fvs##ds', 'dsc$dmkl', 'as!cas'],
      (assert, email) => {
        assert.rejects(emailRegexpValidation(email), `Invalid value -> ${email}`);
      });

  test.each(
      'should check password length more than 5 symbols',
      ['1654', 'DAC', 'q'],
      (assert, password) => {
        assert.rejects(lengthValidation(password, MIN_PASSWORD_LENGTH), `Invalid value -> ${password}`);
      });

  test('should check that two passwords are equals',
      (assert) => {
        assert.rejects(confirmPasswordValidation(CORRECT_PASSWORD, INVALID_CONFIRM_PASSWORD),
            `${CORRECT_PASSWORD} not equal ${INVALID_CONFIRM_PASSWORD}`);
      });
});

module('Positive scenery: Validation rules', () => {
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

  test.each(
      'should check password length more than 5 symbols',
      ['123456', 'qwerty6', '123qwer'],
      async (assert, password) => {
        const result = await lengthValidation(password, MIN_PASSWORD_LENGTH);
        assert.equal(result, password, `"${result}" is correct password.`);
      });

  test('should check that two passwords are equals',
      async (assert) => {
        const result = await confirmPasswordValidation('123456', CORRECT_PASSWORD);
        assert.equal(result, CORRECT_PASSWORD, `Passwords equals`);
      },
  );
});
