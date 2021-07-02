import {
    confirmPasswordValidation,
    emailLengthValidation,
    emailStructureValidation,
    passwordValidation
} from "../../app/validation-rules.js";

const CORRECT_PASSWORD = '123456'
const INVALID_CONFIRM_PASSWORD = '123654'

const {module, test} = QUnit;

/*
* Negative scenery tests.
* */


module('should fail email length validation', () => {
    test.each(
        'should check email length more than 4 symbols',
        ['ema', '12', '3555'], (assert, email) => {
            assert.rejects(emailLengthValidation(email), `Invalid value -> ${email}`)
        });
});

module('should fail email structure validation', () => {
    test.each(
        'should check email contains only that symbols: a-zA-Z 0-9 +.-_@',
        ['fvs##ds', 'dsc$dmkl', 'as!cas'],

        (assert, email) => {
            assert.rejects(emailStructureValidation(email), `Invalid value -> ${email}`)
        });
});

module('should fail password structure validation', () => {
    test.each(
        'should check password length more than 5 symbols',
        ['1654', 'DAC', 'q'],
        (assert, password) => {
            assert.rejects(passwordValidation(password), `Invalid value -> ${password}`)
        });
});

module('should fail confirm password validation', () => {
    test('should check that two passwords are equals', (assert) => {
        assert.rejects(confirmPasswordValidation(CORRECT_PASSWORD, INVALID_CONFIRM_PASSWORD),
            `${CORRECT_PASSWORD} not equal ${INVALID_CONFIRM_PASSWORD}`)
    });
});


/*
* Positive scenery tests.
* */


module('should success email length validation', () => {
    test.each(
        'should check email length more than 4 symbols',
        ['emails', 'artem@gmail', 'hmail@com'],
        (assert, email) => {

            emailLengthValidation(email).then(
                (result) => assert.equal(result, email, `"${result}" is correct email's length.`)
            )
        });
});

module('should success email structure validation', () => {
    test.each(
        'should check email contains only that symbols: a-zA-Z 0-9 +.-_@',
        ['email@em', 'ascx.bu-ku', '+dvs546'],
        (assert, email) => {

            emailStructureValidation(email).then(
                (result) => assert.equal(result, email, `"${result}" is correct email.`)
            )
        });
});

module('should success password structure validation', () => {
    test.each(
        'should check password length more than 5 symbols',
        ['123456', 'qwerty6', '123qwer'],
        (assert, password) => {
            passwordValidation(password).then(
                (result) => assert.equal(result, password, `"${result}" is correct password.`)
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
