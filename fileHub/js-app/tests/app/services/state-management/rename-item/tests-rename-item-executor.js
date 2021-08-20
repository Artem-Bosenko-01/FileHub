import {getSpy} from '../../../get-spy.js';
import {RenameItemExecutor}
  from '../../../../../app/services/state-management/rename-item-action/rename-item-executor.js';
import {FetchCurrentFolderContent}
  from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';

const {module, test} = QUnit;

module('RenameItemExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    const item = {type: 'folder', name: 'item'};
    const newName = 'new name';
    const actionInfoMock = {item, newName};
    const servicesMock = {
      apiService: {
        renameFolder(renamedItem, newItemsName) {
          assert.deepEqual(renamedItem, item, 'Should get renamed item');
          assert.equal(newItemsName, newName, 'Should get new item\'s name');
        },
      },
    };
    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();

    const executor = new RenameItemExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');
    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['RENAME_ITEM_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['RENAME_ITEM_COMPLETED'], 'Should get message');

    assert.equal(dispatchSpy.calls.length, 1, 'Should be called once dispatch spy');
    const firstCalledDispatchSpy = dispatchSpy.calls[0];
    assert.deepEqual(firstCalledDispatchSpy, [new FetchCurrentFolderContent()], 'Should get message');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert) => {
    const errorMessage = 'error message';
    const actionInfoMock = {item: {type: 'folder'}, newName: 'name'};
    const servicesMock = {
      apiService: {
        renameFolder() {
          throw new Error(errorMessage);
        },
      },
    };
    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();

    const executor = new RenameItemExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');
    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['RENAME_ITEM_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['RENAME_ITEM_FAILED', {error: errorMessage}], 'Should get error message');

    assert.equal(dispatchSpy.calls.length, 0, 'Shouldn\'t called dispatch method');
  });
});
