import ActionExecutor from './action-execution.js';

/**
 * Execute fetching information about current folder where user located.
 */
export default class FetchCurrentFolderExecutor extends ActionExecutor {
  /**
   * @inheritDoc
   * @param {FetchCurrentFolder} actionInfo
   * @param {object} services
   * @param {object} state
   */
  apply(actionInfo, services, state ) {
    const currentFolderId = state.currentFolderId;
    const folder = services.apiService.getFolder(currentFolderId);
    state.currentFolder = folder;
  }
}
