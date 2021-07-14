import fetchMock from '../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../app/services/api-service.js';

const {module, test} = QUnit;

module('API service', () => {
  const apiService = new ApiService();

  test('Should test service', async (assert) => {
    const email = 'login';
    const password = 'password';
    fetchMock.mock({
      url: '/login',
      method: 'POST',
    }, 200);
    const res = await apiService.logIn(email, password);
    assert.ok(fetchMock.called());
  });
});
