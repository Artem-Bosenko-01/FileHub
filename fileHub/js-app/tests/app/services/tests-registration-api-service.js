import fetchMock from '../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../app/services/api-service.js';

const {module, test} = QUnit;

module('Registration: API service', (hooks) => {
  hooks.afterEach(() => fetchMock.reset());

  const apiService = new ApiService();
  const email = 'login';
  const password = 'password';
  const token = 'token';

  test('Should create query with valid response status', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 200);
    const res = await apiService.registration(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.token, token, 'Should return token after successful query');
  });

  test('Should create query with response status 4**', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 404);
    const res = await apiService.registration(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.message, '400: 404', 'Should return error with response status');
  });

  test('Should create query with response status 500', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 500);
    const res = await apiService.registration(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.message, '500: 500', 'Should return error with response status');
  });

  test('Should create query with response status 422', async (assert) => {
    assert.expect(3);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, 422);
    const res = await apiService.registration(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.errors, [{_field: 'email', _message: 'test-message'}], 'Should return error with special field');
    assert.equal(res.message, 'This is 422 http failed response.', 'Should return error with message');
  });
});
