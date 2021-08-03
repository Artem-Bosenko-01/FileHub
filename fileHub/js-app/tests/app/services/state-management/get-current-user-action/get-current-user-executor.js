import GetCurrentUserExecutor
  from '../../../../../app/services/state-management/get-current-user-action/get-current-user-executor.js';

const {module, test} = QUnit;

module('Get current user action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(5);
    const startFetching = 'Start fetching';
    const getCurrentUserRequest = 'Get current user request';
    const setCurrentUserInState = 'Set current user';
    const currentUser = 'current user data';

    const mockServices = {
      apiService: {
        getCurrentUser() {
          assert.step(getCurrentUserRequest);
          return currentUser;
        },
      },
    };

    const mutateMock = (type, details) => {
      if (type === 'GET_CURRENT_USER_FETCHING_STARTED') {
        assert.step(startFetching);
      }

      if (type === 'GET_CURRENT_USER_FETCHING_COMPLETED' && details) {
        assert.step(setCurrentUserInState);
        assert.strictEqual(details.user, currentUser, 'Should get current user data');
      }
      return true;
    };
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);

    assert.verifySteps([startFetching, getCurrentUserRequest, setCurrentUserInState]);
  });

  test('Should fail apply executor', async (assert) => {
    assert.expect(4);
    const startFetching = 'Start fetching';
    const errorMessage = 'error message';

    const mockServices = {
      apiService: {
        getCurrentUser() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateMock = (type, details) => {
      if (type === 'GET_CURRENT_USER_FETCHING_STARTED') {
        assert.step(startFetching);
      }

      if (type === 'GET_CURRENT_USER_FETCHING_FAILED' && details) {
        assert.step(details.error);
        assert.strictEqual(details.error, errorMessage, 'Should get error message after fetching fail');
      }
    };
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);

    assert.verifySteps([startFetching, errorMessage]);
  });
});
