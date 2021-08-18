import ActionExecutor from '../action-execution.js';
import {SEARCHING_MUTATOR} from '../mutator/searching-mutator.js';
import {search} from '../../search-by-substring.js';

/**
 *
 */
export class SearchInfoExecutor extends ActionExecutor {
  /**
   * @param {SearchInfo} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)}mutate
   * @param {function} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    const searchLine = actionInfo.searchLine;
    mutate(SEARCHING_MUTATOR.STARTED);
    try {
      const currentFolderContent = state.currentFolderContent;
      const content = search(searchLine, currentFolderContent);
      mutate(SEARCHING_MUTATOR.COMPLETED, {content});
    } catch (error) {
      mutate(SEARCHING_MUTATOR.FAILED, {error: error.message});
    }
  }
}
