import FetchCurrentFolder from './fetch-current-directory-action/fetch-current-folder.js';
import FetchCurrentFolderExecutor from './fetch-current-directory-action/fetch-current-folder-executor.js';
import {HashChanged} from './hash-changed-action/hash-changed.js';
import {HashChangedExecutor} from './hash-changed-action/hash-changed-executor.js';
import {GetRootFolder} from './get-root-folder-action/get-root-folder.js';
import {GetRootFolderExecutor} from './get-root-folder-action/get-root-folder-executor.js';
import {FetchCurrentFolderContent} from './fetch-current-folder-content-action/fetch-current-folder-content.js';
import {FetchCurrentFolderContentExecutor}
  from './fetch-current-folder-content-action/fetch-current-folder-content-executor.js';
import GetCurrentUser from './get-current-user-action/get-current-user.js';
import GetCurrentUserExecutor from './get-current-user-action/get-current-user-executor.js';

/**
 * The factory contains a map of the action name and executor.
 */
export class ActionFactory {
  /**
   * @constructor
   */
  constructor() {
    this._actions = new Map();
    this._actions.set(FetchCurrentFolder.typeName, new FetchCurrentFolderExecutor());
    this._actions.set(HashChanged.typeName, new HashChangedExecutor());
    this._actions.set(GetRootFolder.typeName, new GetRootFolderExecutor());
    this._actions.set(FetchCurrentFolderContent.typeName, new FetchCurrentFolderContentExecutor());
    this._actions.set(GetCurrentUser.typeName, new GetCurrentUserExecutor());
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
