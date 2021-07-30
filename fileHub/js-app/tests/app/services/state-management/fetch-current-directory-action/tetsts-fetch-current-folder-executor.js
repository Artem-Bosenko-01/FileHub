import FetchCurrentFolderExecutor
  from '../../../../../app/services/state-management/fetch-current-directory-action/fetch-current-folder-executor.js';

const {module, test} = QUnit;

module('Fetch current folder action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
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
      if (details && details.folder === folder) {
        assert.step(setFolderInStateStep);
      }
      return true;
    };
    const executor = new FetchCurrentFolderExecutor();
    await executor.apply({}, mockServices, stateMock, mutateMock);

    assert.verifySteps([getFolderStep, setFolderInStateStep]);
  });
});
