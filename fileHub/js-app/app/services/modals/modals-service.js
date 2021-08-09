/**
 * Manages the lifecycle of modal windows.
 */
export class ModalsService {
  /**
   * @constructor
   * @param {HTMLElement} container
   */
  constructor(container) {
    this._container = container;
  }

  /**
   * Close active modal window.
   */
  close() {
    const closeModalButton = this._container.querySelector(`[data-fh="close-button"]`);
    closeModalButton && closeModalButton.dispatchEvent(new Event('click'));
  }

  /**
   * @param {function(container: HTMLElement)} callback
   * @returns {BaseModalWindow}
   */
  open(callback) {
    return callback(this._container);
  }
}
