import {getSpy} from '../../../get-spy.js';
import {DownloadFileExecutor}
  from '../../../../../app/services/state-management/download-file-action/download-file-executor.js';

const {module, test} = QUnit;

module('DownloadFileExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(3);
    const itemId = 'id';
    const fileContent = 'content';

    const actionInfoMock = {
      fileId: itemId,
    };
    const servicesMock = {
      apiService: {
        downloadFile(fileId) {
          if (fileId === itemId) {
            return fileContent;
          }
        },
      },
    };
    const mutateSpy = getSpy();

    const executor = new DownloadFileExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['DOWNLOAD_FILE_MUTATOR_FETCHING_STARTED', {downloadedFile: itemId}],
        'Should get message and downloading file');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, [
      'DOWNLOAD_FILE_MUTATOR_FETCHING_COMPLETED',
      {file: fileContent},
    ], 'Should get message');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert) => {
    const itemId = 'id';
    const errorMessage = 'error message';

    const actionInfoMock = {
      fileId: itemId,
    };
    const servicesMock = {
      apiService: {
        downloadFile() {
          throw new Error(errorMessage);
        },
      },
    };
    const mutateSpy = getSpy();

    const executor = new DownloadFileExecutor();
    await executor.apply(actionInfoMock, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['DOWNLOAD_FILE_MUTATOR_FETCHING_STARTED', {downloadedFile: itemId}],
        'Should get message and downloading file');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['DOWNLOAD_FILE_MUTATOR_FETCHING_FAILED',
      {downloadedFile: itemId,
        error: errorMessage},
    ],
    'Should get error message');
  });
});
