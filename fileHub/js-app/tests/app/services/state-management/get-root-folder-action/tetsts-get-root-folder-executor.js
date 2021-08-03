import {GetRootFolderExecutor}
  from '../../../../../app/services/state-management/get-root-folder-action/get-root-folder-executor.js';

const {module, test} = QUnit;

module('Get root folder action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
    const getRootFolderStep = 'Get root folder request';
    const setRootFolderInStateStep = 'Set root folder';
    const rootFolder = 'root folder';
    const mockServices = {
      apiService: {
        getRootFolder() {
          assert.step(getRootFolderStep);
          return rootFolder;
        },
      },
    };

    const mutateMock = (type, folder) => {
      if (folder.rootFolder === rootFolder) {
        assert.step(setRootFolderInStateStep);
      }
    };
    const executor = new GetRootFolderExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);

    assert.verifySteps([getRootFolderStep, setRootFolderInStateStep]);
  });

  test('Should  fail apply executor', async (assert) => {
    const errorMessage = 'Fetching was failed';
    const mockServices = {
      apiService: {
        getRootFolder() {
          throw new Error(errorMessage);
        },
      },
    };

    const mutateMock = (type, details) => {
      if (type === 'GET_ROOT_FOLDER_MUTATOR_FAILED') {
        assert.equal(details.error, errorMessage, 'Should get error message after fetching fail');
      }
    };
    const executor = new GetRootFolderExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);
  });
});
