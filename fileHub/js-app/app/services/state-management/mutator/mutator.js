import {CURRENT_FOLDER_MUTATOR} from './fetch-current-folder-mutator.js';
import {HASH_CHANGED_MUTATOR} from './hash-changed-mutator.js';

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
        location: details,
      });
  }
};
