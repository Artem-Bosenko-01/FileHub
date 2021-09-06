import {StateBasedComponent} from './state-based-component.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {SearchBar} from './search-bar.js';
import {FileList} from './file-list.js';
import {GetRootFolder} from '../services/state-management/get-root-folder-action/get-root-folder.js';
import FetchCurrentFolder from '../services/state-management/fetch-current-folder-action/fetch-current-folder.js';
import GetCurrentUser from '../services/state-management/get-current-user-action/get-current-user.js';
import {RemoveDialogWindow} from '../modals/remove-dialog.js';
import {DeleteItem} from '../services/state-management/delete-item-action/delete-item.js';
import {OpenModalWindow} from '../services/state-management/open-modal-window/open-modal-window.js';
import {CloseModalWindow} from '../services/state-management/close-modal-window-action/close-modal-window.js';
import {uploadFile} from '../services/upload-file-function.js';
import {UploadFile} from '../services/state-management/upload-file-action/upload-file.js';
import {DownloadFile} from '../services/state-management/download-file-action/download-file.js';
import {downloadFile} from '../services/download-file-function.js';
import {CreateFolderDialog} from '../modals/create-directory-dialog.js';
import {CreateFolder} from '../services/state-management/create-folder-action/create-folder.js';
import {LogOutUser} from '../services/state-management/log-out-user-action/log-out-user.js';
import {SelectItem} from '../services/state-management/select-item-action/select-item.js';
import {RenameItem} from '../services/state-management/rename-item-action/rename-item.js';
import {FetchCurrentFolderContent}
  from '../services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';

/**
 * Main page for authenticated user, that contains information about him and his saved files.
 */
export class FileListPage extends StateBasedComponent {
  /**
   * Listener for redirecting a user to folder.
   * @param {function(folderId: string)} listener
   */
  onNavigateToFolder(listener) {
    this._onNavigateToFolder = listener;
    this.deleteAllSubscribersOnChangedState();
    this._render();
  }

