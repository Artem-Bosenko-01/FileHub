import ValidationError from "./validation-error.js";

export function lengthValidation(idElement, userInputData, length) {
    return new Promise((resolve, reject) => {

        if (userInputData.length < length) {
            reject(new ValidationError(idElement, `Data length should be more than ${length} symbols`));
        }

        resolve(userInputData);
    });
}

export function structureValidation(idElement, userInputData) {

    return new Promise((resolve, reject) => {
        if (/^[a-zA-Z0-9+._@-]*$/.test(userInputData) === false) {
            reject(new ValidationError(idElement, `Data should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
        }

        resolve(userInputData);
    });
}


export function confirmPasswordValidation(passwordUser, confirmPassword) {
    return new Promise((resolve, reject) => {
        if (passwordUser !== confirmPassword) {
            reject(new ValidationError('user-confirm-password-box', `Passwords doesn't match.`));
        }

        resolve(passwordUser);
    });
}

export function drawErrorState(errorValidation) {
    const errorEmailLabel = document.querySelector(`#${errorValidation.idElement} .label-name`);
    errorEmailLabel.classList.add('errorLabel');

    const errorEmailInput = document.querySelector(`#${errorValidation.idElement} .input-value`);
    errorEmailInput.classList.add('invalid-input-value');

    let errorMassage = document.createElement('p');
    errorMassage.className = 'error-massage';
    errorMassage.innerText = errorValidation.massage;

    errorEmailInput.append(errorMassage);
}