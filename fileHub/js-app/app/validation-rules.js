import ValidationError from "./validation-error.js";

const EMAIL_NAME = 'user-email-box';
const PASSWORD_NAME = 'user-password-box';
const CONFIRM_PASSWORD_NAME = 'user-confirm-password-box';

export function formValidation(promises, email, password){
    Promise.allSettled(promises)
        .then(
            (results) => {

                const isAnyPromiseStatusReject = results.some(result => result.status === 'rejected');

                if (!isAnyPromiseStatusReject) {
                    alert("Email -> " + email + ".\nPassword -> " + password + ".");
                }

                results.filter(result => result.status === 'rejected')
                    .forEach(result => drawErrorState(result.reason))
            })
}

export function emailLengthValidation(emailUser) {
    return new Promise((resolve, reject) => {

        if (emailUser.length < 5) {
            reject(new ValidationError(EMAIL_NAME, `Email length should be more than 4 symbols`));
        }

        resolve();
    });
}

export function emailStructureValidation(emailUser){

    return new Promise((resolve, reject) => {
        if (/^[a-zA-Z0-9+._@-]*$/.test(emailUser) === false) {
            reject(new ValidationError(EMAIL_NAME, `Email should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
        }

        resolve();
    });
}

export function passwordValidation(passwordUser) {
    return new Promise((resolve, reject) => {
        if (passwordUser.length < 6) {
            reject(new ValidationError(PASSWORD_NAME, `Password length should be more than 5 symbols`));
        }

        resolve();
    });
}

export function confirmPasswordValidation(passwordUser, confirmPassword) {
    return new Promise((resolve, reject) => {
        if (passwordUser !== confirmPassword) {
            reject(new ValidationError(CONFIRM_PASSWORD_NAME, `Passwords doesn't match.`));
        }

        resolve();
    });
}

function drawErrorState(error) {
    const errorEmailLabel = document.querySelector(`#${error.idElement} .label-name`);
    errorEmailLabel.classList.add('errorLabel');

    const errorEmailInput = document.querySelector(`#${error.idElement} .input-value`);
    errorEmailInput.classList.add('invalid-input-value');

    let errorMassage = document.createElement('p');
    errorMassage.className = 'error-massage';
    errorMassage.innerText = error.massage;

    errorEmailInput.append(errorMassage);
}