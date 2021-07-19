import fetchMock from '../../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

module('Registration: API service', (hooks) => {
  hooks.afterEach(() => fetchMock.reset());

  const email = 'login';
  const password = 'password';
  const token = 'token';

  test('Should handle a response with code 200', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, {token});
    const apiService = new ApiService();
    const res = await apiService.registration(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.token, token, 'Should return token after successful query');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 404);
    const apiService = new ApiService();
    try {
      await apiService.registration(email, password);
    } catch (error) {
      assert.equal(error.message, '400: 404', 'Should return error with response status');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 500);
    const apiService = new ApiService();
    try {
      await apiService.registration(email, password);
    } catch (error) {
      assert.equal(error.message, '500: 500', 'Should return error with response status');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });

  test('Should handle a response with code 422', async (assert) => {
    assert.expect(4);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 422);
    const apiService = new ApiService();
    try {
      await apiService.registration(email, password);
    } catch (error) {
      assert.equal(error.message, 'This is 422 http failed response.', 'Should return error with message.');
      assert.equal(error.errors[0].field, 'email', 'Should return error with special field.');
      assert.equal(error.errors[0].message, 'test-message', 'Should return error with special message for field.');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });
});
