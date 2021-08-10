import {RouteChangedExecutor}
  from '../../../../../app/services/state-management/hash-changed-action/route-changed-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('RouteChangedExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(2);
    const staticParam = 'static';
    const dynamicParam = 'dynamic';

    const mutateSpy = getSpy();

    const actionInfoMock = {
      staticParam, dynamicParam,
    };

    const executor = new RouteChangedExecutor();
    executor.apply(actionInfoMock, {}, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 1, 'Should be called once');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['HASH_CHANGED_MUTATOR_COMPLETED',
      {dynamicPart: dynamicParam, pageRoute: staticParam}], 'Should get message');
  });
});
