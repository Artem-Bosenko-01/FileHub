import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';
import {getWindowMock} from '../../../mock-window-api-service.js';

const {module, test} = QUnit;

export default () => module('renameFolder', () => {
  test('Should handle a response with code 200', async (assert) => {
    const item = {id: '6'};
    const newName = 'new name';
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${item.id}`,
      method: 'PUT',
    }, {name: newName});
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);
    const result = await apiService.renameFolder(item, newName);

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.name, newName, 'Should get new name of renamed folder');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const item = {id: '6'};
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${item.id}`,
      method: 'PUT',
    }, 400);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.renameFolder(item, 'name');
    } catch (error) {
      assert.equal(error.message, '400: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    const item = {id: '6'};
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: `/folder/${item.id}`,
      method: 'PUT',
    }, 500);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.renameFolder(item, 'name');
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
