import {Component} from '../components/component.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {SearchBar} from './search-bar.js';
import {SeparateLine} from './separate-line.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {FileList} from './file-list.js';
import {FileListItemDto} from './file-list-item-dto.js';

/**
 *
 */
export class FileListBody extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    const breadcrumbs = new Breadcrumbs(this.rootElement);
    breadcrumbs.currentDirectory = 'Temp';
    new SeparateLine(this.rootElement);
    new SearchBar(this.rootElement);
    new FolderControlButtons(this.rootElement);
    const fileList = new FileList(this.rootElement);
    const dto1 = new FileListItemDto();
    const dto2 = new FileListItemDto();
    fileList.fileItems = [dto1, dto2];
  }

  /** @inheritDoc */
  get _markup() {
    return '<div class="raw page-raw"></div>';
  }
}
