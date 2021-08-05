import {GetRootFolderExecutor}
  from '../../../../../app/services/state-management/get-root-folder-action/get-root-folder-executor.js';

const {module, test} = QUnit;

module('Get root folder action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
    const getRootFolderStep = 'Get root folder request';
    const rootFolder = 'root folder';
    const servicesMock = {
      apiService: {
        getRootFolder() {
          assert.step(getRootFolderStep);
          return rootFolder;
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'GET_ROOT_FOLDER_MUTATOR_FAILED') {
        assert.equal(details.folder, rootFolder, 'Should get root folder after successfully fetching');
      }
    };
    const executor = new GetRootFolderExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy);

    assert.verifySteps([getRootFolderStep, 'GET_ROOT_FOLDER_MUTATOR_COMPLETED']);
  });

  test('Should  fail apply executor', async (assert) => {
    const errorMessage = 'Fetching was failed';
    const servicesMock = {
      apiService: {
        getRootFolder() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateSpy = (type, details) => {
      assert.step(type);
      if (type === 'GET_ROOT_FOLDER_MUTATOR_FAILED') {
        assert.equal(details.error, errorMessage, 'Should get folder after successfully fetching');
      }
    };
    const executor = new GetRootFolderExecutor();
    await executor.apply({}, servicesMock, {}, mutateSpy);
    assert.verifySteps(['GET_ROOT_FOLDER_MUTATOR_FAILED']);
  });
});
