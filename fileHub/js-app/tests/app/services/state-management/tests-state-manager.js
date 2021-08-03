import {StateManager} from '../../../../app/services/state-management/state-manager.js';

const {module, test} = QUnit;

module('State manager', () => {
  test('Should dispatch special action', (assert) => {
    assert.expect(3);
    const firstActionName = 'event1';
    const secondActionName = 'event2';
    const actions = new ActionsMock(assert);
    const manager = new StateManager({}, {}, actions);

    manager.dispatch({typeName: firstActionName});
    manager.dispatch({typeName: secondActionName});

    assert.verifySteps([firstActionName, secondActionName]);
  });

  test('Should add action on change field', (assert) => {
    assert.expect(2);
    const fieldName = 'filed name';
    const changedField = 'field';

    const mutatorMock = (mutatorName, details) => {
      if (mutatorName === 'CHANGE_VALUE') {
        return {field: details.fieldName};
      }
    };

    const actions = new ActionsMock(assert);
    const manager = new StateManager({}, {}, actions, mutatorMock);

    manager.onStateChanged(changedField, (state) => {
      assert.step(state.field);
    });

    manager.dispatch({typeName: changedField, fieldName});
    assert.verifySteps([fieldName]);
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
    this._actions = new Map()
        .set('event1', {
          apply(action) {
            assert.step(action.typeName);
          },
        })
        .set('event2', {
          apply(action) {
            assert.step(action.typeName);
          },
        })
        .set('field', {
          apply(action, {}, {}, mutator) {
            mutator('CHANGE_VALUE', {fieldName: action.fieldName});
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
