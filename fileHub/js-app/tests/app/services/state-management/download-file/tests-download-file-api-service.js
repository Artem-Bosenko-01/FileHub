import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('downloadFile', () => {
  test('Should handle a response with code 200', async (assert) => {
    const id = 'folder';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/file/5',
      method: 'GET',
    }, {id});
    const apiService = new ApiService({fetch});
    const result = await apiService.downloadFile( '5');
    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.id, id, 'Should get id of uploaded file');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/file/5',
      method: 'GET',
    }, 400);
    const apiService = new ApiService({fetch});

    try {
      await apiService.downloadFile( '5');
    } catch (error) {
      assert.equal(error.message, '400: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/file/5',
      method: 'GET',
    }, 500);
    const apiService = new ApiService({fetch});

    try {
      await apiService.downloadFile( '5');
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
