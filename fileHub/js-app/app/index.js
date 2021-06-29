/*First method*/
/*
function info(){
    let email = document.getElementById("email-user").value;
    let password = document.getElementById("password-user").value;

    if(email ==="") email = "empty";
    if(password ==="") password = "empty";

    alert("Email -> " + email + ".\nPassword -> " + password + ".");
}
*/

/*Second method*/
function info() {

    let data = document.querySelectorAll(".input-value input");

    let email = data[0].value;
    let password = data[1].value;

    if (email === "") email = "empty";
    if (password === "") password = "empty";

    alert("Email -> " + email + ".\nPassword -> " + password + ".");
}
