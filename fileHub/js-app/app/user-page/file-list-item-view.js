import {Component} from '../components/component.js';
import {ListItemViewParser} from './services/list-item-view-parser.js';

/**
 * Line from {@link FileList folder content list}.
 */
export class FileListItemView extends Component {
  /**
   * @param {FileListItem} itemDto
   */
  set listItemFromDto(itemDto) {
    this._itemId = itemDto.itemId;
    this._itemName = itemDto.itemName;
    this._itemType = itemDto.itemType;
    this._itemSize = itemDto.itemSize;
    this._itemMimeType = itemDto.itemMimeType;
    this._itemsAmount = itemDto.itemsAmount;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    const parser = new ListItemViewParser();
    const downloadButton = `<button data-fh="download-button" title="download" type="button" 
                                    class="element-control-button download-element-button">
                            <span class="glyphicon glyphicon-download"></span>
                        </button>`;
    const uploadButton = `<button data-fh="upload-button" title="download" type="button" 
class="element-control-button upload-element-button"><span class="glyphicon glyphicon-upload"></span></button>`;
    return `<tr>
                    <td data-fh="folder-marker" 
                        class="cell-folder-marker">${parser.getFolderMarker(this._itemType)}</td>
                    <td data-fh="icon" class="cell-icon">
                        <span class="glyphicon ${parser.getItemIcon(this._itemType, this._itemMimeType)}"></span>
                    </td>
                    <td data-fh="name" class="cell-name">${parser.getItemName(this._itemType, this._itemName)}</td>
                    <td data-fh="type" class="cell-type">${parser.getItemType(this._itemType, this._itemMimeType)}</td>
                    <td data-fh="size"
                    class="cell-file-size">${parser.getItemSize(this._itemType, this._itemSize, this._itemsAmount)}</td>
                    <td class="cell-file-action-buttons">
                        ${this._itemType === 'folder' ? uploadButton : downloadButton}
                        <button title="delete" type="button" class="element-control-button delete-element-button">
                            <span class="glyphicon glyphicon-remove-circle"></span>
                        </button>
                    </td>
                </tr>`;
  }
}
