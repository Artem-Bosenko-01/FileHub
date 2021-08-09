import {Component} from '../components/component.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {SearchBar} from './search-bar.js';
import {FileList} from './file-list.js';
import {GetRootFolder} from '../services/state-management/get-root-folder-action/get-root-folder.js';
import FetchCurrentFolder from '../services/state-management/fetch-current-directory-action/fetch-current-folder.js';
import {FetchCurrentFolderContent}
  from '../services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';

/**
 * Main page for authenticated user, that contains information about him and his saved files.
 */
export class FileListPage extends Component {
  /**
   * Listener for redirecting a user to folder.
   * @param {function(folderId: string)} listener
   */
  onNavigateToFolder(listener) {
    this._onNavigateToFolder = listener;
    this._render();
  }

  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {TitleService} titleService
   * @param {StateManager} stateManager
   */
  _init(titleService, stateManager) {
    this._titleService = titleService;
    this._titleService.addTitleForPage('Main Page');
    this._stateManager = stateManager;
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const userPanelElement = this._getElement('user-panel');
    const userDetails = new UserDetails(userPanelElement);
    userDetails.userFullName = 'Oxxxymiron';
    new LogOut(userPanelElement);

    const fileListBodyElement = this._getElement('file-list-body');
    const breadcrumbs = new Breadcrumbs(fileListBodyElement);
    breadcrumbs.onFolderNameClick(this._onNavigateToFolder);
    new SearchBar(fileListBodyElement);
    new FolderControlButtons(fileListBodyElement);
    const fileList = new FileList(fileListBodyElement);
    fileList.onFolderClick(this._onNavigateToFolder);

    this._stateManager.onStateChanged('locationParams', async (state) => {
      const currentFolderId = state.locationParams.currentFolderId;
      if (!currentFolderId && !state.rootFolder) {
        this._stateManager.dispatch(new GetRootFolder());
      } else if (!currentFolderId && state.rootFolder) {
        this._onNavigateToFolder(state.rootFolder.id);
      } else {
        await this._stateManager.dispatch(new FetchCurrentFolder());
        this._stateManager.dispatch(new FetchCurrentFolderContent());
      }
    });

    this._stateManager.onStateChanged('currentFolder', (state) => {
      breadcrumbs.currentDirectory = state.currentFolder;
    });

    this._stateManager.onStateChanged('isCurrentFolderFetching', (state) => {
      breadcrumbs.loadingCurrentFolderDataState = state.isCurrentFolderFetching;
    });

    this._stateManager.onStateChanged('fetchingCurrentFolderErrorMessage', (state) => {
      breadcrumbs.currentDirectory = null;
      breadcrumbs.errorMessage = state.fetchingCurrentFolderErrorMessage;
    });

    this._stateManager.onStateChanged('currentFolderContent', (state) => {
      fileList.fileItems = state.currentFolderContent;
    });

    this._stateManager.onStateChanged('isCurrentFolderContentFetching', (state) => {
      fileList.fileItems = null;
      fileList.loadingFolderContentState = state.isCurrentFolderContentFetching;
    });

    this._stateManager.onStateChanged('fetchingCurrentFolderContentErrorMessage', (state) => {
      fileList.fileItems = null;
      fileList.errorMessage = state.fetchingCurrentFolderContentErrorMessage;
    });


    this._stateManager.onStateChanged('rootFolder', (state) => {
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
