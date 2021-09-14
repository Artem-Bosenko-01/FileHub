import ActionExecutor from '../action-execution.js';
import {UPLOAD_FILE_MUTATOR} from '../mutator/upload-file-mutator.js';
import {FetchCurrentFolderContent} from '../fetch-current-folder-content-action/fetch-current-folder-content.js';

/**
 * Execute saving file to the backend.
 */
export class UploadFileExecutor extends ActionExecutor {
  /**
   * @param {UploadFile} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function(actionInfo: ActionInfo)} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    mutate(UPLOAD_FILE_MUTATOR.FETCHING_STARTED);
    try {
      await services.apiService.uploadFile(actionInfo.file, actionInfo.parentFolderId);
      mutate(UPLOAD_FILE_MUTATOR.FETCHING_COMPLETED);
      dispatch(new FetchCurrentFolderContent());
    } catch (error) {
      mutate(UPLOAD_FILE_MUTATOR.FETCHING_FAILED, {error: error.message});
    }
  }
}
