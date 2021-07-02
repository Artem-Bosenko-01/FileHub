import {
    confirmPasswordValidation,
    emailLengthValidation,
    emailStructureValidation,
    passwordValidation
} from "../../app/validation-rules.js";

const INVALID_STRUCTURE_EMAILS = ['fvs##ds', 'dsc$dmkl', 'as!cas']
const CORRECT_STRUCTURE_EMAILS = ['email@em', 'ascx.bu-ku', '+dvs546']
const TOO_SHORT_EMAILS = ['ema', '12', '3555']
const EMAILS_WITH_CORRECT_LENGTH = ['emails', 'artem@gmail', 'hmail@com']
const INVALID_PASSWORDS = ['1654', 'DAC', 'q']
const CORRECT_PASSWORDS = ['123456', 'qwerty6', '123qwer']
const CORRECT_PASSWORD = '123456'
const INVALID_CONFIRM_PASSWORD = '123654'

const {module, test} = QUnit;

module('should fail email length validation', () => {
    test('should check email length more than 4 symbols', (assert) => {

        TOO_SHORT_EMAILS.forEach(
            email => assert.rejects(emailLengthValidation(email), `Invalid value -> ${email}`)
        )
    });
});

module('should fail email structure validation', () => {
    test('should check email contains only that symbols: a-zA-Z 0-9 +.-_@', (assert) => {

        INVALID_STRUCTURE_EMAILS.forEach(
            email => assert.rejects(emailStructureValidation(email), `Invalid value -> ${email}`)
        )
    });
});

module('should fail password structure validation', () => {
    test('should check password length more than 5 symbols', (assert) => {
        INVALID_PASSWORDS.forEach(
            password => assert.rejects(passwordValidation(password), `Invalid value -> ${password}`)
        )
    });
});

module('should fail confirm password validation', () => {
    test('should check that two passwords are equals', (assert) => {
        assert.rejects(confirmPasswordValidation(CORRECT_PASSWORD, INVALID_CONFIRM_PASSWORD),
            `${CORRECT_PASSWORD} not equal ${INVALID_CONFIRM_PASSWORD}`)
    });
});

module('should success email length validation', () => {
    test('should check email length more than 4 symbols', (assert) => {

        EMAILS_WITH_CORRECT_LENGTH.forEach(
            email => emailLengthValidation(email).then(
                (result) => assert.equal(result, email, `"${result}" is correct email's length.`)
            )
        )
    });
});

module('should success email structure validation', () => {
    test('should check email contains only that symbols: a-zA-Z 0-9 +.-_@', (assert) => {

        CORRECT_STRUCTURE_EMAILS.forEach(
            email => emailStructureValidation(email).then(
                (result) => assert.equal(result, email, `"${result}" is correct email.`)
            )
        )
    });
});

module('should success password structure validation', () => {
    test('should check password length more than 5 symbols', (assert) => {
        CORRECT_PASSWORDS.forEach(
            password => passwordValidation(password).then(
                (result) => assert.equal(result, password, `"${result}" is correct password.`)
            )
        )
    });
});

module('should success confirm password validation', () => {
    test('should check that two passwords are equals', (assert) => {
            confirmPasswordValidation('123456', CORRECT_PASSWORD).then(
                (result) => assert.equal(result, CORRECT_PASSWORD, `Passwords equals`))
        }
    )
});
