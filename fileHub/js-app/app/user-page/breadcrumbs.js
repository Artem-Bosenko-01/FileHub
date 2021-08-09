import {Component} from '../components/component.js';

/**
 * The component that renders file path in main user page.
 */
export class Breadcrumbs extends Component {
  /**
   * Listener for navigation through folders.
   * @param {function(folderId: string)} listener
   */
  onFolderNameClick(listener) {
    this._onFolderNameClickEvent = listener;
    this._render();
  }

  /**
   * Id of root folder.
   * @param {string} id
   */
  set rootPage(id) {
    this._rootFolderId = id;
    this._render();
  }

  /**
   * Directory where user is located.
   * @param {FileListItem} value
   */
  set currentDirectory(value) {
    this._currentDirectory = value;
    this._render();
  }

  /**
   *
   * @param {boolean} value
   */
  set loadingCurrentFolderDataState(value) {
    this._isLoadingState = value;
    this._render();
  }

  /**
   *
   * @param {string} value
   */
  set errorMessage(value) {
    this._errorMessage = value;
    this._render();
  }

  /** @inheritDoc */
  _addEventListeners() {
    const rootFolderElement = this._getElement('root-folder');
    rootFolderElement && rootFolderElement.addEventListener('click', (event) => {
      if (this._rootFolderId) {
        this._onFolderNameClickEvent(this._rootFolderId);
      } else {
        this._onFolderNameClickEvent('');
      }
      event.preventDefault();
    });

    const previousFolderElement = this._getElement('previous-folder');
    previousFolderElement && previousFolderElement.addEventListener('click', (event) => {
      this._onFolderNameClickEvent(this._currentDirectory.parentFolderId);
      event.preventDefault();
    });
  }

  /** @inheritDoc */
  get _markup() {
    if (this._isLoadingState) {
      return `<div>
                <ul data-fh="breadcrumbs" class="path">
                    <li class="folder">
                         <span data-fh="loading-symbol" 
                         class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>
                    </li>
                </ul>
                <hr class="user-view-page-separate-line">
              </div>`;
    }

    if (this._currentDirectory) {
      const linkPathElement = `<li  class="folder">
                            <span><a data-fh="root-folder" class="highlight" href="" title="home">Home</a> </span>
                         </li>
                         <li class="folder">
                            <span><a data-fh="previous-folder" class="highlight"
                            href="" title="Previous page">..</a> </span>
                         </li>`;

      const staticPathElement = `<li data-fh="current-dir" class="folder">
                            <span data-fh="current-dir-name">${this._currentDirectory.name}</span>
                          </li>`;
      return `<div>
                <ul data-fh="breadcrumbs" class="path">
                    ${this._currentDirectory.parentFolderId ? linkPathElement : ''}${staticPathElement}
                </ul>
                <hr class="user-view-page-separate-line">
            </div>`;
    }

    if (this._errorMessage) {
      return `<div>
                <ul data-fh="breadcrumbs" class="path">
                    <li class="folder">
                            <span data-fh="breadcrumbs-error-message" class="error-message">
                               <span class="glyphicon glyphicon-exclamation-sign"></span> Can't load breadcrumb data.
                            </span>
                       </li>
                </ul>
                <hr class="user-view-page-separate-line">
            </div>`;
    }

    return `<div></div>`;
  }
}
