import {CURRENT_FOLDER_MUTATOR} from './fetch-current-folder-mutator.js';
import {HASH_CHANGED_MUTATOR} from './hash-changed-mutator.js';
import {GET_ROOT_FOLDER_MUTATOR} from './get-root-folder-mutator.js';
import {FETCH_CURRENT_FOLDER_CONTENT_MUTATOR} from './fetch-current-folder-content-mutator.js';
import {GET_CURRENT_USER_MUTATOR} from './get-current-user-mutator.js';
import {DELETE_ITEM_MUTATOR} from './delete-item-mutator.js';
import {MODAL_WINDOW_MUTATOR} from './modal-window-mutator.js';

export const mutator = (mutatorName, details, state) => {
  switch (mutatorName) {
    case CURRENT_FOLDER_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: true,
      });
    case CURRENT_FOLDER_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: false,
        currentFolder: details.folder,
      });
    case CURRENT_FOLDER_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: false,
        fetchingCurrentFolderErrorMessage: details.error,
      });
    case HASH_CHANGED_MUTATOR.COMPLETED:
      return Object.assign({}, state, {
        location: details.pageRoute,
        locationParams: details.dynamicPart,
      });
    case GET_ROOT_FOLDER_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: true,
      });
    case GET_ROOT_FOLDER_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: false,
        rootFolder: details.rootFolder,
      });
    case GET_ROOT_FOLDER_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        isCurrentFolderFetching: false,
        fetchingRootFolderErrorMessage: details.error,
      });
    case FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        isCurrentFolderContentFetching: true,
      });
    case FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        isCurrentFolderContentFetching: false,
        currentFolderContent: details.folderContent,
      });
    case FETCH_CURRENT_FOLDER_CONTENT_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        isCurrentFolderContentFetching: false,
        fetchingCurrentFolderContentErrorMessage: details.error,
      });
    case GET_CURRENT_USER_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        isCurrentUserInfoFetching: true,
      });
    case GET_CURRENT_USER_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        isCurrentUserInfoFetching: false,
        userData: details.user,
      });
    case GET_CURRENT_USER_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        isCurrentUserInfoFetching: false,
        fetchingCurrentUserDetailsErrorMessage: details.error,
      });
    case DELETE_ITEM_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        removingFile: details.removingFile,
        deletingFileErrorMessage: '',
      });
    case DELETE_ITEM_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        removingFile: null,
      });
    case DELETE_ITEM_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        deletingFileErrorMessage: details.error,
      });
    case MODAL_WINDOW_MUTATOR.OPEN:
      return Object.assign({}, state, {
        itemInModalWindow: details.item,
      });
  }
};
