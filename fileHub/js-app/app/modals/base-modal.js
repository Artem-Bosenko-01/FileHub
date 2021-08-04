import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

export class BaseModal extends Component {
  _initNestedComponents() {
    this._mount('close-button', (slotElement) => {
      const closeButton = new Button(slotElement);
      closeButton.buttonName = 'close-button';
      closeButton.buttonIcon = 'remove';
      closeButton.buttonClasses = ['button-shut-down'];
    });
  }

  _init(header) {
    this._header = header;
  }

  get _markup() {
    return `<div class="fade"></div>
<div class="modal-window">
    <div class="main">
        <div class="raw modal-raw">
            <header class="header">
                <h2 class="modal-header">${this._header}</h2>
                <slot data-fh="close-button"></slot>
                
            </header>
            <div class="data delete-data">
                <p>Are you sure want to delete "name.txt" file?</p>
                <div class="submit-box submit-modal-box submit-delete-modal-box">
                    <button title="Cancel" class="button button-cancel" name="cancel">Cancel</button>
                    <button title="Delete" class="button button-delete" name="delete">Delete</button>
                </div>
            </div>
        </div>
    </div>
</div>
`;
  }
}
