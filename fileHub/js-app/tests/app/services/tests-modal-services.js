import {ModalsService} from '../../../app/services/modals/modals-service.js';

const {module, test} = QUnit;

module('ModalsService', () => {
  test('Should be called the open method and get a base container', (assert) => {
    const baseContainerMock = {
      default: 'default value',
    };

    const service = new ModalsService(baseContainerMock);
    service.open((container) => {
      assert.deepEqual(container, baseContainerMock, 'Should get base container');
    });
  });

  test('Should be called open method and get a base container', (assert) => {
    const closeButtonMock ={
      dispatchEvent: function(event) {},
    };
    const baseContainerMock = {
      querySelector: function(selector) {
        assert.equal(selector, '[data-fh="close-button"]', 'Should get selector');
        return closeButtonMock;
      },
    };

    const service = new ModalsService(baseContainerMock);
    service.close();
  });
});
