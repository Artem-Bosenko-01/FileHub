import {mutator} from '../../../../app/services/state-management/mutator/mutator.js';

const {module, test} = QUnit;

module('Mutator', () => {
  test('Should change state on CURRENT_FOLDER_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('CURRENT_FOLDER_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderFetching: true}, 'Should get current folder fetching flag true');
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
    }, 'Should get current folder');
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
    }, 'Should get error message');
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
    }, 'Should get route for page and also dynamic parameters');
  });

  test('Should change state on GET_ROOT_FOLDER_MUTATOR_STARTED mutator name', (assert) => {
    const mutatedState = mutator('GET_ROOT_FOLDER_MUTATOR_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderFetching: true}, 'Should get root folder fetching flag true');
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
    }, 'Should get root folder');
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
    }, 'Should get error message');
  });

  test('Should change state on CURRENT_FOLDER_CONTENT_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('CURRENT_FOLDER_CONTENT_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentFolderContentFetching: true},
        'Should get current folder content fetching flag true');
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
    }, 'Should get current folder content');
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
    }, 'Should get error message');
  });

  test('Should change state on GET_CURRENT_USER_FETCHING_STARTED mutator name', (assert) => {
    const mutatedState = mutator('GET_CURRENT_USER_FETCHING_STARTED', {}, {});
    assert.deepEqual(mutatedState, {isCurrentUserInfoFetching: true},
        'Should get current user data fetching flag true');
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
    }, 'Should get user data');
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
    }, 'Should get error message');
  });
});
