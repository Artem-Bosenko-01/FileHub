const {module} = QUnit;

module('API service', () => {
  module('Registration', () => import('./tests-registration-api-service.js'));
  module('Authentication', () => import('./tests-authentication-api-service.js'));
});
