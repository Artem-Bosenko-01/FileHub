import ActionExecutor from '../action-execution.js';
import {GET_ROOT_FOLDER_MUTATOR} from '../mutator/get-root-folder-mutator.js';

/**
 * Execute fetching information about root folder.
 */
export class GetRootFolderExecutor extends ActionExecutor {
  /**
   *
   * @param {GetRootFolder} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  async apply(actionInfo, services, state, mutate) {
    const rootFolder = await services.apiService.getRootFolder();
    mutate(GET_ROOT_FOLDER_MUTATOR.COMPLETED, rootFolder);
  }
}
