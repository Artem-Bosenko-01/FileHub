const EMAIL_NAME = 'user-email';
const PASSWORD_NAME = 'user-password';
const CONFIRM_PASSWORD_NAME = 'user-confirm-password';

class ValidationError {

    constructor(idElement, massage) {
        this.idElement = idElement;
        this.massage = massage;
    }
}

document.getElementById("button").addEventListener('click', () => {

    clearErrorState();

    const inputs = document.querySelectorAll(".input-value input");

    const email = inputs[0].value;
    const password = inputs[1].value;
    const confirmPassword = inputs[2];

    let promises = [
        emailValidation(email),
        passwordValidation(password)
    ]
    if (confirmPassword) {
        promises.push(confirmPasswordValidation(password, confirmPassword.value))
    }

    formValidation(promises, email, password);

})

function formValidation(promises, email, password){
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

function emailValidation(emailUser) {
    return new Promise((resolve, reject) => {

        if (emailUser.length < 5) {
            reject(new ValidationError(EMAIL_NAME, `Email length should be more than 4 symbols`));
        }

        if (/^[a-zA-Z0-9+._@-]*$/.test(emailUser) === false) {
            reject(new ValidationError(EMAIL_NAME, `Email should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
        }

        resolve();
    });
}

function passwordValidation(passwordUser) {
    return new Promise((resolve, reject) => {
        if (passwordUser.length < 6) {
            reject(new ValidationError(PASSWORD_NAME, `Password length should be more than 5 symbols`));
        }

        resolve();
    });
}

function confirmPasswordValidation(passwordUser, confirmPassword) {
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

    let errorMassage = document.createElement('span');
    errorMassage.className = 'error-massage';
    errorMassage.innerText = error.massage;

    errorEmailInput.append(errorMassage);
}

function clearErrorState(){
    const invalidInputBoxes = document.querySelectorAll('.invalid-input-value');
    invalidInputBoxes.forEach(invalidBox=> invalidBox.classList.remove('invalid-input-value'));

    [...document.getElementsByClassName('error-massage')].forEach(massage=> massage.remove());
}