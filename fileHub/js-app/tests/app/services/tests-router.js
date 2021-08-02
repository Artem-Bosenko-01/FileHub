import {RoutingConfiguration} from '../../../app/services/routing-configuration.js';
import {Router} from '../../../app/services/router.js';

const {module, test} = QUnit;
const DEFAULT_ROUTE = 'route';
const REGISTER_ROUTE = 'register';
const ERROR_ROUTE = '404';

module('Router', () => {
  test('Should show default page when hash is correct', (assert) => {
    const testWindow = new WindowMock('#register');

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([REGISTER_ROUTE], 'Should route to register page.');
  });

  test('Should show default page when hash is empty', (assert) => {
    const testWindow = new WindowMock('#');

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([DEFAULT_ROUTE], 'Should route to default page.');
  });

  test('Should show error page when hash is invalid', (assert) => {
    const testWindow = new WindowMock('#route456');

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([ERROR_ROUTE], 'Should route to error page.');
  });

  test('Should router works correctly when hash is changed', (assert) => {
    assert.expect(3);
    const configuration = getConfig(assert);

    const testWindow = new WindowMock('#');

    new Router(configuration, testWindow);
    testWindow.hash = '#register';
    testWindow.dispatchEvent(new Event('hashchange'));
    assert.verifySteps([DEFAULT_ROUTE, REGISTER_ROUTE], 'Should route to default page and after to register page.');
  });
});

/**
 * Gets configuration for routers.
 * @param {any} assert
 * @returns {RoutingConfiguration}
 */
function getConfig(assert) {
  const configuration = new RoutingConfiguration(DEFAULT_ROUTE)
      .addRoute(DEFAULT_ROUTE, () => assert.step(DEFAULT_ROUTE))
      .addRoute(REGISTER_ROUTE, () => assert.step(REGISTER_ROUTE))
      .addRoute(ERROR_ROUTE, () => assert.step(ERROR_ROUTE));
  configuration.notFoundRoute = '404';
  return configuration;
}

/**
 * Custom object, which behaviour looks like window object.
 */
class WindowMock extends EventTarget {
  /**
   * @constructor
   * @param {string} hash
   */
  constructor(hash) {
    super();
    this._location = {hash: hash};
  }

  /**
   * Hash for location.
   * @param {string} value
   */
  set hash(value) {
    this._location = {hash: value};
  }

  /**
   * Location of window.
   * @returns {{hash: string}}
   */
  get location() {
    return this._location;
  }
}
