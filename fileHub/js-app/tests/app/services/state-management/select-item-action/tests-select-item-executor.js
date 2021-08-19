import {getSpy} from '../../../get-spy.js';
import {SelectItemExecutor}
  from '../../../../../app/services/state-management/select-item-action/select-item-executor.js';

const {module, test} = QUnit;

module('SelectItemExecutor', () => {
  test('Should call expected mutators when item view line selected', async (assert) => {
    const itemId = 'id';
    const actionInfoMock = {
      id: itemId,
    };
    const mutateSpy = getSpy();

    const executor = new SelectItemExecutor();
    await executor.apply(actionInfoMock, {}, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 1, 'Should be called once');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['SET_SELECTED_ITEM', {itemId}], 'Should get message');
  });
});
