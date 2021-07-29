import ActionExecutor from '../action-execution.js';
import {CURRENT_FOLDER_MUTATOR} from '../mutator/fetch-current-folder-mutator.js';

/**
 * Execute fetching information about current folder where user located.
 */
export default class FetchCurrentFolderExecutor extends ActionExecutor {
  /**
   * @inheritDoc
   * @param {FetchCurrentFolder} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  async apply(actionInfo, services, state, mutate) {
    const currentFolderId = state.locationParams.currentFolderId;
    try {
      mutate(CURRENT_FOLDER_MUTATOR.FETCHING_STARTED);
      const folder = await services.apiService.getFolder(currentFolderId);
      state.currentFolder = folder;
      mutate(CURRENT_FOLDER_MUTATOR.FETCHING_COMPLETED, {folder});
    } catch (error) {
      mutate(CURRENT_FOLDER_MUTATOR.FETCHING_FAILED, {error});
    }
  }
}
