import {StateManager} from '../../../../app/services/state-management/state-manager.js';

const {module, test} = QUnit;

module('State manager', () => {
  test('Should dispatch special action', (assert) => {
    assert.expect(3);
    const actions = new ActionsMock(assert);
    const manager = new StateManager({}, {}, actions);

    manager.dispatch({typeName: 'event1'});
    manager.dispatch({typeName: 'event2'});

    assert.verifySteps(['event1', 'event2']);
  });

  test('Should add action on change field', (assert) => {
    assert.expect(2);
    const actions = new ActionsMock(assert);
    const manager = new StateManager({}, {}, actions);
    manager.onStateChanged('field', (state) => assert.step(state.field));
    manager.state.field = 'changes';

    assert.verifySteps(['changes']);
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
