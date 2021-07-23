import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 *
 */
export class FolderControlButtons extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('uploadButton', (slotElement) => {
      const uploadButton = new Button(slotElement);
      uploadButton.buttonIcon = 'upload';
      uploadButton.buttonClasses = ['control-folder-button'];
      return uploadButton;
    });

    this._mount('createNewDirButton', (slotElement) => {
      const createNewDirButton = new Button(slotElement);
      createNewDirButton.buttonIcon = 'plus';
      createNewDirButton.buttonClasses = ['control-folder-button'];
      return createNewDirButton;
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<div class="control-folder-buttons-box">
                <slot data-fh="uploadButton"></slot>
                <slot data-fh="createNewDirButton"></slot>
            </div>`;
  }
}
