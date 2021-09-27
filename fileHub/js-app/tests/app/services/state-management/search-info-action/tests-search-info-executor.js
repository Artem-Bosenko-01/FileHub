import {getSpy} from '../../../get-spy.js';
import {SearchInfoExecutor}
  from '../../../../../app/services/state-management/search-info-action/search-info-executor.js';

const {module, test} = QUnit;

module('SearchInfoExecutor', () => {
  test('Should call expected mutators when searching are successful', async (assert) => {
    const searchLine = 'line';
    const actionInfoMock = {searchLine};
    const itemViewMock = {
      type: 'folder',
      name: 'inline',
      itemsAmount: 44,
    };
    const stateMock = {
      currentFolderContent: [itemViewMock],
    };
    const mutateSpy = getSpy();

    const executor = new SearchInfoExecutor();
    await executor.apply(actionInfoMock, {}, stateMock, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');
    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['SEARCHING_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['SEARCHING_COMPLETED', {content: [itemViewMock]}], 'Should filtered content');
  });

  test('Should call expected mutators when searching are failed', async (assert) => {
    const searchLine = 'line';
    const actionInfoMock = {searchLine};
    const stateMock = {
      currentFolderContent: [],
    };
    const mutateSpy = getSpy();

    const executor = new SearchInfoExecutor();
    await executor.apply(actionInfoMock, {}, stateMock, mutateSpy.getMethod);

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['SEARCHING_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['SEARCHING_FAILED', {error: 'You try to filter empty massive'}],
        'Should get error message');
  });
});
