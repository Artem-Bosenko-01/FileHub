import {Component} from '../components/component.js';
import {FileListBody} from './file-list-body.js';
import {FileListFooter} from './file-list-footer.js';
import {FileListHeaderPanel} from './file-list-header-panel.js';
import {FileListItem} from './services/file-list-item.js';
import FetchCurrentFolder from '../services/state-management/fetch-current-folder.js';
import DeleteFile from '../services/state-management/delete-file.js';

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

    itemDto.itemId = '1';
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

    listBody.currentFolder = itemDto;
    listBody.fileListItems = [itemDto, itemDto1];

    new FileListFooter(this.rootElement);

    const currentFolder = this._stateManager.state.currentFolder;
    if (!currentFolder) {
      this._stateManager.dispatch(new FetchCurrentFolder());
      this._stateManager.dispatch(new DeleteFile('id'));
    }
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
