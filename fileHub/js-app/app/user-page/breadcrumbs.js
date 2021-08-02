import {Component} from '../components/component.js';

/**
 * The component that renders file path in main user page.
 */
export class Breadcrumbs extends Component {
  /**
   * Directory where user is located.
   * @param {FileListItem} value
   */
  set currentDirectory(value) {
    this._currentDirectory = value;
    this._render();
  }

  /** @inheritDoc */
  get _markup() {
    const errorState = `<li class="folder">
                            <span data-fh="breadcrumbs-error-message" class="error-message">
                               <span class="glyphicon glyphicon-exclamation-sign"></span> Can't load breadcrumb data.
                            </span>
                       </li>`;

    const linkPathElement = `<li class="folder">
                            <span><a class="highlight" href="#index" title="home">Home</a> </span>
                         </li>
                         <li class="folder">
                            <span><a class="highlight" href="#index" title="Previous page">..</a> </span>
                         </li>`;

    const staticPathElement = `<li data-fh="current-dir" class="folder">
                            <span data-fh="current-dir-name">${this._currentDirectory &&
                                                                this._currentDirectory.itemName}</span>
                          </li>`;

    const validState = `${this._currentDirectory &&
                          this._currentDirectory.itemParentFolderId ? linkPathElement : ''}${staticPathElement}`;

    return `    <div>
                  <ul data-fh="breadcrumbs" class="path">
                      ${this._currentDirectory ? validState : errorState}
                  </ul>
                  <hr class="user-view-page-separate-line">
                </div>`;
  }
}
