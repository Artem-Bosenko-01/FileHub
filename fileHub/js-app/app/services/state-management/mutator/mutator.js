import {CURRENT_FOLDER_MUTATOR} from './fetch-current-folder-mutator.js';
import {HASH_CHANGED_MUTATOR} from './hash-changed-mutator.js';
import {GET_ROOT_FOLDER_MUTATOR} from './get-root-folder-mutator.js';
import {FETCH_CURRENT_FOLDER_CONTENT_MUTATOR} from './fetch-current-folder-content-mutator.js';
import {GET_CURRENT_USER_MUTATOR} from './get-current-user-mutator.js';
import {DELETE_ITEM_MUTATOR} from './delete-item-mutator.js';
import {MODAL_WINDOW_MUTATOR} from './modal-window-mutator.js';
import {UPLOAD_FILE_MUTATOR} from './upload-file-mutator.js';
import {DOWNLOAD_FILE_MUTATOR} from './download-file-mutator.js';
import {CREATE_FOLDER_MUTATOR} from './create-folder-mutator.js';
import {LOG_OUT_USER_MUTATOR} from './log-out-user-mutator.js';
import {SELECT_ITEM_MUTATOR} from './select-item-mutator.js';
import {RENAME_ITEM_MUTATOR} from './rename-item-mutator.js';
import {SEARCHING_MUTATOR} from './searching-mutator.js';

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
        selectedLine: null,
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
        itemInModalWindow: null,
      });
    case DELETE_ITEM_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        deletingFileErrorMessage: details.error,
        itemInModalWindow: null,
      });
    case MODAL_WINDOW_MUTATOR.OPEN:
      return Object.assign({}, state, {
        itemInModalWindow: details.item,
      });
    case MODAL_WINDOW_MUTATOR.CLOSE:
      return Object.assign({}, state, {
        itemInModalWindow: null,
      });
    case UPLOAD_FILE_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        isUploadingFile: true,
        uploadingFileErrorMessage: '',
      });
    case UPLOAD_FILE_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        isUploadingFile: false,
      });
    case UPLOAD_FILE_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        isUploadingFile: false,
        uploadingFileErrorMessage: details.error,
      });
    case DOWNLOAD_FILE_MUTATOR.FETCHING_STARTED:
      return Object.assign({}, state, {
        downloadingFileErrorMessage: '',
        downloadedFile: {
          fileId: details.downloadedFile,
          isLoading: true,
        },
      });
    case DOWNLOAD_FILE_MUTATOR.FETCHING_COMPLETED:
      return Object.assign({}, state, {
        downloadedFile: {
          fileId: details.downloadedFile,
          isLoading: false,
        },
        downloadedFileContent: details.file,
      });
    case DOWNLOAD_FILE_MUTATOR.FETCHING_FAILED:
      return Object.assign({}, state, {
        downloadedFile: {
          fileId: details.downloadedFile,
          isLoading: false,
        },
        downloadingFileErrorMessage: details.error,
      });

    case CREATE_FOLDER_MUTATOR.STARTED:
      return Object.assign({}, state, {
        isCreatingNewFolder: true,
        creatingDirectoryErrorMessage: '',
      });
    case CREATE_FOLDER_MUTATOR.COMPLETED:
      return Object.assign({}, state, {
        isCreatingNewFolder: false,
      });
    case CREATE_FOLDER_MUTATOR.FAILED:
      return Object.assign({}, state, {
        creatingDirectoryErrorMessage: details.error,
        itemInModalWindow: null,
        isCreatingNewFolder: false,
      });
    case LOG_OUT_USER_MUTATOR.COMPLETED:
      return Object.assign({}, state, {
        location: '',
      });
    case SELECT_ITEM_MUTATOR.SET:
      return Object.assign({}, state, {
        selectedLine: details.itemId,
        renamingItemErrorMessage: '',
      });
    case RENAME_ITEM_MUTATOR.STARTED:
      return Object.assign({}, state, {
        isRenamingLoadingStatus: true,
        renamingItemErrorMessage: '',
      });
    case RENAME_ITEM_MUTATOR.COMPLETED:
      return Object.assign({}, state, {
        isRenamingLoadingStatus: false,
      });
    case RENAME_ITEM_MUTATOR.FAILED:
      return Object.assign({}, state, {
        renamingItemErrorMessage: details.error,
        isRenamingLoadingStatus: false,
      });
    case SEARCHING_MUTATOR.STARTED:
      return Object.assign({}, state, {
        isSearching: true,
      });
    case SEARCHING_MUTATOR.COMPLETED:
      return Object.assign({}, state, {
        isSearching: false,
        currentFolderContent: details.content,
      });
    case SEARCHING_MUTATOR.FAILED:
      return Object.assign({}, state, {
        searchingItemsErrorMessage: details.error,
        isSearching: false,
      });
  }
};
