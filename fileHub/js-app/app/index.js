import {validationForm} from "./validation-form.js";

const form = document.getElementById('form');

document.getElementById("button").addEventListener('click', () => validationForm(form))
