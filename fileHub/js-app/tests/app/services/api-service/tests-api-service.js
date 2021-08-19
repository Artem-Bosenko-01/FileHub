import testsAuthenticationApiService from '../../login/tests-authentication-api-service.js';
import testsRegistrationApiService from '../../register/tests-registration-api-service.js';
import {ApiService} from '../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

module('APIService', () => {
  testsAuthenticationApiService();
  testsRegistrationApiService();

  test('Should add and call listener on 401 response code status.', (assert) => {
    const get401ResponseStatusStep = '401 response status';
    const mockWindow = new EventTarget();
    const apiService = new ApiService(mockWindow);
    apiService.onNavigateAfterError(assert.step(get401ResponseStatusStep));

    mockWindow.dispatchEvent(new CustomEvent('unauthorizedUserError'));

    assert.verifySteps([get401ResponseStatusStep]);
  });
});
