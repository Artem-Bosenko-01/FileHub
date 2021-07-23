import {Component} from '../components/component.js';
import {FileListItem} from './file-list-item.js';

/**
 *
 */
export class FileList extends Component {
  /**
   * @constructor
   * @param {HTMLElement} parentElement
   */
  constructor(parentElement) {
    super(parentElement);
  }

  /** @inheritDoc */
  _initNestedComponents() {
    if (this._fileItems && this._fileItems.length > 0) {
      const tableElement = this._getElement('fileListItems');
      this._fileItems.forEach((fileItem) => {
        const item = new FileListItem(tableElement);
        item.listItemFromDto = fileItem;
      });
    }
  }

  /**
   *
   * @param {FileListItemDto[]} value
   */
  set fileItems(value) {
    this._fileItems = value;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    const invalidState = `<tr class="empty-directory">
                            <td>
                                <p class="empty-directory-message">
                                    <span class="error-message">
                                        <span 
                                        class="glyphicon glyphicon-exclamation-sign"></span> Can't load directory data.
                                    </span>
                                </p>
                            </td>
                          </tr>`;

    const emptyState = `<tr class="empty-directory">
                            <td>
                                <p class="empty-directory-message">There are no files/directories created yet.</p>
                            </td>
                        </tr>`;

    return `<div class="table-box">
                <table class="table">
                     <tbody data-fh="fileListItems">
                        ${!this._fileItems ? invalidState : this._fileItems.length <= 0 ? emptyState : ''}
                     </tbody>
                </table>
            </div>`;
  }
}
