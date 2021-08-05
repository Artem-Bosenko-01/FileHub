import {RouteChangedExecutor}
  from '../../../../../app/services/state-management/hash-changed-action/route-changed-executor.js';

const {module, test} = QUnit;

module('Route changed action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(4);
    const staticParam = 'static';
    const dynamicParam = 'dynamic';

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'HASH_CHANGED_MUTATOR_COMPLETED') {
        assert.equal(details.pageRoute, staticParam, 'Should get static param of url');
        assert.strictEqual(details.dynamicPart.currentFolderId, dynamicParam, 'Should get current folder id');
      }
    };

    const actionInfoMock = {
      staticParam, dynamicParam,
    };

    const executor = new RouteChangedExecutor();
    executor.apply(actionInfoMock, {}, {}, mutateSpy);
    assert.verifySteps(['HASH_CHANGED_MUTATOR_COMPLETED']);
  });
});
