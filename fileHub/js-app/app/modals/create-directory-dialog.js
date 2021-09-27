import {Component} from '../components/component.js';
import {BaseModalWindow} from './base-modal.js';
import {FormInputField} from '../components/form-input-field.js';
import {Button} from '../components/button.js';

/**
 * Dialog window for getting the name of the folder and approving creating folder.
 */
export class CreateFolderDialog extends Component {
  /**
   * Add listener on close button click.
   * @param {function} listener
   */
  onClose(listener) {
    this._onCloseListener = listener;
    this._render();
  }

  /**
   * Add listener on submit deleting.
   * @param {function(folderName: string)} listener
   */
  onSubmit(listener) {
    this._onSubmitListener = listener;
    this._render();
  }

  /**
   * Error message.
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /**
   * Status of loading.
   * @param {boolean} value
   */
  set creatingInProgress(value) {
    this._isLoadingState = value;
    this._render();
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const modalWindow = new BaseModalWindow(this.rootElement, 'Create New Directory');
    modalWindow.initFormInputField((parentElement) => {
      const inputField = new FormInputField(parentElement);
      inputField.id = 'create-new-folder';
      inputField.title = 'Enter directory Name';
      inputField.inputType = 'text';
      inputField.onChange((value) => this._folderName = value);
      inputField.value = this._folderName;
      inputField.errorMessages = [this._errorMessage];
      return inputField;
    });

    modalWindow.isLoading = this._isLoadingState;
    modalWindow.onCloseButtonCLick(this._onCloseListener);
    modalWindow.initSubmitButton((parentElement) => {
      const button = new Button(parentElement);
      button.buttonName = 'create-new-folder-button';
      if (this._isLoadingState) {
        button.buttonClasses = ['button-disabled'];
        button.buttonIcon = 'repeat';
        button.iconClasses = ['loading'];
      }
      button.buttonTitle = 'Create';
      button.onClick(() => this._onSubmitListener(this._folderName));
      return button;
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<div data-fh="dialog" class="fade"></div>`;
  }
}
