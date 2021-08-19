import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('deleteFile', () => {
  test('Should handle a response with code 200', async (assert) => {
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5',
      method: 'DELETE',
    }, 200);
    const apiService = new ApiService({fetch});
    await apiService.deleteFolder('5');
    assert.ok(fetch.called(), 'Should send a request');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5',
      method: 'DELETE',
    }, 400);
    const apiService = new ApiService({fetch});

    try {
      await apiService.deleteFolder('5');
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
      url: '/folder/5',
      method: 'DELETE',
    }, 500);
    const apiService = new ApiService({fetch});

    try {
      await apiService.deleteFolder('5');
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
