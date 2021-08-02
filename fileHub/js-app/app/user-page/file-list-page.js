import {Component} from '../components/component.js';
import {FileListPageContent} from './file-list-page-content.js';
import {FileListFooter} from './file-list-footer.js';
import {FileListHeaderPanel} from './file-list-header-panel.js';
import FetchCurrentFolder from '../services/state-management/fetch-current-directory-action/fetch-current-folder.js';
import {GetRootFolder} from '../services/state-management/get-root-folder-action/get-root-folder.js';
import GetCurrentUser from '../services/state-management/get-current-user-action/get-current-user.js';

import {FetchCurrentFolderContent}
  from '../services/state-management/fetch-current-folder-content-action/fetch-current-folder-content.js';

// TODO Split them.

/**
 * Main page for authenticated user, that contains information about him and his saved files.
 */
export class FileListPage extends Component {
  // TODO Don't mix them.

  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {TitleService} titleService
   * @param {StateManager} stateManager
   */
  _init(titleService, stateManager) {
    this._stateManager = stateManager;
    this._titleService = titleService;
    this._titleService.addTitleForPage('Main Page');
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const headerPanel = new FileListHeaderPanel(this.rootElement);
    const listBody = new FileListPageContent(this.rootElement);

    new FileListFooter(this.rootElement);

    this._stateManager.onStateChanged('currentFolder', (state) => {
      if (state.isCurrentFolderFetching) {
        listBody.currentFolder = 'loading';
      }
      listBody.currentFolder = state.currentFolder;
    });

    this._stateManager.onStateChanged('userData', (state) => {
      if (state.isCurrentUserInfoFetching) {
        headerPanel.userFullName = 'loading';
      }
      headerPanel.userFullName = state.userData.name;
    });

    this._stateManager.onStateChanged('locationParams', ({locationParams}) => {
      const currentFolderId = locationParams.currentFolderId;
      if (!currentFolderId) {
        this._stateManager.dispatch(new GetRootFolder());
      } else {
        this._stateManager.dispatch(new FetchCurrentFolder());
        this._stateManager.dispatch(new FetchCurrentFolderContent());
        this._stateManager.dispatch(new GetCurrentUser());
      }
    });

    this._stateManager.onStateChanged('rootFolder', (state) => {
      this._redirect(`index/${state.rootFolder.itemId}`);
      /* this._stateManager.dispatch(new HashChanged(`index/${state.rootFolder.itemId}`));*/
    });

    this._stateManager.onStateChanged('currentFolderContent', (state) => {
      if (state.isCurrentFolderContentFetching) {
        listBody.fileListItems = 'loading';
      }
      listBody.fileListItems = state.currentFolderContent;
    });
  }

  /**
   * Event to redirect user.
   * @param {function(hash: string)} listener
   */
  onRedirect(listener) {
    this._redirect = listener;
  }

  // TODO We DO use header in different places and we DO NOT extract it.
  // TODO Meantime, We DO use footer in the only place and we DO extract it.
  // TODO What's your philosophy ?

  /** @inheritDoc */
  get _markup() {
    return `<header class="header">
              <h1 title="TeamDev">
                 <a class="logo" href="#index">
                    TeamDev
                 </a>
              </h1>
            </header>`;
  }
}
