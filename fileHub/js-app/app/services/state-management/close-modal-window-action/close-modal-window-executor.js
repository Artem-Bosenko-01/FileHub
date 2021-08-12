import ActionExecutor from '../action-execution.js';
import {MODAL_WINDOW_MUTATOR} from '../mutator/modal-window-mutator.js';

/**
 *
 */
export class CloseModalWindowExecutor extends ActionExecutor {
  /**
   * @param {CloseModalWindow} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function}dispatch
   */
  apply(actionInfo, services, state, mutate, dispatch) {
    mutate(MODAL_WINDOW_MUTATOR.CLOSE);
  }
}
