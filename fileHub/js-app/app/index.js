import {
    confirmPasswordValidation,
    emailLengthValidation,
    emailStructureValidation,
    formValidation,
    passwordValidation
} from "./validation-rules.js";

document.getElementById("button").addEventListener('click', () => {

    clearErrorState();

    const email = document.getElementById('user-email').value;
    const password = document.getElementById('user-password').value;
    const confirmPassword = document.getElementById('user-confirm-password');

    let promises = [
        emailLengthValidation(email),
        emailStructureValidation(email),
        passwordValidation(password)
    ]

    if (confirmPassword) {
        promises.push(confirmPasswordValidation(password, confirmPassword.value))
    }

    formValidation(promises, email, password);

})

function clearErrorState(){
    const invalidInputBoxes = document.querySelectorAll('.invalid-input-value');
    invalidInputBoxes.forEach(invalidBox=> invalidBox.classList.remove('invalid-input-value'));

    [...document.getElementsByClassName('error-massage')].forEach(massage=> massage.remove());
}