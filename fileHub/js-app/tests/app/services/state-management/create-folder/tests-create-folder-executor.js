import {getSpy} from '../../../get-spy.js';
import {CreateFolderExecutor}
  from '../../../../../app/services/state-management/create-folder-action/create-folder-executor.js';
import {FetchCurrentFolderContent}
  from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';
const {module, test} = QUnit;

module('CreateFolderExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert)=> {
    const parentFolderId = 'id';
    const folderName = 'folder name';
    const actionInfoMock = {
      folderName,
      parentFolderId,
    };
    const mockServices = {
      apiService: {
        createFolder(inputFolderName, inputParentFolderId) {
          assert.equal(inputFolderName, folderName, 'Should get folder name');
          assert.equal(inputParentFolderId, parentFolderId, 'Should get Parent folder id');
        },
      },
    };

    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();
    const executor = new CreateFolderExecutor();
    await executor.apply(actionInfoMock, mockServices, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['CREATE_FOLDER_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CREATE_FOLDER_COMPLETED'], 'Should get message');

    assert.equal(dispatchSpy.calls.length, 1, 'Should be called once dispatch spy');
    const firstCalledDispatchSpy = dispatchSpy.calls[0];
    assert.deepEqual(firstCalledDispatchSpy, [new FetchCurrentFolderContent()], 'Should get message');
  });

  test('Should call expected mutators when API Service returns 200 code status', async (assert)=> {
    const errorMessage = 'error message';
    const actionInfoMock = {
      folderName: 'folder name',
      parentFolderId: 'id',
    };
    const mockServices = {
      apiService: {
        createFolder() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();
    const executor = new CreateFolderExecutor();
    await executor.apply(actionInfoMock, mockServices, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['CREATE_FOLDER_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CREATE_FOLDER_FAILED', {error: errorMessage}],
        'Should get message');

    assert.equal(dispatchSpy.calls.length, 0, 'Shouldn\'t called dispatch method');
  });
});
