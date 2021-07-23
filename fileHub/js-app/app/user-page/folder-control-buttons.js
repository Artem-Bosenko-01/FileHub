import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 * List of buttons that are responsible for managing files at folder content.
 */
export class FolderControlButtons extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('uploadButton', (slotElement) => {
      const uploadButton = new Button(slotElement);
      uploadButton.buttonId = 'upload-button';
      uploadButton.buttonIcon = 'upload';
      uploadButton.buttonClasses = ['control-folder-button upload-file-button'];
      uploadButton.onClick(() => alert('Upload new file into directory'));
      return uploadButton;
    });

    this._mount('createNewDirButton', (slotElement) => {
      const createNewDirButton = new Button(slotElement);
      createNewDirButton.buttonId = 'create-new-dir-button';
      createNewDirButton.buttonIcon = 'plus';
      createNewDirButton.buttonClasses = ['control-folder-button create-dir-button'];
      createNewDirButton.onClick(() => alert('Create new directory'));
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
