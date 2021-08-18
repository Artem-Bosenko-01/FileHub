import ActionExecutor from '../action-execution.js';
import {LOG_OUT_USER_MUTATOR} from '../mutator/log-out-user-mutator.js';

/**
 * Execute logging out user and stopping current user session.
 */
export class LogOutUserExecutor extends ActionExecutor {
  /**
   * @param {LogOutUser} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   * @param {function} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    try {
      await services.apiService.logOut();
      mutate(LOG_OUT_USER_MUTATOR.COMPLETED);
    } catch (error) {
    }
  }
}
