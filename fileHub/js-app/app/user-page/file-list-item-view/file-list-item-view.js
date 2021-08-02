import {Component} from '../../components/component.js';
import {typeFullName, typeIcon} from './file-mime-types-list.js';

/**
 * Line from {@link FileList folder content list}.
 */
export class FileListItemView extends Component {
  /**
   * Event for navigation through folders.
   * @param {function(folderId: string)} value
   */
  set onNavigate(value) {
    this._navigate = value;
    this._render();
  }

  /** @inheritDoc */
  _init(itemDto) {
    this._item = itemDto;
  }

  /** @inheritDoc */
  _addEventListeners() {
    const folderNameElement = this._getElement('folder-name');
    folderNameElement && folderNameElement.addEventListener('click', (event) => {
      this._navigate(this._item.id);
      event.preventDefault();
    });
  }

  /**
   * Adds special marker for folder in the item list.
   * @private
   * @returns {string}
   */
  _getFolderMarker() {
    if (this._item.type === 'folder') {
      return '<span class="glyphicon glyphicon-chevron-right"></span>';
    }
    return '';
  }

  /**
   * Adds name for item view in the file list.
   * @private
   * @returns {string}
   */
  _getItemName() {
    if (this._item.type === 'folder') {
      return `<a data-fh="folder-name" class="highlight" href="">${this._item.name}</a>`;
    } else {
      return this._item.name;
    }
  }

  /**
   * Adds icon for item view in the file list.
   * @private
   * @returns {string}
   */
  _getItemIcon() {
    if (this._item.type === 'folder') {
      return 'glyphicon-folder-close';
    } else {
      return typeIcon(this._item.mimeType);
    }
  }

  /**
   * Adds type of item for item view in the file list.
   * @private
   * @returns {string}
   */
  _getItemType() {
    if (this._item.type === 'folder') {
      return 'Folder';
    } else {
      return typeFullName(this._item.mimeType);
    }
  }

  /**
   * Converts size of the file to necessary format, which understandable for the user.
   * @private
   * @returns {string}
   */
  _getItemSize() {
    if (this._item.type === 'folder') {
      return this._item.itemsAmount.toString();
    } else {
      return this._formatBytes(this._item.size, 1);
    }
  }

  /**
   *
   * @param {number} bytes
   * @param {number} decimals
   * @returns {string}
   * @private
   */
  _formatBytes(bytes, decimals = 2) {
    if (bytes === 0) return '0 Bytes';

    const k = 1024;
    const dm = decimals < 0 ? 0 : decimals;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

    const i = Math.floor(Math.log(bytes) / Math.log(k));

    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  }

  /** @inheritDoc */
  get _markup() {
    const downloadButton = `<button data-fh="download-button" title="download" type="button" 
                                    class="element-control-button download-element-button">
                            <span class="glyphicon glyphicon-download"></span>
                        </button>`;
    const uploadButton = `<button data-fh="upload-button" title="download" type="button" 
class="element-control-button upload-element-button"><span class="glyphicon glyphicon-upload"></span></button>`;

    return `<tr>
                    <td data-fh="folder-marker" class="cell-folder-marker">${this._getFolderMarker()}</td>
                    <td data-fh="icon" class="cell-icon"><span class="glyphicon ${this._getItemIcon()}"></span></td>
                    <td data-fh="name" class="cell-name">${this._getItemName()}</td>
                    <td data-fh="type" class="cell-type">${this._getItemType()}</td>
                    <td data-fh="size" class="cell-file-size">${this._getItemSize()}</td>
                    <td class="cell-file-action-buttons">
                        ${this._item.itemType === 'folder' ? uploadButton : downloadButton}
                        <button title="delete" type="button" class="element-control-button delete-element-button">
                            <span class="glyphicon glyphicon-remove-circle"></span>
                        </button>
                    </td>
                </tr>`;
  }
}
