import ActionExecutor from '../action-execution.js';
import {MODAL_WINDOW_MUTATOR} from '../mutator/modal-window-mutator.js';

/**
 * Execute opening modal window process.
 */
export class OpenModalWindowExecutor extends ActionExecutor {
  /**
   * @param {OpenModalWindow} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function(actionInfo: ActionInfo)} dispatch
   */
  apply(actionInfo, services, state, mutate, dispatch) {
    mutate(MODAL_WINDOW_MUTATOR.OPEN, {item: actionInfo.item});
  }
}
