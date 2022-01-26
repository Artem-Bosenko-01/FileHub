import {UploadFileExecutor}
  from '../../../../../app/services/state-management/upload-file-action/upload-file-executor.js';
import {getSpy} from '../../../get-spy.js';
import {FetchCurrentFolderContent}
  from '../../../../../app/services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';

const {module, test} = QUnit;

module('UploadFileExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert)=>{
    const actionInfoMock = {
      file: 'fileName',
      parentFolderId: 'id',
    };

    const mockServices = {
      apiService: {
        uploadFile() {
        },
      },
    };

    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();

    const uploadFileExecutor = new UploadFileExecutor();
    await uploadFileExecutor.apply(actionInfoMock, mockServices, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['UPLOAD_FILE_MUTATOR_FETCHING_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['UPLOAD_FILE_MUTATOR_FETCHING_COMPLETED'], 'Should get message');

    assert.equal(dispatchSpy.calls.length, 1, 'Should be called once dispatch spy');
    const firstCalledDispatchSpy = dispatchSpy.calls[0];
    assert.deepEqual(firstCalledDispatchSpy, [new FetchCurrentFolderContent()], 'Should get message');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert)=>{
    const errorMessage = 'Fetching was failed';
    const actionInfoMock = {
      file: 'fileName',
      parentFolderId: 'id',
    };

    const mockServices = {
      apiService: {
        uploadFile() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();
    const dispatchSpy = getSpy();

    const uploadFileExecutor = new UploadFileExecutor();
    await uploadFileExecutor.apply(actionInfoMock, mockServices, {}, mutateSpy.getMethod, dispatchSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.deepEqual(firstCalled, ['UPLOAD_FILE_MUTATOR_FETCHING_STARTED'], 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['UPLOAD_FILE_MUTATOR_FETCHING_FAILED', {error: errorMessage}],
        'Should get message');

    assert.equal(dispatchSpy.calls.length, 0, 'Shouldn\'t called dispatch method');
  });
});
