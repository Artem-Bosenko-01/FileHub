import fetchMock from '../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

export default () => module('Registration', () => {
  const email = 'login';
  const password = 'password';

  test('Should handle a response with code 200', async (assert) => {
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/register',
      method: 'POST',
    }, 200);
    const apiService = new ApiService({fetch});

    await apiService.register(email, password);
    assert.ok(fetch.called(), 'Should send a request');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/register',
      method: 'POST',
    }, {
      status: 404,
      body: {
        message: 'client error',
      }});
    const apiService = new ApiService({fetch});
    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, '404: client error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 500', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/register',
      method: 'POST',
    }, 500);
    const apiService = new ApiService({fetch});
    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });

  test('Should handle a response with code 422', async (assert) => {
    assert.expect(4);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/register',
      method: 'POST',
    }, {
      status: 422,
      body: {
        errors: [{
          field: 'email',
          message: 'this is message',
        }],
      }});
    const apiService = new ApiService({fetch});

    try {
      await apiService.register(email, password);
    } catch (error) {
      assert.equal(error.message, 'This is 422 http failed response.', 'Should return error with message.');
      assert.equal(error.errors[0].field, 'email', 'Should return error with special field.');
      assert.equal(error.errors[0].message, 'this is message', 'Should return error with special message for field.');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
