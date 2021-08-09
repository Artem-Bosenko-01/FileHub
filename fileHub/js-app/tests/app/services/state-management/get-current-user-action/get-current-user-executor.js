import GetCurrentUserExecutor
  from '../../../../../app/services/state-management/get-current-user-action/get-current-user-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('Get current user action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
    const currentUser = 'current user data';

    const servicesMock = {
      apiService: {
        getCurrentUser() {
          return currentUser;
        },
      },
    };

    const mutateSpy = getSpy();
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'GET_CURRENT_USER_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['GET_CURRENT_USER_FETCHING_COMPLETED', {user: currentUser}],
        'Should get user data');
  });

  test('Should fail apply executor', async (assert) => {
    assert.expect(3);
    const errorMessage = 'error message';

    const servicesMock = {
      apiService: {
        getCurrentUser() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();
    const executor = new GetCurrentUserExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'GET_CURRENT_USER_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['GET_CURRENT_USER_FETCHING_FAILED', {error: errorMessage}],
        'Should get error message');
  });
});
