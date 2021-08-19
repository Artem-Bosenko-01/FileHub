import ActionExecutor from '../action-execution.js';
import {FetchCurrentFolderContent} from '../fetch-current-folder-content-action/fetch-current-folder-content.js';
import {RENAME_ITEM_MUTATOR} from '../mutator/rename-item-mutator.js';

/**
 * Execute renaming item.
 */
export class RenameItemExecutor extends ActionExecutor {
  /**
   * @param {RenameItem} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function(mutatorName: string)} mutate
   * @param {function(ActionInfo)} dispatch
   */
  async apply(actionInfo, services, state, mutate, dispatch) {
    mutate(RENAME_ITEM_MUTATOR.STARTED);
    try {
      const {item, newName} = actionInfo;
      if (item.type === 'folder') {
        await services.apiService.renameFolder(item, newName);
      } else {
        await services.apiService.renameFile(item, newName);
      }
      mutate(RENAME_ITEM_MUTATOR.COMPLETED);
      dispatch(new FetchCurrentFolderContent());
    } catch (error) {
      mutate(RENAME_ITEM_MUTATOR.FAILED, {error: error.message});
    }
  }
}
