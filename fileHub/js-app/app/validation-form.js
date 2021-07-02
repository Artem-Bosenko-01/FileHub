import {confirmPasswordValidation, drawErrorState, lengthValidation, structureValidation} from "./validation-rules.js";

const EMAIL_NAME = 'user-email-box';
const PASSWORD_NAME = 'user-password-box';

export async function validationForm(formElement) {
    clearErrorState(formElement);

    const email = formElement.querySelector('#email-user').value;
    const password = formElement.querySelector('#password-user').value;
    const confirmPassword = formElement.querySelector('#confirm-password-user');

    const promises = [];
    promises.push(lengthValidation(EMAIL_NAME, email, 5));
    promises.push(structureValidation(EMAIL_NAME, email));
    promises.push(lengthValidation(PASSWORD_NAME, password, 6))

    if (confirmPassword) {
        promises.push(confirmPasswordValidation(password, confirmPassword.value))
    }


    const results = await Promise.allSettled(promises)

    const isAnyPromiseStatusReject = results.some(result => result.status === 'rejected');

    if (!isAnyPromiseStatusReject) {
        alert("Email -> " + email + ".\nPassword -> " + password + ".");
    }

    results.filter(result => result.status === 'rejected')
        .forEach(result => drawErrorState(result.reason))
}

function clearErrorState(element) {
    const invalidInputBoxes = element.querySelectorAll('.invalid-input-value');
    invalidInputBoxes.forEach(invalidBox => invalidBox.classList.remove('invalid-input-value'));

    [...element.getElementsByClassName('error-massage')].forEach(massage => massage.remove());
}