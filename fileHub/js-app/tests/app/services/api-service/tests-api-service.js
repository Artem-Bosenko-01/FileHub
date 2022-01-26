import testsAuthenticationApiService from '../../login/tests-authentication-api-service.js';
import testsRegistrationApiService from '../../register/tests-registration-api-service.js';
import testsFetchRootFolderApiService from '../state-management/get-root-folder/tests-fetch-root-folder-api-service.js';
import testsFetchCurrentUserApiService
  from '../state-management/get-current-user/tests-fetch-current-user-api-service.js';
import testsFetchCurrentFolderApiService
  from '../state-management/fetch-current-directory/tests-fetch-current-folder-api-service.js';
import testsFetchCurrentFolderContentApiService
  from '../state-management/fetch-current-folder-content/tests-fetch-current-folder-content-api-service.js';
import testsDeleteFileApiService from '../state-management/delete-item/tests-delete-file-api-service.js';
import testsDeleteFolderApiService from '../state-management/delete-item/tests-delete-folder-api-service.js';
import testsUploadFileApiService from '../state-management/upload-file/tests-upload-file-api-service.js';
import testsDownloadFileApiService from '../state-management/download-file/tests-download-file-api-service.js';
import testsCreateFolderApiService from '../state-management/create-folder/tests-create-folder-api-service.js';
import testsLogOutApiService from '../state-management/log-out-user/tests-log-out-api-service.js';
import testsRenameFileApiService from '../state-management/rename-item/tests-rename-file-api-service.js';
import testsRenameFolderApiService from '../state-management/rename-item/tests-rename-folder-api-service.js';

import {ApiService} from '../../../../app/services/api-service/api-service.js';

const {module, test} = QUnit;

module('APIService', () => {
  testsAuthenticationApiService();
  testsRegistrationApiService();
  testsFetchCurrentFolderApiService();
  testsFetchCurrentFolderContentApiService();
  testsFetchRootFolderApiService();
  testsFetchCurrentUserApiService();
  testsDeleteFileApiService();
  testsDeleteFolderApiService();
  testsUploadFileApiService();
  testsDownloadFileApiService();
  testsCreateFolderApiService();
  testsLogOutApiService();
  testsRenameFileApiService();
  testsRenameFolderApiService();

  test('Should add and call listener on 401 response code status.', (assert) => {
    const get401ResponseStatusStep = '401 response status';
    const mockWindow = new EventTarget();
    const apiService = new ApiService(mockWindow);
    apiService.onNavigateAfterError(assert.step(get401ResponseStatusStep));

    mockWindow.dispatchEvent(new CustomEvent('unauthorizedUserError'));

    assert.verifySteps([get401ResponseStatusStep]);
  });
});
