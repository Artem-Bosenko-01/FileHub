import {Router} from '../../../app/services/router.js';

const {module, test} = QUnit;
const DEFAULT_ROUTE = 'route';
const REGISTER_ROUTE = 'register';

module('Router', () => {
  test('Should add and call event listener on route change', (assert) => {
    const testWindow = new WindowMock(`#${REGISTER_ROUTE}`);

    const router = new Router(testWindow);
    router.onRouteChanged((url) => {
      assert.equal(url, REGISTER_ROUTE, 'Should get route where user are located now.');
    });
    testWindow.dispatchEvent(new Event('hashchange'));
  });

  test('Should change location when redirect method is called', (assert) => {
    assert.expect(2);
    const testWindow = new WindowMock(DEFAULT_ROUTE);
    assert.equal(DEFAULT_ROUTE, testWindow.location.hash, 'Should stay at default route.');

    const router = new Router(testWindow);
    router.redirect(REGISTER_ROUTE);
    assert.equal(REGISTER_ROUTE, testWindow.location.hash, 'Should redirect to register route.');
  });
});

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
   * Location of window.
   * @returns {{hash: string}}
   */
  get location() {
    return this._location;
  }
}
