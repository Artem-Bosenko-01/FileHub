import {Component} from '../components/component.js';
import {BaseModalWindow} from './base-modal.js';
import {Button} from '../components/button.js';

/**
 *
 */
export class RemoveDialogWindow extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    this._itemName = 'asccaac';
    const modalWindow = new BaseModalWindow(this.rootElement, 'Delete Item');
    modalWindow.innerTextForBody = `Are you sure want to delete "${this._itemName}" item?`;
    modalWindow.initSubmitButton((parentElement) => {
      const button = new Button(parentElement);
      button.buttonName = 'delete-button';
      button.buttonTitle = 'Delete';
      button.buttonClasses = ['button-delete'];
      return button;
    });
  }
  /** @inheritDoc */
  get _markup() {
    return `<div class="fade"></div>`;
  }
}
