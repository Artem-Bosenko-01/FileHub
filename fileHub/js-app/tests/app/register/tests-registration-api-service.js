import fetchMock from '../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('Registration', (hooks) => {
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
    const res = await apiService.register(email, password);
    assert.ok(fetchMock.called(), 'Should call mock for fetch');
    assert.equal(res.token, token, 'Should return token after successful query');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, {
      status: 404,
      body: {
        message: 'client error',
      }});
    const apiService = new ApiService();
    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, '400: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, {
      status: 500,
      body: {
        message: 'server error',
      }});
    const apiService = new ApiService();
    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, '500: server error', 'Should return error with response status');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });

  test('Should handle a response with code 422', async (assert) => {
    assert.expect(4);
    fetchMock.mock({
      url: '/register',
      method: 'POST',
    }, {
      status: 422,
      body: [{
        field: 'email',
        message: 'this is message',
      }]});
    const apiService = new ApiService();
    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, 'This is 422 http failed response.', 'Should return error with message.');
      assert.equal(error.errors[0].field, 'email', 'Should return error with special field.');
      assert.equal(error.errors[0].message, 'this is message', 'Should return error with special message for field.');
    } finally {
      assert.ok(fetchMock.called(), 'Should call mock for fetch');
    }
  });
});
