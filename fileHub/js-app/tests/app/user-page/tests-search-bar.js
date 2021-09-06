import {SearchBar} from '../../../app/file-list/search-bar.js';
import searchElement from '../search-element-function.js';

const {module, test} = QUnit;

module('SearchBar', () => {
  test('Should render search input panel with button', (assert) => {
    assert.expect(3);
    const fixture = document.getElementById('qunit-fixture');

    new SearchBar(fixture);

    const button = searchElement('search-button', fixture);
    assert.ok(searchElement('search-input-panel', fixture), 'Should render search input panel');
    assert.ok(button, 'Should render button near search panel');
    assert.equal(button.innerText, 'Search', 'Should render button with special title');
  });

  test('Should add loading status to button at search bar', (assert) => {
    const fixture = document.getElementById('qunit-fixture');

    const searchBar = new SearchBar(fixture);
    searchBar.isSearchingLoading = true;

    const button = searchElement('search-button', fixture);
    assert.equal(button.innerHTML, '<span class="glyphicon glyphicon-repeat loading"></span>Search',
        'Should render loading symbol near button\'s title');
  });

  test('Should add and call listener on search button click', (assert) => {
    const query = 'search query';
    const fixture = document.getElementById('qunit-fixture');

    const searchBar = new SearchBar(fixture);
    searchBar.onSearchSubmit((searchLine) => assert.equal(searchLine, query));
    const inputLine = searchElement('search-input-panel', fixture);
    inputLine.value = query;

    const button = searchElement('search-button', fixture);
    button.click();
  });
});
