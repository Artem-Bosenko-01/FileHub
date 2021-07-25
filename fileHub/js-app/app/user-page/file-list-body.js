import {Component} from '../components/component.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {SearchBar} from './search-bar.js';
import {DividingLine} from './dividing-line.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {FileList} from './file-list.js';
import {FileListItem} from './services/file-list-item.js';

/**
 * Unites components for user main page.
 */
export class FileListBody extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const breadcrumbs = new Breadcrumbs(this.rootElement);
    breadcrumbs.currentDirectory = 'Temp';
    new DividingLine(this.rootElement);
    new SearchBar(this.rootElement);
    new FolderControlButtons(this.rootElement);
    const fileList = new FileList(this.rootElement);

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
    fileList.fileItems = [itemDto, itemDto1];
  }

  /** @inheritDoc */
  get _markup() {
    return '<div data-fh="file-list-body" class="raw page-raw"></div>';
  }
}
