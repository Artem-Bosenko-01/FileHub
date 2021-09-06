import fetchMock from '../../../node_modules/fetch-mock/esm/client.js';
import {ApiService} from '../../../app/services/api-service/api-service.js';
import {getWindowMock} from '../mock-window-api-service.js';

const {module, test} = QUnit;

export default () => module('Authentication', () => {
  const email = 'login';
  const password = 'password';
  const token = 'token';

  test('Should handle a response with code 200', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    const mockWindow = getWindowMock(fetch);
    fetch.mock({
      url: '/login',
      method: 'POST',
    }, {token});
    const apiService = new ApiService(mockWindow);
    await apiService.logIn(email, password);

    assert.ok(fetch.called(), 'Should send a request');
    assert.equal(mockWindow.localStorage.getItem(), token, 'Should return token after successful response');
  });

  test('Should handle a response with code 4**', async (assert) => {
    assert.expect(2);
    const fetch = fetchMock.sandbox();
    fetch.mock({
      url: '/login',
      method: 'POST',
    }, {
      status: 404,
      body: {
        message: 'client error',
      },
    });
    const apiService = new ApiService({fetch});

    try {
      await apiService.logIn(email, password);
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
      url: '/login',
      method: 'POST',
    }, 500);
    const apiService = new ApiService({fetch});

    try {
      await apiService.logIn(email, password);
    } catch (error) {
      assert.equal(error.message, '500: Server Error', 'Should return error with response status');
    } finally {
      assert.ok(fetch.called(), 'Should send a request');
    }
  });
});
