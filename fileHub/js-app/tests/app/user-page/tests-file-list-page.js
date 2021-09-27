import {FileListPage} from '../../../app/file-list/file-list-page.js';

const {module, test} = QUnit;

module('FileListPage', () => {
  test('Should render component for page', (assert) => {
    const fixture = document.getElementById('qunit-fixture');

    const documentMock = {title: ''};

    const titleServiceMock = {
      addTitleForPage() {
        documentMock.title = 'Main page - FileHub';
      },
    };

    const stateManagerMock = {
      state: {currentFolder: 'folder'},
      onStateChanged() {
      },
    };

    new FileListPage(fixture, titleServiceMock, stateManagerMock);

    assert.equal(documentMock.title, 'Main page - FileHub', 'Should add title to page');
  });
});
