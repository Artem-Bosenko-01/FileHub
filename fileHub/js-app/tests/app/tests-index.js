import {
    confirmPasswordValidation,
    emailLengthValidation,
    emailStructureValidation,
    passwordValidation
} from "../../app/validation-rules.js";

const INVALID_STRUCTURE_EMAILS = ['fvs##ds', 'dsc$dmkl', 'as!cas']
const TOO_SHORT_EMAILS = ['ema', '12', '3555']
const INVALID_PASSWORDS = ['1654', 'DAC', 'q']
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


