import GetCurrentUserExecutor
  from '../../../../../app/services/state-management/get-current-user-action/get-current-user-executor.js';

const {module, test} = QUnit;

module('Get current user action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
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
      if (details && details === currentUser) {
        assert.step(setCurrentUserInState);
      }
      return true;
    };
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);

    assert.verifySteps([getCurrentUserRequest, setCurrentUserInState]);
  });
});
