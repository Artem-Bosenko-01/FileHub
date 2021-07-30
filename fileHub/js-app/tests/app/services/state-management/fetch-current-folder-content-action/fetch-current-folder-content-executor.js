import {FetchCurrentFolderContentExecutor}
  from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content-executor.js';

const {module, test} = QUnit;

module('Fetch current folder content action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
    const getFolderContentStep = 'Get folder by id request';
    const setFolderContentInStateStep = 'Set folder content';
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

    const mutateMock = (type, details) => {
      if (details && details.folderContent === folderContent) {
        assert.step(setFolderContentInStateStep);
      }
      return true;
    };
    const executor = new FetchCurrentFolderContentExecutor();
    await executor.apply({}, mockServices, stateMock, mutateMock);

    assert.verifySteps([getFolderContentStep, setFolderContentInStateStep]);
  });
});
