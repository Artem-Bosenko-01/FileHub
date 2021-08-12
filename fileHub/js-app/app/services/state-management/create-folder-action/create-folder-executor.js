import ActionExecutor from '../action-execution.js';
import {FetchCurrentFolderContent} from '../fetch-current-folder-content-action/fetch-current-folder-content.js';
import {CREATE_FOLDER_MUTATOR} from '../mutator/create-folder-mutator.js';

/**
 *
 */
export class CreateFolderExecutor extends ActionExecutor {
  /**
   * @param {CreateFolder} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName:string)} mutate
   * @param {function(actionInfo: ActionInfo)} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    mutate(CREATE_FOLDER_MUTATOR.STARTED);
    const {folderName, parentFolderId} = actionInfo;
    try {
      await services.apiService.createFolder(folderName, parentFolderId);
      mutate(CREATE_FOLDER_MUTATOR.COMPLETED);
      dispatch(new FetchCurrentFolderContent());
    } catch (error) {
      mutate(CREATE_FOLDER_MUTATOR.FAILED, {error: error.message});
    }
  }
}
