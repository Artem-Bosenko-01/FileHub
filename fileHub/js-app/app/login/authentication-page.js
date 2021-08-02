import {Component} from '../components/component.js';
import {AuthenticationForm} from './authentication-form.js';

/**
 * Creates authentication form and puts some API service for it.
 */
export class AuthenticationPage extends Component {
  /**
   * @inheritDoc
   * Adds api and title services to page
   * @param {ApiService} apiService
   * @param {TitleService}titleService
   */
  _init(apiService, titleService) {
    this._apiService = apiService;
    this._titleService = titleService;
    this._titleService.addTitleForPage('Authentication');
  }

  /** @inheritDoc */
  _initNestedComponents() {
    debugger;
    const form = new AuthenticationForm(this.rootElement);
    form.onSubmit(async (credentials) => {
      const {email, password} = credentials;
      try {
        const response = await this._apiService.logIn(email, password);
        alert(`${response}`);
        window.location.hash = 'index';
      } catch (error) {
        this.clearErrorMessages();
        form.addServerError(error.message);
      }
    });
  }

  /**
   * Remove server error messages, which was rendered after previous response.
   * @returns {void}
   */
  clearErrorMessages() {
    const errors = this._getElements('server-error');
    if (errors) {
      [...errors].forEach(
          (error)=> error.remove(),
      );
    }
  }

  /** @inheritDoc */
  get _markup() {
    return `<header>
             <h1 title="TeamDev">
                    <a class="logo" href="https://www.teamdev.com/" target="_blank">
                        TeamDev
                    </a>
                </h1>
            </header>`;
  }
}
