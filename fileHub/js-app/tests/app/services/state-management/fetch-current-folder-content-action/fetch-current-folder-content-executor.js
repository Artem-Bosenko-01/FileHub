import {FetchCurrentFolderContentExecutor} from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content-executor.js';

const {module, test} = QUnit;

module('Fetch current folder content action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(5);
    const getFolderContentStep = 'Get folder by id request';
    const id = 'folder';
    const folderContent = 'folderContent';
    const stateMock = {
      locationParams: {
        currentFolderId: id,
      },
    };

    const mockServices = {
      apiService: {
        getFolderContent(folderId) {
          if (id === folderId) {
            assert.step(getFolderContentStep);
            return folderContent;
          }
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'CURRENT_FOLDER_CONTENT_FETCHING_COMPLETED') {
        assert.strictEqual(details.folderContent, folderContent,
            'Should get folder content after successfully fetching');
      }
    };
    const executor = new FetchCurrentFolderContentExecutor();
    await executor.apply({}, mockServices, stateMock, mutateSpy);

    assert.verifySteps(['CURRENT_FOLDER_CONTENT_FETCHING_STARTED', getFolderContentStep,
      'CURRENT_FOLDER_CONTENT_FETCHING_COMPLETED']);
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
        getFolderContent(folderId) {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'CURRENT_FOLDER_CONTENT_FETCHING_FAILED') {
        assert.equal(details.error, errorMessage, 'Should get error message after fail fetching');
      }
    };
    const executor = new FetchCurrentFolderContentExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy);

    assert.verifySteps(['CURRENT_FOLDER_CONTENT_FETCHING_STARTED', 'CURRENT_FOLDER_CONTENT_FETCHING_FAILED']);
  });
});
