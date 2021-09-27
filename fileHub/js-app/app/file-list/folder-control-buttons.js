import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 * List of buttons that are responsible for managing files at folder content.
 */
export class FolderControlButtons extends Component {
  /**
   * Loading status.
   * @param {boolean} value
   */
  set loadingCreateNewFolderFile(value) {
    this._loadingCreateNewFolder = value;
    this._render();
  }

  /**
   * Add listener on click create new folder button.
   * @param {function()} listener
   */
  onCreateNewFolderButtonClick(listener) {
    this._onCreateNewFolderButtonClickListener = listener;
    this._render();
  }

  /**
   * Loading status.
   * @param {boolean} value
   */
  set loadingUploadFile(value) {
    this._loadingUploadFile = value;
    this._render();
  }

  /**
   * Add listener on click delete button.
   * @param {function()} listener
   */
  onUploadButtonClick(listener) {
    this._onUploadButtonClickListener = listener;
    this._render();
  }

  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('uploadButton', (slotElement) => {
      const uploadButton = new Button(slotElement);
      uploadButton.buttonName = 'upload-button';
      if (this._loadingUploadFile) {
        uploadButton.buttonIcon = 'repeat';
        uploadButton.iconClasses = ['loading'];
      } else {
        uploadButton.buttonIcon = 'upload';
      }
      uploadButton.disabled = this._loadingUploadFile;
      uploadButton.buttonClasses = ['control-folder-button upload-file-button'];
      uploadButton.onClick(this._onUploadButtonClickListener);
      return uploadButton;
    });

    this._mount('createNewDirButton', (slotElement) => {
      const createNewDirButton = new Button(slotElement);
      createNewDirButton.buttonName = 'create-new-dir-button';
      if (this._loadingCreateNewFolder) {
        createNewDirButton.buttonIcon = 'repeat';
        createNewDirButton.iconClasses = ['loading'];
      } else {
        createNewDirButton.buttonIcon = 'plus';
      }
      createNewDirButton.disabled = this._loadingCreateNewFolder;
      createNewDirButton.buttonClasses = ['control-folder-button create-dir-button'];
      createNewDirButton.onClick(this._onCreateNewFolderButtonClickListener);
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
