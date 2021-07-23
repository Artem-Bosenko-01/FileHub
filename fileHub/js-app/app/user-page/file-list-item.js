import {Component} from '../components/component.js';

/**
 *
 */
export class FileListItem extends Component {
  /**
   *
   * @param {FileListItemDto} itemDto
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
    return `<tr>
                    <td class="cell-folder-marker"><span class="glyphicon glyphicon-chevron-right"></span></td>
                    <td class="cell-icon"><span class="glyphicon glyphicon-folder-close"></span></td>
                    <td class="cell-name"><a class="highlight" href="">Other</a></td>
                    <td class="cell-type">Folder</td>
                    <td class="cell-file-size">—</td>
                    <td class="cell-file-action-buttons">
                        <button title="download" type="button" class="element-control-button download-element-button">
                            <span class="glyphicon glyphicon-download"></span>
                        </button>
                        <button title="delete" type="button" class="element-control-button delete-element-button">
                            <span class="glyphicon glyphicon-remove-circle"></span>
                        </button>
                    </td>
                </tr>`;
  }
}
