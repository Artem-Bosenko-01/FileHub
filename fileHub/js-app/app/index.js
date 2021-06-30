document.getElementById("button").addEventListener('click', () => {

    const inputs = document.querySelectorAll(".input-value input");

    const email = inputs[0].value;
    const password = inputs[1].value;

    const emailPromise = emailValidation(email);
    const passwordPromise = passwordValidation(password);

    /*
        const mainValidation = new Promise((resolve, reject) => {
            resolve();
        })

            mainValidation.then(() => emailValidation(email), (reject) => drawErrorState("user-email", reject));
            mainValidation.then(() => passwordValidation(password), (reject) => drawErrorState("user-password", reject));
            mainValidation.then(()=>{
                alert(email + "\n" + password)
            })
            mainValidation.catch()*/

    /*    emailPromise.catch((reject) => {
            drawErrorState("user-email", reject)
        })

        passwordPromise.catch((reject) => {
            drawErrorState("user-password", reject)
        })*/

    Promise.all([emailPromise, passwordPromise])
        .then(() => alert(email + "\n" + password))
        .catch(
            (reason, id) => drawErrorState(id, reason)
        );

})


function emailValidation(emailUser) {

    return new Promise((resolve, reject) => {

        debugger

        if (emailUser.length < 5) {
            reject(`Email length should be more than 4 symbols`, 'user-email');
        }

        if (/^[a-zA-Z0-9+._@-]*$/.test(emailUser) === false) {
            reject(`Email should be contains a-zA-Z, 0-9 or symbols like "+._@-"`, 'user-email');
        }

        resolve();
    });
}

function passwordValidation(passwordUser) {
    return new Promise((resolve, reject) => {
        if (passwordUser.length < 6) {
            reject(`Password length should be more than 5 symbols`, 'user-password');
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
