import {FileListPage} from '../../../app/user-page/file-list-page.js';

const {module, test} = QUnit;

module('File list page', () => {
  test('Should render component for page', (assert) => {
    const fixture = document.getElementById('qunit-fixture');

    const testDocument = {title: ''};

    const testTitleService = {
      addTitleForPage() {
        testDocument.title = 'Main page - FileHub';
      },
    };

    const testServerManager = {
      state: {currentFolder: 'folder'},
    };

    new FileListPage(fixture, testTitleService, testServerManager);

    assert.equal(testDocument.title, 'Main page - FileHub', 'Should add title to page');
  });
});
