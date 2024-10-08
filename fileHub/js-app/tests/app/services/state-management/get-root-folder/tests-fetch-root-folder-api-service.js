import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';
import {getWindowMock} from '../../../mock-window-api-service.js';

const {module, test} = QUnit;

export default () => module('getRootFolder', () => {
  test('Should handle a response with code 200', async (assert) => {
    const rootFolder = {id: 'folder'};
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/root-folder',
      method: 'GET',
    }, {folder: rootFolder});
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    const result = await apiService.getRootFolder();

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.id, rootFolder.id, 'Should get root item with id');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/root-folder',
      method: 'GET',
    }, 400);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.getRootFolder();
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
      url: '/root-folder',
      method: 'GET',
    }, 500);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.getRootFolder();
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
