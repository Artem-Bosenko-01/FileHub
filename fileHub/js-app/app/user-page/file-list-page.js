import {Component} from '../components/component.js';
import {FileListBody} from './file-list-body.js';
import {FileListFooter} from './file-list-footer.js';
import {FileListHeaderPanel} from './file-list-header-panel.js';
import {FileListItem} from './services/file-list-item.js';
import FetchCurrentFolder from '../services/state-management/fetch-current-directory-action/fetch-current-folder.js';
import {GetRootFolder} from '../services/state-management/get-root-folder-action/get-root-folder.js';

/**
 * Main page for authenticated user, that contains information about him and his saved files.
 */
export class FileListPage extends Component {
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
    const headerPanel = new FileListHeaderPanel(this.rootElement);
    headerPanel.userFullName = 'Oxxxymiron';
    const listBody = new FileListBody(this.rootElement);

    const itemDto = new FileListItem();
    itemDto.itemId = 'fold777';
    itemDto.itemName = 'folder';
    itemDto.itemType = 'folder';
    itemDto.itemsAmount = 44;
    itemDto.parentFolderId = 'as';
    const itemDto1 = new FileListItem();
    itemDto1.itemId = '2';
    itemDto1.itemName = 'file';
    itemDto1.itemType = 'file';
    itemDto1.itemMimeType = 'pdf';
    itemDto1.itemSize = 7987864;
    itemDto1.parentFolderId = '54';
    listBody.fileListItems = [itemDto, itemDto1];

    new FileListFooter(this.rootElement);

    this._stateManager.onStateChanged('currentFolder', (state) => {
      if (state.isCurrentFolderFetching) {
        listBody.currentFolder = 'loading';
      }
      listBody.currentFolder = state.currentFolder;
    });

    this._stateManager.onStateChanged('rootFolder', (state) => {
      this._redirect(`index/${state.rootFolder.itemId}`);
      /* this._stateManager.dispatch(new HashChanged(`index/${state.rootFolder.itemId}`));*/
    });

    this._stateManager.onStateChanged('locationParams', ({locationParams}) => {
      const currentFolderId = locationParams.currentFolderId;
      if (!currentFolderId) {
        this._stateManager.dispatch(new GetRootFolder());
      } else {
        this._stateManager.dispatch(new FetchCurrentFolder());
      }
    });
  }

  /**
   * Event to redirect user.
   * @param {function(hash: string)} listener
   */
  onRedirect(listener) {
    this._redirect = listener;
  }

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
