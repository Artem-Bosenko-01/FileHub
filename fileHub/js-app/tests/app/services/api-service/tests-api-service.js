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
const {module} = QUnit;

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
});
