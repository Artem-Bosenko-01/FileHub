import {GetRootFolderExecutor}
  from '../../../../../app/services/state-management/get-root-folder-action/get-root-folder-executor.js';
import {getSpy} from '../../../get-spy.js';

const {module, test} = QUnit;

module('GetRootFolderExecutor', () => {
  test('Should call expected mutators when API Service returns 200 code status', async (assert) => {
    assert.expect(3);
    const rootFolder = 'root folder';
    const servicesMock = {
      apiService: {
        getRootFolder() {
          return rootFolder;
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new GetRootFolderExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'GET_ROOT_FOLDER_MUTATOR_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['GET_ROOT_FOLDER_MUTATOR_COMPLETED', {rootFolder: rootFolder}],
        'Should get root folder');
  });

  test('Should call expected mutators when API Service throws an exception', async (assert) => {
    const errorMessage = 'Fetching was failed';
    const servicesMock = {
      apiService: {
        getRootFolder() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = getSpy();

    const executor = new GetRootFolderExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy.getMethod);

    assert.equal(mutateSpy.calls.length, 2, 'Should be called twice');

    const firstCalled = mutateSpy.calls[0];
    assert.equal(firstCalled, 'GET_ROOT_FOLDER_MUTATOR_STARTED', 'Should get message');

    const secondCalled = mutateSpy.calls[1];
    assert.deepEqual(secondCalled, ['GET_ROOT_FOLDER_MUTATOR_FAILED', {error: errorMessage}],
        'Should get error message');
  });
});
