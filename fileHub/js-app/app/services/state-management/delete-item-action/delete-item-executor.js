import ActionExecutor from '../action-execution.js';
import {DELETE_ITEM_MUTATOR} from '../mutator/delete-item-mutator.js';
import {FetchCurrentFolderContent} from '../fetch-current-folder-content-action/fetch-current-folder-content.js';

/**
 * Execute deleting file from folder content.
 */
export class DeleteItemExecutor extends ActionExecutor {
  /**
   * @param {DeleteItem} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   * @param {function} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    mutate(DELETE_ITEM_MUTATOR.FETCHING_STARTED, {removingFile: actionInfo.item});
    try {
      const {type, id} = actionInfo.item;

      if (type === 'folder') {
        await services.apiService.deleteFolder(id);
      } else {
        await services.apiService.deleteFile(id);
      }
      mutate(DELETE_ITEM_MUTATOR.FETCHING_COMPLETED);
      dispatch(new FetchCurrentFolderContent());
    } catch (error) {
      mutate(DELETE_ITEM_MUTATOR.FETCHING_FAILED, {error: error.message});
    }
  }
}
