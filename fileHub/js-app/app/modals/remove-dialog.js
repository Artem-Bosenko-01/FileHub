import {Component} from '../components/component.js';
import {BaseModalWindow} from './base-modal.js';
import {Button} from '../components/button.js';

/**
 *
 */
export class RemoveDialogWindow extends Component {
  /**
   * Add listener on submit deleting.
   * @param {function} listener
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
  set deletingInProgress(value) {
    this._isLoadingState = value;
    this._render();
  }

  /** @inheritDoc */
  _init(removingModel) {
    this._removingModel = removingModel;
  }

  /** @inheritDoc */
  _initNestedComponents() {
    const modalWindow = new BaseModalWindow(this.rootElement, 'Delete Item');
    if (this._errorMessage) {
      modalWindow.errorMessage = this._errorMessage;
    } else {
      modalWindow.innerTextForBody = `Are you sure want to delete "${this._removingModel.name}" item?`;
    }
    modalWindow.isLoading = this._isLoadingState;
    modalWindow.onCloseButtonCLick(() => this.rootElement.remove());
    modalWindow.initSubmitButton((parentElement) => {
      const button = new Button(parentElement);
      button.buttonName = 'delete-button';
      if (this._isLoadingState) {
        button.buttonClasses = ['button-delete', 'button-disabled'];
        button.buttonIcon = 'repeat';
        button.iconClasses = ['loading'];
      } else {
        button.buttonClasses = ['button-delete'];
      }
      button.buttonTitle = 'Delete';
      button.onClick(this._onSubmitListener);
      return button;
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<div data-fh="dialog" class="fade"></div>`;
  }
}
