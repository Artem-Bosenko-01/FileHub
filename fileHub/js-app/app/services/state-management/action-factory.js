import FetchCurrentFolder from './fetch-current-directory-action/fetch-current-folder.js';
import FetchCurrentFolderExecutor from './fetch-current-directory-action/fetch-current-folder-executor.js';
import {RouteChanged} from './hash-changed-action/route-changed.js';
import {RouteChangedExecutor} from './hash-changed-action/route-changed-executor.js';
import {GetRootFolder} from './get-root-folder-action/get-root-folder.js';
import {GetRootFolderExecutor} from './get-root-folder-action/get-root-folder-executor.js';
import {FetchCurrentFolderContent} from './fetch-current-folder-content-action/fetch-current-folder-content.js';
import {FetchCurrentFolderContentExecutor}
  from './fetch-current-folder-content-action/fetch-current-folder-content-executor.js';

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
        .set(FetchCurrentFolderContent.typeName, new FetchCurrentFolderContentExecutor());
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
