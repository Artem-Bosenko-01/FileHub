/**
 * Abstract base for adding html-components to document.
 */
export class Component {
  /**
   * @constructor
   * @param {HTMLElement} parentElement - is element, in which necessary create or update some components.
   * @param {any} arg
   */
  constructor(parentElement, ...arg) {
    this.parentElement = parentElement;
    this._init(...arg);
    this._render();
  }

  /**
   * Initializes component properties and markup of component.
   * @param {any} arg - parameters which need to initialize component.
   * @protected
   */
  _init(...arg) {
  }

  /**
   * Renders markup for special component.
   * @protected
   */
  get _markup() {
  }

  /**
   * Searches element in DOM by data attribute.
   * @param {string} searchDataAttribute
   * @protected
   * @returns {HTMLElement}
   */
  _getElement(searchDataAttribute) {
    return this.rootElement.querySelector(`[data-fh="${searchDataAttribute}"]`);
  }

  /**
   * Searches all elements in DOM by data attribute.
   * @param {string} searchDataAttribute
   * @protected
   * @returns {HTMLElement}
   */
  _getElements(searchDataAttribute) {
    return this.rootElement.querySelectorAll(`[data-fh="${searchDataAttribute}"]`);
  }

  /**
   * Adds some function on event to listener.
   * @protected
   */
  _addEventListeners() {
  }

  /**
   * Init nested components in process of rendering.
   * @protected
   */
  _initNestedComponents() {
  }

  /**
   * Initializes something at special place, which defines by slot id.
   * @param {string} slotId
   * @param {function} initializer
   * @protected
   */
  _mount(slotId, initializer) {
    const slot = this._getElement(slotId);
    const component = initializer(slot);
    slot.replaceWith(component.rootElement);
  }

  /**
   * Shows some components in {@link parentElement parent element}.
   * @protected
   */
  _render() {
    const {_markup} = this;
    const tempElement = document.createElement('div');
    tempElement.innerHTML = _markup;

    if (this.rootElement) {
      const existElement = tempElement.firstElementChild;
      this.rootElement.replaceWith(existElement);
      this.rootElement = existElement;
    } else {
      this.rootElement = tempElement.firstElementChild;
      this.parentElement.appendChild(this.rootElement);
    }
    this._addEventListeners();
    this._initNestedComponents();
  }
}
