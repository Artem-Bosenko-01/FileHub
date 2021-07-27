import ActionExecutor from './action-execution.js';

/**
 * Execute fetching information about current folder where user located.
 */
export default class FetchCurrentFolderExecutor extends ActionExecutor {
  /**
   * @inheritDoc
   * @param {FetchCurrentFolder} actionInfo
   */
  apply(actionInfo) {
    const currentFolderId = actionInfo.state.currentFolderId;
    const folder = actionInfo.services.apiService.getFolder(currentFolderId);
    actionInfo.state.currentFolder = folder;
  }
}
