import ValidationError from './validation-error.js';

/**
 * This method allows to validate user input data by fixed length. If data are too short, promise will reject.
 * @param {FormGroupBox} userInputData - is line of user data, which he are input.
 * @param {number} length - is minimum allowed length of the user input data.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function lengthValidation(userInputData, length) {
  return new Promise((resolve, reject) => {
    if (userInputData.inputValue.length < length) {
      reject(new ValidationError(userInputData, `Data length should be more than ${length} symbols`));
    }

    resolve(userInputData);
  });
}

/**
 * This method allows to validate user input data by regular expression.
 * If data consists of characters other than those allowed, promise will reject.
 * @param {FormGroupBox} userInputData - is line of user data, which he are input.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function structureValidation(userInputData) {
  return new Promise((resolve, reject) => {
    if (/^[a-zA-Z0-9+._@-]*$/.test(userInputData.inputValue) === false) {
      reject(new ValidationError(userInputData, `Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
    }

    resolve(userInputData);
  });
}

/**
 * This is method, that check equals of password string and confirm password string.
 * @param {FormGroupBox} passwordUser - is first line, where user input password.
 * @param {FormGroupBox} confirmPassword - is second line, where user confirm his password.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function confirmPasswordValidation(passwordUser, confirmPassword) {
  return new Promise((resolve, reject) => {
    if (passwordUser.inputValue !== confirmPassword.inputValue) {
      reject(new ValidationError(confirmPassword, `Passwords doesn't match.`));
    }

    resolve(passwordUser);
  });
}

