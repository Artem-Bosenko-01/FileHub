import FetchCurrentFolderExecutor
  from '../../../../../app/services/state-management/fetch-current-directory-action/fetch-current-folder-executor.js';

const {module, test} = QUnit;

module('Fetch current folder action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(4);
    const startFetching = 'Start fetching';
    const getFolderStep = 'Get folder by id request';
    const setFolderInStateStep = 'Set root folder';
    const id = 'folder';
    const folder = 'folderItem';
    const stateMock = {
      locationParams: {
        currentFolderId: id,
      },
    };

    const mockServices = {
      apiService: {
        getFolder(folderId) {
          if (folderId === id) {
            assert.step(getFolderStep);
            return folder;
          }
        },
      },
    };

    const mutateMock = (type, details) => {
      if (type === 'CURRENT_FOLDER_FETCHING_STARTED') {
        assert.step(startFetching);
      }

      if (details && details.folder === folder) {
        assert.step(setFolderInStateStep);
      }
      return true;
    };
    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, mockServices, stateMock, mutateMock);

    assert.verifySteps([startFetching, getFolderStep, setFolderInStateStep]);
  });

  test('Should fail apply executor', async (assert) => {
    assert.expect(4);
    const startFetching = 'Start fetching';
    const errorMessage = 'Fetching was failed';
    const stateMock = {
      locationParams: {
        currentFolderId: 'id',
      },
    };

    const mockServices = {
      apiService: {
        getFolder(folderId) {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateMock = (type, details) => {
      if (type === 'CURRENT_FOLDER_FETCHING_STARTED') {
        assert.step(startFetching);
      }

      if (type === 'CURRENT_FOLDER_FETCHING_FAILED') {
        assert.step(details.error);
        assert.equal(details.error, errorMessage, 'Should get error message after fetching fail');
      }
    };
    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, mockServices, stateMock, mutateMock);

    assert.verifySteps([startFetching, errorMessage]);
  });
});
