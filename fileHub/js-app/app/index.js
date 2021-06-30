document.getElementById("form").addEventListener('submit', ()=>{

    const inputs = document.querySelectorAll(".input-value input");

    const email = inputs[0].value;
    const password = inputs[1].value;

    alert("Email -> " + email + ".\nPassword -> " + password + ".");

});
