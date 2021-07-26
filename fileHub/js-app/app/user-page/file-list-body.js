import {Component} from '../components/component.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {SearchBar} from './search-bar.js';
import {DividingLine} from './dividing-line.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {FileList} from './file-list.js';

/**
 * Unites components for user main page.
 */
export class FileListBody extends Component {
  /**
   * Folder where the user is now.
   * @param {FileListItem} value
   */
  set currentFolder(value) {
    this._currentFolder = value;
    this._render();
  }

  /**
   * List of items for user data table.
   * @param {FileListItem[]} value
   */
  set fileListItems(value) {
    this._fileListItems = value;
    this._render();
  }
  /** @inheritDoc */
  _initNestedComponents() {
    const breadcrumbs = new Breadcrumbs(this.rootElement);
    breadcrumbs.currentDirectory = this._currentFolder;
    new DividingLine(this.rootElement);
    new SearchBar(this.rootElement);
    new FolderControlButtons(this.rootElement);
    const fileList = new FileList(this.rootElement);
    fileList.fileItems = this._fileListItems;
  }

  /** @inheritDoc */
  get _markup() {
    return '<div data-fh="file-list-body" class="raw page-raw"></div>';
  }
}
