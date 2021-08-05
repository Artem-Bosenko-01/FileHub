import GetCurrentUserExecutor
  from '../../../../../app/services/state-management/get-current-user-action/get-current-user-executor.js';

const {module, test} = QUnit;

module('Get current user action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(5);
    const getCurrentUserRequest = 'Get current user request';
    const currentUser = 'current user data';

    const servicesMock = {
      apiService: {
        getCurrentUser() {
          assert.step(getCurrentUserRequest);
          return currentUser;
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'GET_CURRENT_USER_FETCHING_COMPLETED') {
        assert.strictEqual(details.user, currentUser, 'Should get current user data');
      }
    };
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy);

    assert.verifySteps(['GET_CURRENT_USER_FETCHING_STARTED', getCurrentUserRequest,
      'GET_CURRENT_USER_FETCHING_COMPLETED']);
  });

  test('Should fail apply executor', async (assert) => {
    assert.expect(4);
    const errorMessage = 'error message';

    const servicesMock = {
      apiService: {
        getCurrentUser() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'GET_CURRENT_USER_FETCHING_FAILED') {
        assert.strictEqual(details.error, errorMessage, 'Should get error message after fetching fail');
      }
    };
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy);

    assert.verifySteps(['GET_CURRENT_USER_FETCHING_STARTED', 'GET_CURRENT_USER_FETCHING_FAILED']);
  });
});
