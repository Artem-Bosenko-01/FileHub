import {SearchBar} from '../../../app/user-page/search-bar.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('SearchBar', () => {
  test('Should  render search input panel with button', (assert) => {
    assert.expect(3);
    const fixture = document.getElementById('qunit-fixture');

    new SearchBar(fixture);

    const button = searchElement('search-button', fixture);
    assert.ok(searchElement('search-input-panel', fixture), 'Should render search input panel');
    assert.ok(button, 'Should render button near search panel');
    assert.equal(button.innerText, 'Search', 'Should render button with special title');
  });
});