  /**
   * Adds api and title services to page
   * @param {TitleService} titleService
   * @param {StateManager} stateManager
   * @param {ModalsService} modalService
   */
  _init(titleService, stateManager, modalService) {
    super._init(titleService, stateManager);
    this._stateManager = stateManager;
    this._modalService = modalService;
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const userPanelElement = this._getElement('user-panel');
    const userDetails = new UserDetails(userPanelElement);
    const logOut = new LogOut(userPanelElement);
    logOut.onClick(() => {
      this._stateManager.dispatch(new LogOutUser());
      this.deleteAllSubscribersOnChangedState();
    });

    const fileListBodyElement = this._getElement('file-list-body');
    const breadcrumbs = new Breadcrumbs(fileListBodyElement);
    breadcrumbs.onFolderNameClick(this._onNavigateToFolder);
    new SearchBar(fileListBodyElement);
    const controlButtons = new FolderControlButtons(fileListBodyElement);

    controlButtons.onUploadButtonClick(async () => {
      const uploadedFile = await uploadFile(document);
      this._stateManager.dispatch(new UploadFile(uploadedFile, this._stateManager.state.currentFolder.id));
    });

    controlButtons.onCreateNewFolderButtonClick(() => {
      this._stateManager.dispatch(new OpenModalWindow({createNewFolderWindow: true}));
    });

    const fileList = new FileList(fileListBodyElement);
    fileList.onFolderDoubleCLicked(this._onNavigateToFolder);
    fileList.onDeleteButtonClick((item) => {
      this._stateManager.dispatch(new OpenModalWindow(item));
    });

    fileList.onUploadButtonClick(async (item) => {
      const uploadedFile = await uploadFile(document);
      this._stateManager.dispatch(new UploadFile(uploadedFile, item.id));
    });

    fileList.onDownloadButtonClick((item) => {
      this._stateManager.dispatch(new DownloadFile(item.id));
    });

    fileList.onClickLine((itemId) => {
      fileList.renameItem(null);
      this._stateManager.dispatch(new SelectItem(itemId));
      fileList.selectedFileListItem = itemId;
    });

    fileList.onItemNameClick((item) => {
      const selectedLine = this._stateManager.state.selectedLine;
      this._renamedItem = item;
      if (selectedLine === item.id) {
        setTimeout(() => fileList.renameItem(selectedLine));
      }
    });

    fileList.onRenameItemSubmit((newName) => {
      this._stateManager.dispatch(new RenameItem(this._renamedItem, newName));
    });

    this._onStateChangedListener('locationParams', async () => {
      const state = this._stateManager.state;
      if (state.location !== 'index') {
        return;
      }

      const currentFolderId = state.locationParams.currentFolderId;
      if (!currentFolderId && !state.rootFolder) {
        this._stateManager.dispatch(new GetRootFolder());
      } else if (!currentFolderId && state.rootFolder) {
        this._onNavigateToFolder(state.rootFolder.id);
      } else {
        this._stateManager.dispatch(new FetchCurrentFolder());
        this._stateManager.dispatch(new FetchCurrentFolderContent());
        if (!state.userData) {
          this._stateManager.dispatch(new GetCurrentUser());
        } else {
          userDetails.userFullName = state.userData.name;
        }
      }
    });

    this._onStateChangedListener('itemInModalWindow', () => {
      const state = this._stateManager.state;
      if (!state.itemInModalWindow) {
        return;
      }

      if (state.itemInModalWindow.createNewFolderWindow) {
        this._modalWindow = this._modalService.open((container) => {
          return new CreateFolderDialog(container);
        });

        this._modalWindow.onSubmit((folderName) => {
          this._stateManager.dispatch(new CreateFolder(folderName, state.currentFolder.id));
        });
      } else {
        this._modalWindow = this._modalService.open((container) => {
          return new RemoveDialogWindow(container, state.itemInModalWindow);
        });

        this._modalWindow.onSubmit(() => this._stateManager.dispatch(new DeleteItem(state.itemInModalWindow)));
      }

      this._modalWindow.onClose(() => {
        this._stateManager.dispatch(new CloseModalWindow());
        this._modalService.close();
      });
    });

    this._onStateChangedListener('deletingFileErrorMessage', () => {
      this._modalWindow.deletingInProgress = !this._stateManager.state.deletingFileErrorMessage;

      this._modalWindow.errorMessage = this._stateManager.state.deletingFileErrorMessage;
    });

    this._onStateChangedListener('removingFile', () => {
      const state = this._stateManager.state;
      this._modalWindow.deletingInProgress = state.removingFile === state.itemInModalWindow;

      if (!this._stateManager.state.removingFile) {
        this._modalService.close();
      }
    });

    this._onStateChangedListener('currentFolder', () => {
      breadcrumbs.currentDirectory = this._stateManager.state.currentFolder;
    });

    this._onStateChangedListener('isCurrentFolderFetching', () => {
      breadcrumbs.loading = this._stateManager.state.isCurrentFolderFetching;
    });

    this._onStateChangedListener('fetchingCurrentFolderErrorMessage', () => {
      breadcrumbs.currentDirectory = null;
      breadcrumbs.errorMessage = this._stateManager.state.fetchingCurrentFolderErrorMessage;
    });

    this._onStateChangedListener('currentFolderContent', () => {
      fileList.renameItem(null);
      fileList.selectedFileListItem = null;
      fileList.fileItems = this._stateManager.state.currentFolderContent;
    });

    this._onStateChangedListener('isCurrentFolderContentFetching', () => {
      fileList.fileItems = null;
      fileList.loading = this._stateManager.state.isCurrentFolderContentFetching;
    });

    this._onStateChangedListener('fetchingCurrentFolderContentErrorMessage', () => {
      fileList.fileItems = null;
      fileList.errorMessage = this._stateManager.state.fetchingCurrentFolderContentErrorMessage;
    });

    this._onStateChangedListener('userData', () => {
      userDetails.userFullName = this._stateManager.state.userData.name;
    });

    this._onStateChangedListener('isCurrentUserInfoFetching', () => {
      userDetails.loading = this._stateManager.state.isCurrentUserInfoFetching;
    });

    this._onStateChangedListener('fetchingCurrentUserDetailsErrorMessage', () => {
      userDetails.errorMessage = this._stateManager.state.fetchingCurrentUserDetailsErrorMessage;
    });

    this._onStateChangedListener('isUploadingFile', () => {
      controlButtons.loadingUploadFile = this._stateManager.state.isUploadingFile;
      fileList.isLoadingUploadFile = this._stateManager.state.isUploadingFile;
    });

    this._onStateChangedListener('uploadingFileErrorMessage', () => {
      fileList.errorMessageAfterFileManipulations = this._stateManager.state.uploadingFileErrorMessage;
    });

    this._onStateChangedListener('downloadedFileContent', () => {
      downloadFile(document, this._stateManager.state.downloadedFileContent);
    });

    this._onStateChangedListener('downloadedFile', () => {
      const {fileId, isLoading} = this._stateManager.state.downloadedFile;
      fileList.isLoadingDownloadFile(isLoading, fileId);
    });

    this._onStateChangedListener('downloadingFileErrorMessage', () => {
      fileList.errorMessageAfterFileManipulations = this._stateManager.state.downloadingFileErrorMessage;
    });

    this._onStateChangedListener('isCreatingNewFolder', () => {
      const creatingStatus = this._stateManager.state.isCreatingNewFolder;
      this._modalWindow.creatingInProgress = creatingStatus;
      if (!creatingStatus && !this._stateManager.state.creatingDirectoryErrorMessage) {
        this._modalService.close();
      }
    });

    this._onStateChangedListener('creatingDirectoryErrorMessage', () => {
      const errorMessage = this._stateManager.state.creatingDirectoryErrorMessage;
      if (errorMessage) {
        this._modalWindow.creatingInProgress = false;
      }
      this._modalWindow.errorMessage = errorMessage;
    });

    this._onStateChangedListener('isRenamingLoadingStatus', () => {
      fileList.isRenameItemLoading = this._stateManager.state.isRenamingLoadingStatus;
    });

    this._onStateChangedListener('renamingItemErrorMessage', () => {
      fileList.errorMessageRenamingItem = this._stateManager.state.renamingItemErrorMessage;
    });

    this._onStateChangedListener('rootFolder', () => {
      const state = this._stateManager.state;
      const rootFolderId = state.rootFolder.id;
      breadcrumbs.rootPage = rootFolderId;
      if (!state.locationParams.currentFolderId) {
        this._onNavigateToFolder(rootFolderId);
      }
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<div>
                <header class="header">
                  <h1 title="TeamDev">
                     <a class="logo" href="#index">
                        TeamDev
                     </a>
                  </h1>
                  <ul data-fh="user-panel" class="panel"></ul>
                </header>
                <div data-fh="file-list-body" class="raw page-raw"></div>
                <footer data-fh="footer" class="footer">
                  <ul class="social-icons">
                    <li>
                        <a title="linkedin" class="icon" href="https://www.linkedin.com/company/teamdev-ltd-/mycompany/"
                           target="_blank">
                            <img src="./images/icon-linkedin.png" alt="linkedin">
                        </a>
                    </li>
                    <li>
                        <a title="facebook" class="icon" href="https://www.facebook.com/TeamDev" target="_blank">
                            <img src="./images/icon-facebook.png" alt="facebook">
                        </a>
                    </li>
                    <li>
                        <a title="instagram" class="icon" href="https://www.instagram.com/teamdev_ltd/?hl=ru"
                           target="_blank">
                            <img src="./images/icon-instagram.png" alt="instagram">
                        </a>
                    </li>
                  </ul>
                  <p class="copyright"
                      >Copyright &copy; 2021 <a 
                      title="TeamDev" class="highlight" href="https://www.teamdev.com/" target="_blank">TeamDev</a>. All
                      rights reserved.</p>
                </footer>
            </div>
            `;
  }
}
