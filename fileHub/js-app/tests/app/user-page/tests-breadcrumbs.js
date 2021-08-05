import {Breadcrumbs} from '../../../app/user-page/breadcrumbs.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('Breadcrumbs', (hooks) => {
  let fixture;
  hooks.beforeEach(() => {
    fixture = document.getElementById('qunit-fixture');
  });

  test('Should render breadcrumbs with directories path', (assert) => {
    assert.expect(3);
    const currentDirectoryName = 'Directory';
    const breadcrumbs = new Breadcrumbs(fixture);

    breadcrumbs.currentDirectory = {
      name: currentDirectoryName,
    };

    assert.ok(searchElement('breadcrumbs', fixture), 'Should render breadcrumbs');
    assert.ok(searchElement('current-dir', fixture), 'Should render tag for current folder');
    assert.equal(searchElement('current-dir-name', fixture).innerText, currentDirectoryName,
        'Should render folder name at breadcrumbs');
  });

  test('Should render breadcrumbs with error message', (assert) => {
    assert.expect(2);
    const errorMessage = 'Can\'t load breadcrumb data.';
    const breadcrumbs = new Breadcrumbs(fixture);
    breadcrumbs.errorMessage = 'error';

    assert.ok(searchElement('breadcrumbs', fixture), 'Should render breadcrumbs');
    assert.equal(searchElement('breadcrumbs-error-message', fixture).innerText, errorMessage,
        'Should render error message at breadcrumbs');
  });

  test('Should render breadcrumbs with loading state', (assert) => {
    assert.expect(2);
    const breadcrumbs = new Breadcrumbs(fixture);
    breadcrumbs.loadingCurrentFolderDataState = true;

    assert.ok(searchElement('breadcrumbs', fixture), 'Should render breadcrumbs');
    assert.ok(searchElement('loading-symbol', fixture), 'Should render loading symbol at breadcrumbs');
  });

  test('Should add navigate event to breadcrumbs', (assert) => {
    const currentDirectoryName = 'Directory';
    const parentId = 'as54';
    const breadcrumbs = new Breadcrumbs(fixture);
    breadcrumbs.currentDirectory = {
      name: currentDirectoryName,
      parentFolderId: parentId,
      type: 'folder',
    };
    breadcrumbs.onFolderNameClick((folderId) => assert.strictEqual(folderId, parentId,
        'Should get parent folder id'));

    const previousFolderLink = searchElement('previous-folder', fixture);
    previousFolderLink.dispatchEvent(new Event('click'));
  });
});
