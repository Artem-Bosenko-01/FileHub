import {Component} from '../components/component.js';
import {FileListItem} from '../file-list-item.js';
import {UserDetails} from './user-details.js';
import {LogOut} from './log-out.js';
import {Breadcrumbs} from './breadcrumbs.js';
import {SearchBar} from './search-bar.js';
import {FolderControlButtons} from './folder-control-buttons.js';
import {FileList} from './file-list.js';

/**
 * Main page for authenticated user, that contains information about him and his saved files.
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
    const userPanelElement = this._getElement('user-panel');
    const userDetails = new UserDetails(userPanelElement);
    userDetails.userFullName = 'Oxxxymiron';
    new LogOut(userPanelElement);

    const fileListBodyElement = this._getElement('file-list-body');
    const breadcrumbs = new Breadcrumbs(fileListBodyElement);
    new SearchBar(fileListBodyElement);
    new FolderControlButtons(fileListBodyElement);
    const fileList = new FileList(fileListBodyElement);

    const item1 = {
      id: '1',
      name: 'folder',
      type: 'folder',
      itemsAmount: 44,
      parentFolderId: 'as',
    };

    const item2 = {
      id: '2',
      name: 'file',
      type: 'file',
      mimeType: 'pdf',
      size: 7987864,
      parentFolderId: '54',
    };

    const itemDto = new FileListItem(item1);

    const itemDto1 = new FileListItem(item2);

    breadcrumbs.currentDirectory = itemDto;
    fileList.fileItems = [itemDto, itemDto1];
  }

  /** @inheritDoc */
  get _markup() {
    return `<div>
                <header class="header">
                  <h1 title="TeamDev">
                     <a class="logo" href="#index">
                        TeamDev
                     </a>
                  </h1>
                  <ul data-fh="user-panel" class="panel"></ul>
                </header>
                <div data-fh="file-list-body" class="raw page-raw"></div>
                <footer data-fh="footer" class="footer">
                  <ul class="social-icons">
                    <li>
                        <a title="linkedin" class="icon" href="https://www.linkedin.com/company/teamdev-ltd-/mycompany/"
                           target="_blank">
                            <img src="./images/icon-linkedin.png" alt="linkedin">
                        </a>
                    </li>
                    <li>
                        <a title="facebook" class="icon" href="https://www.facebook.com/TeamDev" target="_blank">
                            <img src="./images/icon-facebook.png" alt="facebook">
                        </a>
                    </li>
                    <li>
                        <a title="instagram" class="icon" href="https://www.instagram.com/teamdev_ltd/?hl=ru"
                           target="_blank">
                            <img src="./images/icon-instagram.png" alt="instagram">
                        </a>
                    </li>
                  </ul>
                  <p class="copyright"
                      >Copyright &copy; 2021 <a 
                      title="TeamDev" class="highlight" href="https://www.teamdev.com/" target="_blank">TeamDev</a>. All
                      rights reserved.</p>
                </footer>
            </div>
            `;
  }
}
