import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('createFolder', () => {
  test('Should handle a response with code 200', async (assert) => {
    const name = 'folder';
    const parentId = 'id';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${parentId}/folder`,
      method: 'POST',
    }, {name});
    const apiService = new ApiService({fetch});
    const result = await apiService.createFolder(name, parentId);
    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.name, name, 'Should get name of created folder');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const parentId = '8';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${parentId}/folder`,
      method: 'POST',
    }, 400);
    const apiService = new ApiService({fetch});

    try {
      await apiService.createFolder( '5', parentId);
    } catch (error) {
      assert.equal(error.message, '400: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    const parentId = '8';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${parentId}/folder`,
      method: 'POST',
    }, 500);
    const apiService = new ApiService({fetch});

    try {
      await apiService.createFolder( '5', parentId);
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
