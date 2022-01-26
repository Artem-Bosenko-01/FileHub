import {ModalsService} from '../../../app/services/modals/modals-service.js';

const {module, test} = QUnit;

module('ModalsService', () => {
  test('Should return container for initializing window when open() is called.', (assert) => {
    const baseContainerMock = {
      default: 'default value',
    };

    const service = new ModalsService(baseContainerMock);
    service.open((container) => {
      assert.deepEqual(container, baseContainerMock, 'Should get base container');
    });
  });

  test('Should return \'dialog\' element when close() is called.', (assert) => {
    const closeButtonMock ={
      remove: function() {},
    };
    const baseContainerMock = {
      querySelector: function(selector) {
        assert.equal(selector, '[data-fh="dialog"]', 'Should get selector');
        return closeButtonMock;
      },
    };

    const service = new ModalsService(baseContainerMock);
    service.close();
  });
});
