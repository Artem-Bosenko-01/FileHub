import FetchCurrentFolderExecutor
  from '../../../../../app/services/state-management/fetch-current-folder-action/fetch-current-folder-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('FetchCurrentFolderExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(3);
    const id = 'folder';
    const folder = 'folderItem';
    const stateMock = {
      locationParams: {
        currentFolderId: id,
      },
    };

    const servicesMock = {
      apiService: {
        getFolder(folderId) {
          if (folderId === id) {
            return folder;
          }
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'CURRENT_FOLDER_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CURRENT_FOLDER_FETCHING_COMPLETED', {folder: folder}],
        'Should get folder');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert) => {
    assert.expect(3);
    const errorMessage = 'Fetching was failed';
    const stateMock = {
      locationParams: {
        currentFolderId: 'id',
      },
    };

    const servicesMock = {
      apiService: {
        getFolder() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();
    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'CURRENT_FOLDER_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CURRENT_FOLDER_FETCHING_FAILED', {error: errorMessage}],
        'Should get error message');
  });
});
