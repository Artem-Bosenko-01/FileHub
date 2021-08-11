import {Component} from '../../components/component.js';
import {typeFullName, typeIcon} from './file-mime-types-list.js';
import {Button} from '../../components/button.js';

/**
 * Line from {@link FileList folder content list}.
 */
export class FileListItemView extends Component {
  /**
   * Loading status of download file.
   * @param {boolean} value
   */
  set isLoadingDownloadFile(value) {
    this._loadingDownloadFile = value;
    this._render();
  }

  /**
   * Add listener on click download button.
   * @param {function(item: FileListItem)} listener
   */
  onDownloadButtonClick(listener) {
    this._onDownloadButtonClickListener = listener;
    this._render();
  }

  /**
   * Loading status.
   * @param {boolean} value
   */
  set isLoadingUploadFile(value) {
    this._loadingUploadFile = value;
    this._render();
  }

  /**
   * Add listener on click delete button.
   * @param {function(item: FileListItem)} listener
   */
  onUploadButtonClick(listener) {
    this._onUploadButtonClickListener = listener;
    this._render();
  }

  /**
   * Add listener on click delete button.
   * @param {function(item: FileListItem)} listener
   */
  onDeleteButtonClick(listener) {
    this._onDeleteButtonClickListener = listener;
    this._render();
  }

  /**
   * Listener for navigation through folders.
   * @param {function(folderId: string)} listener
   */
  onFolderNameCLicked(listener) {
    this._onFolderNameCLickedEvent = listener;
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
      this._onFolderNameCLickedEvent(this._item.id);
      event.preventDefault();
    });
  }

  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('delete-button', (slotElement) => {
      const button = new Button(slotElement);
      button.buttonName = 'delete-button';
      button.buttonClasses = ['element-control-button', 'delete-element-button'];
      button.buttonIcon = 'remove-circle';
      this._onDeleteButtonClickListener && button.onClick(() => this._onDeleteButtonClickListener(this._item));
      return button;
    });

    if (this._item.type === 'folder') {
      this._mount('upload-button', (slotElement) => {
        const button = new Button(slotElement);
        button.buttonName = 'upload-button';
        button.buttonClasses = ['element-control-button', 'upload-element-button'];
        if (this._loadingUploadFile) {
          button.buttonIcon = 'repeat';
          button.iconClasses = ['loading'];
        } else {
          button.buttonIcon = 'upload';
        }
        button.isButtonDisabled = this._loadingUploadFile;
        this._onDeleteButtonClickListener && button.onClick(() => this._onUploadButtonClickListener(this._item));
        return button;
      });
    } else {
      this._mount('download-button', (slotElement) => {
        const button = new Button(slotElement);
        button.buttonName = 'download-button';
        button.buttonClasses = ['element-control-button', 'download-element-button'];
        if (this._loadingDownloadFile) {
          button.buttonIcon = 'repeat';
          button.iconClasses = ['loading'];
        } else {
          button.buttonIcon = 'download';
        }
        button.isButtonDisabled = this._loadingDownloadFile;
        this._onDownloadButtonClickListener && button.onClick(() => this._onDownloadButtonClickListener(this._item));
        return button;
      });
    }
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
    const downloadButton = `<slot data-fh="download-button"></slot>`;
    const uploadButton = `<slot data-fh="upload-button"></slot>`;

    return `<tr>
                    <td data-fh="folder-marker" class="cell-folder-marker">${this._getFolderMarker()}</td>
                    <td data-fh="icon" class="cell-icon"><span class="glyphicon ${this._getItemIcon()}"></span></td>
                    <td data-fh="name" class="cell-name">${this._getItemName()}</td>
                    <td data-fh="type" class="cell-type">${this._getItemType()}</td>
                    <td data-fh="size" class="cell-file-size">${this._getItemSize()}</td>
                    <td class="cell-file-action-buttons">
                        ${this._item.type === 'folder' ? uploadButton : downloadButton}
                        <slot data-fh="delete-button"></slot>
                    </td>
                </tr>`;
  }
}
