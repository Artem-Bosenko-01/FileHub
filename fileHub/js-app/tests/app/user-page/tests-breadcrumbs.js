import {Breadcrumbs} from '../../../app/user-page/breadcrumbs.js';
import {searchElement} from '../search-element-function.js';

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

    breadcrumbs.currentDirectory = currentDirectoryName;

    assert.ok(searchElement('breadcrumbs', fixture), 'Should render breadcrumbs');
    assert.ok(searchElement('current-dir', fixture), 'Should render tag for current folder');
    assert.equal(searchElement('current-dir-name', fixture).innerText, currentDirectoryName,
        'Should render folder name at breadcrumbs');
  });

  test('Should render breadcrumbs with error message', (assert) => {
    assert.expect(2);
    const errorMessage = 'Can\'t load breadcrumb data.';
    new Breadcrumbs(fixture);

    assert.ok(searchElement('breadcrumbs', fixture), 'Should render breadcrumbs');
    assert.equal(searchElement('breadcrumbs-error-message', fixture).innerText, errorMessage,
        'Should render error message at breadcrumbs');
  });
});
