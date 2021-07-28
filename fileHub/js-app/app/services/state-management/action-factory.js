import FetchCurrentFolder from './fetch-current-directory/fetch-current-folder.js';
import DeleteFile from './delete-file-action/delete-file.js';
import FetchCurrentFolderExecutor from './fetch-current-directory/fetch-current-folder-executor.js';
import DeleteFileExecutor from './delete-file-action/delete-file-executor.js';
import {HashChanged} from './hash-changed-action/hash-changed.js';
import {HashChangedExecutor} from './hash-changed-action/hash-changed-executor.js';

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
    this._actions.set(DeleteFile.typeName, new DeleteFileExecutor());
    this._actions.set(HashChanged.typeName, new HashChangedExecutor());
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
