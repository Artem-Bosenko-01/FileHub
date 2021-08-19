import fetchMock from '../../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('getCurrentUser', () => {
  test('Should handle a response with code 200', async (assert) => {
    const userDetails = {id: 'id', name: 'name'};
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/user',
      method: 'GET',
    }, {id: 'id', name: 'name'});
    const apiService = new ApiService({fetch});
    const result = await apiService.getCurrentUser();

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(result.id, userDetails.id, 'Should get user id');
    assert.equal(result.name, userDetails.name, 'Should get user name');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/user',
      method: 'GET',
    }, 400);
    const apiService = new ApiService({fetch});

    try {
      await apiService.getCurrentUser();
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
      url: '/user',
      method: 'GET',
    }, 500);
    const apiService = new ApiService({fetch});

    try {
      await apiService.getCurrentUser();
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
