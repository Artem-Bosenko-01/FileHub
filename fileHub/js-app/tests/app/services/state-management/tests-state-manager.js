import {StateManager} from '../../../../app/services/state-management/state-manager.js';

const {module, test} = QUnit;

module('State manager', () => {
  test('Should dispatch special action', (assert) => {
    const actions = new ActionsMock(assert);
    const manager = new StateManager({}, {}, actions);

    manager.dispatch({typeName: 'event1'});
    manager.dispatch({typeName: 'event2'});

    assert.verifySteps(['event1', 'event2']);
  });
});

/**
 *
 */
class ActionsMock {
  /**
   * @constructor
   * @param {assert} assert
   */
  constructor(assert) {
    this._actions = new Map();
    this._actions.set('event1', {
      apply(action) {
        assert.step(action.typeName);
      },
    });

    this._actions.set('event2', {
      apply(action) {
        assert.step(action.typeName);
      },
    });
  }

  /**
   * @param {string} action
   * @returns {function}
   */
  getActionExecutor(action) {
    return this._actions.get(action);
  }
}
