import ActionExecutor from '../action-execution.js';
import {FETCH_CURRENT_FOLDER_CONTENT_MUTATOR} from '../mutator/fetch-current-folder-content-mutator.js';

/**
 * Execute fetching information about current folder content where user located.
 */
export class FetchCurrentFolderContentExecutor extends ActionExecutor {
  /**
   *
   * @param {FetchCurrentFolderContent} actionInfo
   * @param {{ApiService}} services
   * @param {object} state
   * @param {function} mutate
   */
  async apply(actionInfo, services, state, mutate) {
    const currentFolderId = state.locationParams.currentFolderId;
    try {
      mutate(FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_STARTED);
      const folderContent = await services.apiService.getFolderContent(currentFolderId);
      mutate(FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_COMPLETED, {folderContent});
    } catch (error) {
      mutate(FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_FAILED, {error: error.message});
    }
  }
}
