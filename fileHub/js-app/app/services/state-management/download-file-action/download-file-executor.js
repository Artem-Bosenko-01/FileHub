import ActionExecutor from '../action-execution.js';
import {DOWNLOAD_FILE_MUTATOR} from '../mutator/download-file-mutator.js';

/**
 * Execute downloading file from folder content.
 */
export class DownloadFileExecutor extends ActionExecutor {
  /**
   * @param {DownloadFile} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {

    mutate(DOWNLOAD_FILE_MUTATOR.FETCHING_STARTED, {downloadedFile: actionInfo.file.id});
    try {
      const file = await services.apiService.downloadFile(actionInfo.file);
      mutate(DOWNLOAD_FILE_MUTATOR.FETCHING_COMPLETED, {file});
    } catch (error) {
      mutate(DOWNLOAD_FILE_MUTATOR.FETCHING_FAILED, {downloadedFile: actionInfo.fileId, error: error.message});
    }
  }
}
