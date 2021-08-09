import {FetchCurrentFolderContentExecutor} from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('FetchCurrentFolderContentExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(3);
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
            return folderContent;
          }
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new FetchCurrentFolderContentExecutor();
    await executor.apply({}, mockServices, stateMock, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'CURRENT_FOLDER_CONTENT_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CURRENT_FOLDER_CONTENT_FETCHING_COMPLETED', {folderContent: folderContent}],
        'Should get folder content');
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
        getFolderContent() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new FetchCurrentFolderContentExecutor();
    await executor.apply({}, servicesMock, stateMock, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'CURRENT_FOLDER_CONTENT_FETCHING_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['CURRENT_FOLDER_CONTENT_FETCHING_FAILED', {error: errorMessage}],
        'Should get error message');
  });
});
