import ActionExecutor from '../action-execution.js';

/**
 *
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
    actionInfo.redirect(`index/${rootFolder.itemId}`);
  }
}
