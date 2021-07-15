import {RoutingConfiguration} from '../../../app/services/routing-configuration.js';
import {Router} from '../../../app/services/router.js';

const {module, test} = QUnit;
const DEFAULT_ROUTE = 'route';
const REGISTER_ROUTE = 'register';
const ERROR_ROUTE = '404';

module('Router service', (hooks) => {
  test('Should show default page when hash is correctly', (assert) => {
    const testWindow = {
      location: {hash: '#register'},

      addEventListener(event, init) {
        init();
      },
    };

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([REGISTER_ROUTE, REGISTER_ROUTE], 'Should route to default page.');
  });

  test('Should show default page when hash is empty', (assert) => {
    const testWindow = {
      location: {hash: '#'},

      addEventListener(event, init) {
        init();
      },
    };

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([DEFAULT_ROUTE, DEFAULT_ROUTE], 'Should route to default page.');
  });

  test('Should show error page when hash is invalid', (assert) => {
    const testWindow = {
      location: {hash: '#router456'},

      addEventListener(event, init) {
        init();
      },
    };

    const configuration = getConfig(assert);
    new Router(configuration, testWindow);
    assert.verifySteps([ERROR_ROUTE, ERROR_ROUTE], 'Should route to error page.');
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

