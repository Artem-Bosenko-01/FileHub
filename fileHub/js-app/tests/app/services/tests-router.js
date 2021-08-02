import {Router} from '../../../app/services/router.js';

const {module, test} = QUnit;
const DEFAULT_ROUTE = 'route';
const REGISTER_ROUTE = 'register';

module('Router', () => {
  test('Should show default page when hash is correct', (assert) => {
    const testWindow = new WindowMock('#register');

    const router = new Router(testWindow);
    router.onHashChanged((event) => {
      assert.step(event.target.location.hash.substring(1));
    });
    testWindow.dispatchEvent(new Event('hashchange'));
    assert.verifySteps([REGISTER_ROUTE], 'Should route to register page.');
  });

  test('Should router works correctly when hash is changed', (assert) => {
    assert.expect(3);

    const testWindow = new WindowMock('#route');

    const router = new Router(testWindow);
    router.onHashChanged((event) => {
      assert.step(event.target.location.hash.substring(1));
    });
    testWindow.dispatchEvent(new Event('hashchange'));
    router.redirect('register');
    testWindow.dispatchEvent(new Event('hashchange'));
    assert.verifySteps([DEFAULT_ROUTE, REGISTER_ROUTE], 'Should route to default page and after to register page.');
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
