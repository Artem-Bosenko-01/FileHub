import ActionExecutor from '../action-execution.js';
import {SELECT_ITEM_MUTATOR} from '../mutator/select-item-mutator.js';

/**
 * Execute selecting item.
 */
export class SelectItemExecutor extends ActionExecutor {
  /**
   * @param {SelectItem} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function} dispatch
   */
  apply(actionInfo, services, state, mutate, dispatch) {
    mutate(SELECT_ITEM_MUTATOR.SET, {itemId: actionInfo.id});
  }
}
