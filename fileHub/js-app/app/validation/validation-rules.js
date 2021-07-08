import ValidationError from './validation-error.js';

/**
 * This method allows to validate user input data by fixed length. If data are too short, promise will reject.
 * @param {string} value - is line of user data, which he are input.
 * @param {number} length - is minimum allowed length of the user input data.
 * @param {FormGroupBox} fieldId
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function lengthValidation(fieldId, value, length) {
  return new Promise((resolve, reject) => {
    if (value.length < length) {
      reject(new ValidationError(fieldId, `Data length should be more than ${length} symbols`));
    }

    resolve(value);
  });
}

/**
 * This method allows to validate user input data by regular expression.
 * If data consists of characters other than those allowed, promise will reject.

 * @param {string} value - is line of user data, which he are input.
 * @param {FormGroupBox} fieldId
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function structureValidation(fieldId, value) {
  return new Promise((resolve, reject) => {
    if (/^[a-zA-Z0-9+._@-]*$/.test(value) === false) {
      reject(new ValidationError(fieldId, `Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
    }

    resolve(value);
  });
}

/**
 * This is method, that check equals of password string and confirm password string.
 * @param {FormGroupBox} fieldId
 * @param {string} passwordUser - is first line, where user input password.
 * @param {string} confirmPassword - is second line, where user confirm his password.
 * @returns {Promise} - is result of va
 * lidation process: promise will resolve or reject.
 */
export function confirmPasswordValidation(fieldId, passwordUser, confirmPassword) {
  return new Promise((resolve, reject) => {
    if (passwordUser !== confirmPassword) {
      reject(new ValidationError(fieldId, `Passwords doesn't match.`));
    }

    resolve(passwordUser);
  });
}

