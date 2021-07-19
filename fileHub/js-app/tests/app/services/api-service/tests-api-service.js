import testsAuthenticationApiService from '../../login/tests-authentication-api-service.js';
import testsRegistrationApiService from '../../register/tests-registration-api-service.js';
const {module} = QUnit;

module('API service', () => {
  testsAuthenticationApiService();
  testsRegistrationApiService();
});
