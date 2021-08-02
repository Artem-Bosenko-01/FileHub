import {FileListPage} from '../../../app/user-page/file-list-page.js';

const {module, test} = QUnit;

module('FileListPage', () => {
  test('Should render component for page', (assert) => {
    const fixture = document.getElementById('qunit-fixture');

    const testDocument = {title: ''};

    const testApiService = {};

    const testTitleService = {
      addTitleForPage() {
        testDocument.title = 'Main page - FileHub';
      },
    };

    new FileListPage(fixture, testApiService, testTitleService);

    assert.equal(testDocument.title, 'Main page - FileHub', 'Should add title to page');
  });
});
