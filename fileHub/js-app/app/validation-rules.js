import ValidationError from './validation-error.js';

/**
 * This method allows to validate user input data by fixed length. If data are too short, promise will reject.
 * @param {string} idElement - is id of element, that will be contains error massage if promise are reject.
 * @param {string} userInputData - is line of user data, which he are input.
 * @param {number} length - is minimum allowed length of the user input data.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function lengthValidation(idElement, userInputData, length) {
  return new Promise((resolve, reject) => {
    if (userInputData.length < length) {
      reject(new ValidationError(idElement, `Data length should be more than ${length} symbols`));
    }

    resolve(userInputData);
  });
}

/**
 * This method allows to validate user input data by regular expression.
 * If data consists of characters other than those allowed, promise will reject.
 * @param {string} idElement - is id of element, that will be contains error massage if promise are reject.
 * @param {string} userInputData - is line of user data, which he are input.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function structureValidation(idElement, userInputData) {
  return new Promise((resolve, reject) => {
    if (/^[a-zA-Z0-9+._@-]*$/.test(userInputData) === false) {
      reject(new ValidationError(idElement, `Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
    }

    resolve(userInputData);
  });
}

/**
 * This is method, that check equals of password string and confirm password string.
 * @param {string} passwordUser - is first line, where user input password.
 * @param {string} confirmPassword - is second line, where user confirm his password.
 * @returns {Promise} - is result of validation process: promise will resolve or reject.
 */
export function confirmPasswordValidation(passwordUser, confirmPassword) {
  return new Promise((resolve, reject) => {
    if (passwordUser !== confirmPassword) {
      reject(new ValidationError('confirm-password-user', `Passwords doesn't match.`));
    }

    resolve(passwordUser);
  });
}

/**
 * This is function for drawing error massage when promise return reject.
 * @param {ValidationError} errorValidation - is exception when promise are rejected
 */
export function drawErrorState(errorValidation) {
  const errorEmailInput = document.querySelector(`#${errorValidation.idElement}`);
  errorEmailInput.parentElement.classList.add('invalid-input-value');

  const errorMassage = document.createElement('p');
  errorMassage.className = 'error-massage';
  errorMassage.innerText = errorValidation.message;

  errorEmailInput.after(errorMassage);
}
