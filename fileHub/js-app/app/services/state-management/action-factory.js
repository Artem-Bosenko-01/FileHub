import FetchCurrentFolder from './fetch-current-directory-action/fetch-current-folder.js';
import FetchCurrentFolderExecutor from './fetch-current-directory-action/fetch-current-folder-executor.js';
import {HashChanged} from './hash-changed-action/hash-changed.js';
import {HashChangedExecutor} from './hash-changed-action/hash-changed-executor.js';
import {GetRootFolder} from './get-root-folder-action/get-root-folder.js';
import {GetRootFolderExecutor} from './get-root-folder-action/get-root-folder-executor.js';
import {FetchCurrentFolderContent} from './fetch-current-folder-content-action/fetch-current-folder-content.js';
import GetCurrentUser from './get-current-user-action/get-current-user.js';
import GetCurrentUserExecutor from './get-current-user-action/get-current-user-executor.js';
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
        .set(HashChanged.typeName, new HashChangedExecutor())
        .set(GetRootFolder.typeName, new GetRootFolderExecutor())
        .set(FetchCurrentFolderContent.typeName, new FetchCurrentFolderContentExecutor())
        .set(GetCurrentUser.typeName, new GetCurrentUserExecutor());
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
