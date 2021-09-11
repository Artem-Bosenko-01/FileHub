import FetchCurrentFolder from './fetch-current-folder-action/fetch-current-folder.js';
import FetchCurrentFolderExecutor from './fetch-current-folder-action/fetch-current-folder-executor.js';
import {RouteChanged} from './route-changed-action/route-changed.js';
import {RouteChangedExecutor} from './route-changed-action/route-changed-executor.js';
import {GetRootFolder} from './get-root-folder-action/get-root-folder.js';
import {GetRootFolderExecutor} from './get-root-folder-action/get-root-folder-executor.js';
import {FetchCurrentFolderContent} from './fetch-current-folder-content-action/fetch-current-folder-content.js';
import GetCurrentUser from './get-current-user-action/get-current-user.js';
import GetCurrentUserExecutor from './get-current-user-action/get-current-user-executor.js';
import {FetchCurrentFolderContentExecutor}
  from './fetch-current-folder-content-action/fetch-current-folder-content-executor.js';
import {DeleteItem} from './delete-item-action/delete-item.js';
import {DeleteItemExecutor} from './delete-item-action/delete-item-executor.js';
import {OpenModalWindow} from './open-modal-window/open-modal-window.js';
import {OpenModalWindowExecutor} from './open-modal-window/open-modal-window-executor.js';
import {CloseModalWindow} from './close-modal-window-action/close-modal-window.js';
import {CloseModalWindowExecutor} from './close-modal-window-action/close-modal-window-executor.js';

/**
 * The factory contains a map of the action name and executor.
 */
export class ActionFactory {
  /**
   * @constructor
   */
  constructor() {
    this._actions = new Map()
        .set(FetchCurrentFolder.typeName, new FetchCurrentFolderExecutor())
        .set(RouteChanged.typeName, new RouteChangedExecutor())
        .set(GetRootFolder.typeName, new GetRootFolderExecutor())
        .set(FetchCurrentFolderContent.typeName, new FetchCurrentFolderContentExecutor())
        .set(GetCurrentUser.typeName, new GetCurrentUserExecutor())
        .set(DeleteItem.typeName, new DeleteItemExecutor())
        .set(OpenModalWindow.typeName, new OpenModalWindowExecutor())
        .set(CloseModalWindow.typeName, new CloseModalWindowExecutor());
  }

  /**
   * Gets action executor by type name.
   * @param {string} name
   * @returns {ActionExecutor}
   */
  getActionExecutor(name) {
    return this._actions.get(name);
  }
}
