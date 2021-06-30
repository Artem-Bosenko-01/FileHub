class ValidationError{

    constructor(id, reason) {
        this.id = id;
        this.reason = reason;
    }
}

document.getElementById("button").addEventListener('click', () => {

    const inputs = document.querySelectorAll(".input-value input");

    const email = inputs[0].value;
    const password = inputs[1].value;

    const emailPromise = emailValidation(email);
    const passwordPromise = passwordValidation(password);


    Promise.all([emailPromise, passwordPromise])
        .then(() => alert("Email -> " + email + ".\nPassword -> " + password + "."))
        .catch(
            (error) => drawErrorState(error.id, error.reason)
        );

})


function emailValidation(emailUser) {
    return new Promise((resolve, reject) => {

        if (emailUser.length < 5) {
            reject(new ValidationError('user-email', `Email length should be more than 4 symbols`));
        }

        if (/^[a-zA-Z0-9+._@-]*$/.test(emailUser) === false) {
            reject(new ValidationError('user-email', `Email should be contains a-zA-Z, 0-9 or symbols like "+._@-"`));
        }

        resolve();
    });
}

function passwordValidation(passwordUser) {
    return new Promise((resolve, reject) => {
        if (passwordUser.length < 6) {
            reject(new ValidationError('user-password', `Password length should be more than 5 symbols`));
        }

        resolve();
    });
}

function drawErrorState(idElement, reject) {
    const errorEmailLabel = document.querySelector(`#${idElement} .label-name`);
    errorEmailLabel.classList.add('errorLabel');

    const errorEmailInput = document.querySelector(`#${idElement} .input-value`);
    errorEmailInput.classList.add('invalid-input-value');

    let errorMassage = document.createElement('span');
    errorMassage.className = 'error-massage';
    errorMassage.innerText = reject;

    errorEmailInput.append(errorMassage);
}
