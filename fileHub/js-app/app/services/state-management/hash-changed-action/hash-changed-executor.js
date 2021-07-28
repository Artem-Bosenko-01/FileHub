import ActionExecutor from '../action-execution.js';
import {HASH_CHANGED_MUTATOR} from '../mutator/hash-changed-mutator.js';

/**
 *
 */
export class HashChangedExecutor extends ActionExecutor {
  /**
   * @constructor
   * @param {HashChanged} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  apply(actionInfo, services, state, mutate) {
    const pageRoute = actionInfo.staticParam;
    const currentFolderId = actionInfo.dynamicParam;
    mutate(HASH_CHANGED_MUTATOR.COMPLETED, {pageRoute: pageRoute, dynamicPart: {
      currentFolderId: currentFolderId,
    }},
    );
  }
}
