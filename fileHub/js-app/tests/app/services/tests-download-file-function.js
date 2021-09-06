import {downloadFile} from '../../../app/services/download-file-function.js';

const {module, test} = QUnit;

module('downloadFile', () => {
  test('Should  set downloaded file in link', (assert) => {
    const name = 'name';
    const mimeType = 'mimeType';
    const content = 'content';
    const fileContent = {
      name: name,
      mimeType: mimeType,
      content: content,
    };

    const linkMock = {
      href: '',
      download: '',
      click: function() {
      },
    };

    const documentMock = {
      createElement: function(tagName) {
        if (tagName === 'a') {
          return linkMock;
        }
        throw new Error();
      },
    };
    downloadFile(documentMock, fileContent);

    assert.ok(linkMock.href, 'Should get object url with content and special mime type',
    );
    assert.deepEqual(linkMock.download, name, 'Should get file name to link download name');
  });
});
