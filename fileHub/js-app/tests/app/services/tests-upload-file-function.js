import {uploadFile} from '../../../app/services/upload-file-function.js';

const {module, test} = QUnit;

module('uploadFile', () => {
  test('Should get uploaded file', async (assert) => {
    const eventBus = new EventTarget();
    const fileName = 'file name';
    const inputElementMock = {
      addEventListener: function(name, listener) {
        eventBus.addEventListener(name, listener);
      },
      click: function() {
        eventBus.dispatchEvent(new Event('change'));
      },
      files: [fileName],
    };

    const documentMock = {
      createElement: function(tagName) {
        if (tagName === 'input') {
          return inputElementMock;
        }
        throw new Error();
      },
    };
    const file = await uploadFile(documentMock);
    assert.equal(file, fileName, 'Should get uploaded file');
  });
});
