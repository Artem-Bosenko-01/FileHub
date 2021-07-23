import {Component} from '../components/component.js';
import {FileListBody} from './file-list-body.js';
import {FileListFooter} from './file-list-footer.js';
import {FileListHeaderPanel} from './file-list-header-panel.js';

/**
 *
 */
export class FileListPage extends Component {
  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {ApiService} apiService
   * @param {TitleService}titleService
   */
  _init(apiService, titleService) {
    this._apiService = apiService;
    this._titleService = titleService;
    this._titleService.addTitleForPage('Main Page');
  }

  /** @inheritDoc */
  _initNestedComponents() {
    new FileListHeaderPanel(this.rootElement);
    new FileListBody(this.rootElement);
    new FileListFooter(this.rootElement);
  }

  /** @inheritDoc */
  get _markup() {
    return `<header class="header">
              <h1 title="TeamDev">
                 <a class="logo" href="#index">
                    TeamDev
                 </a>
              </h1>
            </header>`;
  }
}
