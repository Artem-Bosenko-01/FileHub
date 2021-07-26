import {Component} from '../components/component.js';
import {FileListBody} from './file-list-body.js';
import {FileListFooter} from './file-list-footer.js';
import {FileListHeaderPanel} from './file-list-header-panel.js';
import {FileListItem} from './services/file-list-item.js';

/**
 * Main page for authenticated user, that contains information about him and his saved files.
 */
export class FileListPage extends Component {
  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {ApiService} apiService
   * @param {TitleService}titleService
   */
  _init(apiService, titleService) {
    this._apiService = apiService;
    this._titleService = titleService;
    this._titleService.addTitleForPage('Main Page');
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const headerPanel = new FileListHeaderPanel(this.rootElement);
    headerPanel.userFullName = 'Oxxxymiron';
    const listBody = new FileListBody(this.rootElement);

    listBody.currentFolder = 'Temp';

    const itemDto = new FileListItem();
    itemDto.itemId = '1';
    itemDto.itemName = 'file1';
    itemDto.itemType = 'folder';
    itemDto.itemsAmount = 44;
    const itemDto1 = new FileListItem();
    itemDto1.itemId = '2';
    itemDto1.itemName = 'file';
    itemDto1.itemType = 'file';
    itemDto1.itemMimeType = 'pdf';
    itemDto1.itemSize = 7987864;

    listBody.fileListItems = [itemDto, itemDto1];

    new FileListFooter(this.rootElement);
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
