import {
    confirmPasswordValidation,
    emailLengthValidation,
    emailStructureValidation,
    formInputValuesValidation,
    passwordValidation
} from "./validation-rules.js";

document.getElementById("button").addEventListener('click', () => {

    clearErrorState();

    const email = document.getElementById('email-user').value;
    const password = document.getElementById('password-user').value;
    const confirmPassword = document.getElementById('confirm-password-user');

    let promises = [
        emailLengthValidation(email),
        emailStructureValidation(email),
        passwordValidation(password)
    ]

    if (confirmPassword) {
        promises.push(confirmPasswordValidation(password, confirmPassword.value))
    }

    formInputValuesValidation(promises, email, password);

})

function clearErrorState(){
    const invalidInputBoxes = document.querySelectorAll('.invalid-input-value');
    invalidInputBoxes.forEach(invalidBox=> invalidBox.classList.remove('invalid-input-value'));

    [...document.getElementsByClassName('error-massage')].forEach(massage=> massage.remove());
}