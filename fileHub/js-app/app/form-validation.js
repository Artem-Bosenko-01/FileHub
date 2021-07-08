import {confirmPasswordValidation, drawErrorState, lengthValidation, structureValidation} from './validation-rules.js';


/**
 * This is main function for validation form. This function creates necessary promises and
 * analyzes results of them running. If all promises resolves, function will shaw alert.
 * @param {ParameterValidation[]} parameters - is element, that contains user input data.
 * This user data are necessary to be validate.
 */
export async function formValidation(parameters) {
  const promises = [];

  parameters.forEach((parameter)=>{
    switch (parameter.parameterValidationType) {
      case 'email': {
        promises.push(lengthValidation(parameter.component, 5));
        promises.push(structureValidation(parameter.component));
        break;
      }
      case 'password': {
        promises.push(lengthValidation(parameter.component, 6));
        break;
      }
      case 'confirmPassword': {
        promises.push(confirmPasswordValidation(parameter.component, parameter.confirmPassword));
        break;
      }
    }
  });

  const results = await Promise.allSettled(promises);

  const isAnyPromiseStatusReject = results.some((result) => result.status === 'rejected');

  if (!isAnyPromiseStatusReject) {
    alert('Successful validation');
  }

  results.filter((result) => result.status === 'rejected')
      .forEach((result) => {
        result.reason.component.errorMessage = result.reason.message;
      });
}
