import {typeFullName, typeIcon} from './file-mime-types-list.js';

/**
 * Configure {@link FileListItemView item view} depending on parameters which it has.
 */
export class ListItemViewParser {
  /**
   * Adds special marker for folder in the item list.
   * @param {string} itemType
   * @returns {string}
   */
  getFolderMarker(itemType) {
    if (itemType === 'folder') {
      return '<span class="glyphicon glyphicon-chevron-right"></span>';
    }
    return '';
  }

  /**
   * Adds name for item view in the file list.
   * @param {string} itemType
   * @param {string} itemId
   * @param {string} itemName
   * @returns {string}
   */
  getItemName(itemType, itemId, itemName) {
    if (itemType === 'folder') {
      return `<a data-fh="folder-name" class="highlight" href="#index/${itemId}">${itemName}</a>`;
    } else {
      return itemName;
    }
  }

  /**
   * Adds icon for item view in the file list.
   * @param {string} itemType
   * @param {string} itemMimeType
   * @returns {string}
   */
  getItemIcon(itemType, itemMimeType) {
    if (itemType === 'folder') {
      return 'glyphicon-folder-close';
    } else {
      return typeIcon(itemMimeType);
    }
  }

  /**
   * Adds type of item for item view in the file list.
   * @param {string} itemType
   * @param {string} [itemMimeType]
   * @returns {string}
   */
  getItemType(itemType, itemMimeType) {
    if (itemType === 'folder') {
      return 'Folder';
    } else {
      return typeFullName(itemMimeType);
    }
  }

  /**
   * Converts size of the file to necessary format, which understandable for the user.
   * @param {string} itemType
   * @param {number} [itemSize]
   * @param {number} [itemsAmount]
   * @returns {string}
   */
  getItemSize(itemType, itemSize, itemsAmount) {
    if (itemType === 'folder') {
      return itemsAmount.toString();
    } else {
      return this._formatBytes(itemSize, 3);
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
}
