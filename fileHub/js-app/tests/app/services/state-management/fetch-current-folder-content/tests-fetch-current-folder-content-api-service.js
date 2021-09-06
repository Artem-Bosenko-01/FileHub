import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';
import {getWindowMock} from '../../../mock-window-api-service.js';

const {module, test} = QUnit;

export default () => module('getFolderContent', () => {
  const folderContent = [{id: 'folder'}, {id: 'file'}];
  test('Should handle a response with code 200', async (assert) => {
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5/content',
      method: 'GET',
    }, {items: folderContent});
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    const result = await apiService.getFolderContent('5');

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.length, 2, 'Should get massive with 2 items');
    assert.equal(result[0].id, folderContent[0].id, 'Should get item with id');
    assert.equal(result[1].id, folderContent[1].id, 'Should get item with id');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/folder/5/content',
      method: 'GET',
    }, 400);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.getFolderContent('5');
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
      url: '/folder/5/content',
      method: 'GET',
    }, 500);
    const mockWindow = getWindowMock(fetch);
    const apiService = new ApiService(mockWindow);

    try {
      await apiService.getFolderContent('5');
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
