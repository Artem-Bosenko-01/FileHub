import {Component} from '../components/component.js';
import {FileListItemView} from './file-list-item-view/file-list-item-view.js';

/**
 * Component that renders folder content list.
 */
export class FileList extends Component {
  /**
   * Add listener on click delete button.
   * @param {function(item: FileListItem)} listener
   */
  onDeleteButtonClick(listener) {
    this._onDeleteButtonClick = listener;
    this._render();
  }

  /**
   * Listener for navigation through folders.
   * @param {function(folderId: string)} listener
   */
  onFolderClick(listener) {
    this._onFolderClickedEvent = listener;
    this._render();
  }

  /**
   *
   * @param {FileListItem[]} value
   */
  set fileItems(value) {
    this._fileItems = value;
    this._render();
  }

  /**
   *
   * @param {boolean} value
   */
  set loadingFolderContentState(value) {
    this._isLoadingState = value;
    this._render();
  }

  /**
   *
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /** @inheritDoc */
  _init(...arg) {
    this._fileListName = 'fileListItems';
  }

  /** @inheritDoc */
  _initNestedComponents() {
    if (this._fileItems && this._fileItems.length > 0) {
      const tableElement = this._getElement(this._fileListName);
      this._fileItems.forEach((fileItem) => {
        const itemView = new FileListItemView(tableElement, fileItem);
        itemView.onFolderNameCLicked(this._onFolderClickedEvent);
        itemView.onDeleteButtonClick(this._onDeleteButtonClick);
      });
    }
  }

  /** @inheritDoc */
  get _markup() {
    if (this._isLoadingState) {
      return `<div class="table-box">
                <table class="table">
                     <tbody data-fh="${this._fileListName}">
                        <tr class="empty-directory">
                            <td>
                                <p class="empty-directory-message">
                                    <span data-fh="loading-symbol" 
                                    class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>
                                </p>
                            </td>
                        </tr>
                     </tbody>
                </table>
            </div>`;
    }

    if (this._fileItems) {
      const emptyState = `<tr class="empty-directory">
                            <td>
                                <p data-fh="empty-file-list-message" 
                                    class="empty-directory-message">There are no files/directories created yet.</p>
                            </td>
                        </tr>`;

      return `<div class="table-box">
                <table class="table">
                     <tbody data-fh="${this._fileListName}">
                        ${this._fileItems.length === 0 ? emptyState : ''}
                     </tbody>
                </table>
            </div>`;
    }

    if (this._errorMessage) {
      return `<div class="table-box">
                <table class="table">
                     <tbody data-fh="${this._fileListName}">
                        <tr class="empty-directory">
                            <td>
                                <p class="empty-directory-message">
                                    <span data-fh="file-list-error-message" class="error-message">
                                        <span 
                                        class="glyphicon glyphicon-exclamation-sign"></span> Can't load directory data.
                                    </span>
                                </p>
                            </td>
                          </tr>
                     </tbody>
                </table>
            </div>`;
    }

    return `<div></div>`;
  }
}
