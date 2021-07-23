import {Component} from '../components/component.js';
import {Button} from '../components/button.js';

/**
 *
 */
export class SearchBar extends Component {
  /** @inheritDoc */
  _initNestedComponents() {
    this._mount('button', (slotElement) => {
      const button = new Button(slotElement);
      button.buttonTitle = 'Search';
      button.buttonClasses = ['button-search'];
      return button;
    });
  }

  /** @inheritDoc */
  get _markup() {
    return `<div class="search-panel">
                <div class="input-value input-search-query">
                    <input title="Search something" type="text" placeholder="Enter entity name...">
                </div>
                <div class="search-button-box">
                   <slot data-fh="button"></slot>
                </div>
            </div>`;
  }
}
