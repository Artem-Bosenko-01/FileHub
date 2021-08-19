import {getSpy} from '../../../get-spy.js';
import {LogOutUserExecutor}
  from '../../../../../app/services/state-management/log-out-user-action/log-out-user-executor.js';

const {module, test} = QUnit;

module('LogOutUserExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    const servicesMock = {
      apiService: {
        logOut() {
        },
      },
    };

    const mutateSpy = getSpy();
    const executor = new LogOutUserExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 1, 'Should be called once');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'LOG_OUT_USER_COMPLETED', 'Should get message');
  });
});
