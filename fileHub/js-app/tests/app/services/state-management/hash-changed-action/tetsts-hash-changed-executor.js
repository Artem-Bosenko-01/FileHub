import {HashChangedExecutor}
  from '../../../../../app/services/state-management/hash-changed-action/hash-changed-executor.js';

const {module, test} = QUnit;

module('Hash changed action executor', () => {
  test('Should successfully apply executor', async (assert) => {
    assert.expect(3);
    const staticParamMock = 'static';
    const dynamicParamMock = 'dynamic';
    const setLocationStep = 'Set location in state';
    const setLocationParamStep = 'Set location param in state';

    const mutateMock = (type, details) => {
      if (details) {
        if (details.pageRoute === staticParamMock) {
          assert.step(setLocationStep);
        }
        if (details.dynamicPart.currentFolderId === dynamicParamMock) {
          assert.step(setLocationParamStep);
        }
      }
    };

    const actionInfoMock = {
      staticParam: staticParamMock,
      dynamicParam: dynamicParamMock,
    };

    const executor = new HashChangedExecutor();
    executor.apply(actionInfoMock, {}, {}, mutateMock);
    assert.verifySteps([setLocationStep, setLocationParamStep]);
  });
});
