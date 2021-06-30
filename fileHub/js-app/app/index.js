document.getElementById("button").addEventListener('click', () => {

    const inputs = document.querySelectorAll(".input-value input");

    const email = inputs[0].value;
    const password = inputs[1].value;

    const validationUserData = new Promise((resolve, reject) => {
        emailValidation(email).catch((reject) => {
            drawErrorState("user-email", reject)
        });

        passwordValidation(password).catch((reject) => { drawErrorState("user-password", reject)});

        resolve();
    });

    validationUserData.then(() => alert(email + "\n" + password));
})


function emailValidation(emailUser) {

    return new Promise((resolve, reject) => {
        if (emailUser.length < 5) {
            reject(`Email length should be more than 4 symbols`);
        }

        if (/^[a-zA-Z0-9+._@-]*$/.test(emailUser) === false) {
            reject(`Email should be contains a-zA-Z, 0-9 or symbols like "+._@-"`);
        }

        resolve();
    });
}

function passwordValidation(passwordUser) {
    return new Promise((resolve, reject) => {
        if (passwordUser.length < 6) {
            reject(`Password length should be more than 5 symbols`);
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
