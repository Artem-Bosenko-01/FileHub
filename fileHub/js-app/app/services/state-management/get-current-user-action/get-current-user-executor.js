import ActionExecutor from '../action-execution.js';
import {GET_CURRENT_USER_MUTATOR} from '../mutator/get-current-user-mutator.js';

/**
 *
 */
export default class GetCurrentUserExecutor extends ActionExecutor {
  /**
   * @inheritDoc
   * @param {GetCurrentUser} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  async apply(actionInfo, services, state, mutate) {
    mutate(GET_CURRENT_USER_MUTATOR.FETCHING_STARTED);
    try {
      const currentUserInfo = await services.apiService.getCurrentUser();
      mutate(GET_CURRENT_USER_MUTATOR.FETCHING_COMPLETED, {user: currentUserInfo});
    } catch (error) {
      mutate(GET_CURRENT_USER_MUTATOR.FETCHING_FAILED, {error: error.message});
    }
  }
}
