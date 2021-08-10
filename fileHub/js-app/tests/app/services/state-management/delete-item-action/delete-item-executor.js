import {DeleteItemExecutor}
  from '../../../../../app/services/state-management/delete-item-action/delete-item-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('DeleteItemExecutor', ()=>{
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(4);
    const itemId = 'id';
    const itemType = 'folder';

    const actionInfoMock = {
      item: {
        id: itemId,
        type: itemType,
      },
    };
    const servicesMock = {
      apiService: {
        deleteFolder(folderId) {
          if (folderId === itemId) {
            return true;
          }
        },
      },
    };
    const dispatchSpy = getSpy();
    const mutateSpy = getSpy();
    const executor = new DeleteItemExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['DELETE_ITEM_FETCHING_STARTED', {removingFile: actionInfoMock.item}],
        'Should get message and removing file');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['DELETE_ITEM_FETCHING_COMPLETED'], 'Should get message');

    assert.equal(dispatchSpy.calls.length, 1, 'Should be called once dispatch spy');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert) => {
    assert.expect(4);
    const errorMessage = 'Fetching was failed';
    const itemId = 'id';
    const itemType = 'folder';

    const actionInfoMock = {
      item: {
        id: itemId,
        type: itemType,
      },
    };
    const servicesMock = {
      apiService: {
        deleteFolder() {
          throw new Error(errorMessage);
        },
      },
    };
    const dispatchSpy = getSpy();
    const mutateSpy = getSpy();
    const executor = new DeleteItemExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['DELETE_ITEM_FETCHING_STARTED', {removingFile: actionInfoMock.item}],
        'Should get message and removing file');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['DELETE_ITEM_FETCHING_FAILED', {error: errorMessage}],
        'Should get error message');

    assert.equal(dispatchSpy.calls.length, 0, 'Shouldn\'t called dispatch method');
  });
});
