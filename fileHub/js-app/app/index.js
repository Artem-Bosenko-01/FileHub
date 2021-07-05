import {validationForm} from './validation-form.js';

const form = document.getElementsByClassName('data')[0];

document.getElementById('button').addEventListener('click', () => validationForm(form));
