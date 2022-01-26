import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 * Component for searching necessary files at folder content list.
 */
export class SearchBar extends Component {
  /**
   * Loading status of searching.
   * @param {boolean} value
   */
  set isSearchingLoading(value) {
    this._isSearchingLoading = value;
    this._render();
  }

  /**
   * Listener on click search button.
   * @param {function(searchLine: string)} listener
   */
  onSearchSubmit(listener) {
    this._onSearchSubmitListener = listener;
    this._render();
  }

  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('button', (slotElement) => {
      const button = new Button(slotElement);
      button.buttonName = 'search-button';
      if (this._isSearchingLoading) {
        button.buttonIcon = 'repeat';
        button.iconClasses = ['loading'];
      }
      button.buttonTitle = 'Search';
      button.buttonClasses = ['button-search'];
      this._onSearchSubmitListener && button.onClick(() => this._onSearchSubmitListener(this._getInputValue));
      return button;
    });
  }

  /**
   * @returns {string}
   * @private
   */
  get _getInputValue() {
    const inputLine = this._getElement('search-input-panel');
    return inputLine.value;
  }

  /** @inheritDoc */
  get _markup() {
    return `<div class="search-panel">
                <div class="input-value input-search-query">
                    <input data-fh="search-input-panel" title="Search something" type="text" 
                    placeholder="Enter entity name...">
                </div>
                <div class="search-button-box">
                   <slot data-fh="button"></slot>
                </div>
            </div>`;
  }
}
