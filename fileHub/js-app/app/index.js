import {
    confirmPasswordValidation, drawErrorState,
    lengthValidation,
    structureValidation,
} from "./validation-rules.js";

const EMAIL_NAME = 'user-email-box';
const PASSWORD_NAME = 'user-password-box';

document.getElementById("button").addEventListener('click', () => {

    clearErrorState();

    const email = document.getElementById('email-user').value;
    const password = document.getElementById('password-user').value;
    const confirmPassword = document.getElementById('confirm-password-user');

    let promises = [
        lengthValidation(EMAIL_NAME, email, 5),
        structureValidation(EMAIL_NAME, email),
        lengthValidation(PASSWORD_NAME, password, 6)
    ]

    if (confirmPassword) {
        promises.push(confirmPasswordValidation(password, confirmPassword.value))
    }

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

})

function clearErrorState(){
    const invalidInputBoxes = document.querySelectorAll('.invalid-input-value');
    invalidInputBoxes.forEach(invalidBox=> invalidBox.classList.remove('invalid-input-value'));

    [...document.getElementsByClassName('error-massage')].forEach(massage=> massage.remove());
}