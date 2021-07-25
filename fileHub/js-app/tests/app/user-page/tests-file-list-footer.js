import {FileListFooter} from '../../../app/user-page/file-list-footer.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;

module('File list footer', () => {
  test('Should render footer for user page', (assert) => {
    const fixture = document.getElementById('qunit-fixture');

    new FileListFooter(fixture);

    assert.ok(searchElement('footer', fixture), 'Should render footer component');
  });
});
