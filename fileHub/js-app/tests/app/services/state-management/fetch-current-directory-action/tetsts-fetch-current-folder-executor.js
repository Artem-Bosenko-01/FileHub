import FetchCurrentFolderExecutor
  from '../../../../../app/services/state-management/fetch-current-directory-action/fetch-current-folder-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('Fetch current folder action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(5);
    const getFolderStep = 'Get folder by id request';
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
            assert.step(getFolderStep);
            return folder;
          }
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy);

    debugger;

    mutateSpy.getCalls();
    assert.verifySteps(['CURRENT_FOLDER_FETCHING_STARTED', getFolderStep, 'CURRENT_FOLDER_FETCHING_COMPLETED']);
  });

  test('Should fail apply executor', async (assert) => {
    assert.expect(4);
    const errorMessage = 'Fetching was failed';
    const stateMock = {
      locationParams: {
        currentFolderId: 'id',
      },
    };

    const servicesMock = {
      apiService: {
        getFolder(folderId) {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'CURRENT_FOLDER_FETCHING_FAILED') {
        assert.equal(details.error, errorMessage, 'Should get error message after fetching fail');
      }
    };
    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy);

    assert.verifySteps(['CURRENT_FOLDER_FETCHING_STARTED', 'CURRENT_FOLDER_FETCHING_FAILED']);
  });
});
