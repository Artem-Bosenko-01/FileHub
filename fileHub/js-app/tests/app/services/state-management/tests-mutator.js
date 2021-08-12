import {mutator} from '../../../../app/services/state-management/mutator/mutator.js';

const {module, test} = QUnit;

module('Mutator', () => {
  test('Should change state on CURRENT_FOLDER_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('CURRENT_FOLDER_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderFetching: true}, 'Should set current folder fetching flag true');
  });

  test('Should change state on CURRENT_FOLDER_FETCHING_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('CURRENT_FOLDER_FETCHING_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      fetchingCurrentFolderErrorMessage: errorMessage,
      isCurrentFolderFetching: false,
    }, 'Should set current folder');
  });

  test('Should change state on CURRENT_FOLDER_FETCHING_COMPLETED mutator name', (assert) => {
    const folderName = 'folder';
    const mockDetails = {
      folder: folderName,
    };
    const mutatedState = mutator('CURRENT_FOLDER_FETCHING_COMPLETED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      currentFolder: folderName,
      isCurrentFolderFetching: false,
    }, 'Should set error message');
  });

  test('Should change state on HASH_CHANGED_MUTATOR_COMPLETED mutator name', (assert) => {
    const pageRoute = 'route';
    const dynamicPart = 'dynamic';
    const mockDetails = {
      pageRoute,
      dynamicPart,
    };
    const mutatedState = mutator('HASH_CHANGED_MUTATOR_COMPLETED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      location: pageRoute,
      locationParams: dynamicPart,
    }, 'Should set route for page and also dynamic parameters');
  });

  test('Should change state on GET_ROOT_FOLDER_MUTATOR_STARTED mutator name', (assert) => {
    const mutatedState = mutator('GET_ROOT_FOLDER_MUTATOR_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderFetching: true}, 'Should set root folder fetching flag true');
  });

  test('Should change state on GET_ROOT_FOLDER_MUTATOR_COMPLETED mutator name', (assert) => {
    const folderName = 'folder';
    const mockDetails = {
      rootFolder: folderName,
    };
    const mutatedState = mutator('GET_ROOT_FOLDER_MUTATOR_COMPLETED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      rootFolder: folderName,
      isCurrentFolderFetching: false,
    }, 'Should set root folder');
  });

  test('Should change state on GET_ROOT_FOLDER_MUTATOR_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('GET_ROOT_FOLDER_MUTATOR_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      fetchingRootFolderErrorMessage: errorMessage,
      isCurrentFolderFetching: false,
    }, 'Should set error message');
  });

  test('Should change state on CURRENT_FOLDER_CONTENT_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('CURRENT_FOLDER_CONTENT_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderContentFetching: true},
        'Should set current folder content fetching flag true');
  });

  test('Should change state on CURRENT_FOLDER_CONTENT_FETCHING_COMPLETED mutator name', (assert) => {
    const folderContent = 'folder';
    const mockDetails = {
      folderContent: folderContent,
    };
    const mutatedState = mutator('CURRENT_FOLDER_CONTENT_FETCHING_COMPLETED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      currentFolderContent: folderContent,
      isCurrentFolderContentFetching: false,
    }, 'Should set current folder content');
  });

  test('Should change state on CURRENT_FOLDER_CONTENT_FETCHING_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('CURRENT_FOLDER_CONTENT_FETCHING_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      fetchingCurrentFolderContentErrorMessage: errorMessage,
      isCurrentFolderContentFetching: false,
    }, 'Should set error message');
  });

  test('Should change state on GET_CURRENT_USER_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('GET_CURRENT_USER_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentUserInfoFetching: true},
        'Should set current user data fetching flag true');
  });

  test('Should change state on GET_CURRENT_USER_FETCHING_COMPLETED mutator name', (assert) => {
    const userName = 'name';
    const mockDetails = {
      user: userName,
    };
    const mutatedState = mutator('GET_CURRENT_USER_FETCHING_COMPLETED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      userData: userName,
      isCurrentUserInfoFetching: false,
    }, 'Should set user data');
  });

  test('Should change state on GET_CURRENT_USER_FETCHING_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('GET_CURRENT_USER_FETCHING_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      fetchingCurrentUserDetailsErrorMessage: errorMessage,
      isCurrentUserInfoFetching: false,
    }, 'Should set error message');
  });

  test('Should change state on DELETE_ITEM_FETCHING_STARTED mutator name', (assert) => {
    const removingFileName = 'error';
    const mockDetails = {
      removingFile: removingFileName,
    };
    const mutatedState = mutator('DELETE_ITEM_FETCHING_STARTED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      removingFile: removingFileName,
      deletingFileErrorMessage: '',
    },
    'Should set current removing file');
  });

  test('Should change state on DELETE_ITEM_FETCHING_COMPLETED mutator name', (assert) => {
    const mutatedState = mutator('DELETE_ITEM_FETCHING_COMPLETED', {}, {});
    assert.deepEqual(mutatedState, {itemInModalWindow: null, removingFile: null}, 'Should set removingFile null');
  });

  test('Should change state on DELETE_ITEM_FETCHING_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('DELETE_ITEM_FETCHING_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      itemInModalWindow: null,
      deletingFileErrorMessage: errorMessage,
    }, 'Should set error message');
  });

  test('Should change state on OPEN_MODAL_WINDOW mutator name', (assert) => {
    const fileName = 'file name';
    const mockDetails = {
      item: fileName,
    };
    const mutatedState = mutator('OPEN_MODAL_WINDOW', mockDetails, {});
    assert.deepEqual(mutatedState, {itemInModalWindow: fileName}, 'Should set removingFile');
  });

  test('Should change state on CLOSE_MODAL_WINDOW mutator name', (assert) => {
    const mutatedState = mutator('CLOSE_MODAL_WINDOW', {}, {});
    assert.deepEqual(mutatedState, {itemInModalWindow: null}, 'Should set removingFile null');
  });

  test('Should change state on UPLOAD_FILE_MUTATOR_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('UPLOAD_FILE_MUTATOR_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isUploadingFile: true}, 'Should set uploading file flag true');
  });

  test('Should change state on UPLOAD_FILE_MUTATOR_FETCHING_COMPLETED mutator name', (assert) => {
    const mutatedState = mutator('UPLOAD_FILE_MUTATOR_FETCHING_COMPLETED', {}, {});
    assert.deepEqual(mutatedState, {isUploadingFile: false}, 'Should set uploading file flag false');
  });

  test('Should change state on UPLOAD_FILE_MUTATOR_FETCHING_FAILED mutator name', (assert) => {
    const errorMessage = 'error';
    const mockDetails = {
      error: errorMessage,
    };
    const mutatedState = mutator('UPLOAD_FILE_MUTATOR_FETCHING_FAILED', mockDetails, {});
    assert.deepEqual(mutatedState, {
      isUploadingFile: false,
      uploadingFileErrorMessage: errorMessage,
    }, 'Should set error message');
  });
});
