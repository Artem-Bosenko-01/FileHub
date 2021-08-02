import {Component} from '../components/component.js';
import {FileListItemView} from './file-list-item-view.js';

/**
 * Component that renders folder content list.
 */
export class FileList extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    if (this._fileItems && this._fileItems.length > 0) {
      const tableElement = this._getElement('fileListItems');
      this._fileItems.forEach((fileItem) => {
        const item = new FileListItemView(tableElement);
        item.listItemFromDto = fileItem;
      });
    }
  }

  /**
   *
   * @param {string|FileListItem[]} value
   */
  set fileItems(value) {
    this._fileItems = value;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    // TODO Refactor execution flow. You're not a C programmer.

    let directoryContent;

    if (this._fileItems === 'loading') {
      directoryContent = `<tr class="empty-directory">
                    <td>
                        <p class="empty-directory-massage"><span class="glyphicon glyphicon-repeat loading" 
                        aria-hidden="true"></span></p>
                    </td>
                </tr>`;
    } else if (this._fileItems) {
      const emptyState = `<tr class="empty-directory">
                            <td>
                                <p data-fh="empty-file-list-message" 
                                    class="empty-directory-message">There are no files/directories created yet.</p>
                            </td>
                        </tr>`;
      this._fileItems.length <= 0 ? directoryContent = emptyState : directoryContent = '';
    } else {
      directoryContent = `<tr class="empty-directory">
                            <td>
                                <p class="empty-directory-message">
                                    <span data-fh="file-list-error-message" class="error-message">
                                        <span 
                                        class="glyphicon glyphicon-exclamation-sign"></span> Can't load directory data.
                                    </span>
                                </p>
                            </td>
                          </tr>`;
    }

    return `<div class="table-box">
                <table class="table">
                     <tbody data-fh="fileListItems">
                        ${directoryContent}
                     </tbody>
                </table>
            </div>`;
  }
}
