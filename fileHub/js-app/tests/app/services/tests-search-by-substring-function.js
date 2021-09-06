import {search} from '../../../app/services/search-by-substring.js';

const {module, test} = QUnit;

module('search', () => {
  test('Should filter massive of items by substring', (assert) => {
    const query = 'line';
    const massive = [
      {type: 'folder', name: 'inline', itemsAmount: 44},
      {type: 'file', name: 'name', mimeType: 'type', size: 474},
    ];

    const filteredMassive = search(query, massive);

    assert.deepEqual(filteredMassive, [{type: 'folder', name: 'inline', itemsAmount: 44}],
        'Should get line, that contains query string at fields of object');
  });

  test('Should get massive when query string id empty.', (assert) => {
    const query = '';
    const massive = [
      {type: 'folder', name: 'inline', itemsAmount: 44},
      {type: 'file', name: 'name', mimeType: 'type', size: 474},
    ];

    const filteredMassive = search(query, massive);

    assert.deepEqual(filteredMassive, massive, 'Should get massive');
  });

  test('Should throw message when massive that needs to filter is empty.', (assert) => {
    const query = 'query';
    const massive = [];

    assert.throws(() => search(query, massive));
  });
});
