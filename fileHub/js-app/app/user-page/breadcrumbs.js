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
    let bodyBreadcrumb;
    if (this._currentDirectory === 'loading') {
      bodyBreadcrumb = `<li class="folder">
                                <span class="glyphicon glyphicon-repeat loading" aria-hidden="true"></span>
                         </li>`;
    } else if (this._currentDirectory) {
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

      bodyBreadcrumb = `${this._currentDirectory &&
      this._currentDirectory.itemParentFolderId ? linkPathElement : ''}${staticPathElement}`;
    } else {
      bodyBreadcrumb = `<li class="folder">
                            <span data-fh="breadcrumbs-error-message" class="error-message">
                               <span class="glyphicon glyphicon-exclamation-sign"></span> Can't load breadcrumb data.
                            </span>
                       </li>`;
    }

    return `<ul data-fh="breadcrumbs" class="path">
                ${bodyBreadcrumb}
            </ul>`;
  }
}
