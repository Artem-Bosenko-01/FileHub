import {GetRootFolderExecutor}
  from '../../../../../app/services/state-management/get-root-folder-action/get-root-folder-executor.js';

const {module, test} = QUnit;

module('Get root folder action executor', () => {
  test('Should apply executor', async (assert) => {
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
      if (folder === rootFolder) {
        assert.step(setRootFolderInStateStep);
      }
    };
    const executor = new GetRootFolderExecutor();
    await executor.apply({}, mockServices, {}, mutateMock);

    assert.verifySteps([getRootFolderStep, setRootFolderInStateStep]);
  });
});
