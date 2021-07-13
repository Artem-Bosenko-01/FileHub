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
    this.init(...arg);
    this.render();
  }

  /**
   * Initializes component properties and markup of component.
   */
  init() {
  }

  /**
   * Renders markup for special component.
   */
  get markup() {
  }

  /**
   * Searches element in DOM.
   * @param {string} searchClass
   * @returns {HTMLElement}
   */
  getElement(searchClass) {
    return document.querySelector(`[data-fh="${searchClass}"]`);
  }

  /**
   * Adds some function on event to listener.
   */
  addEventListeners() {
  }

  /**
   * Init nested components after rendering.
   */
  initNestedComponents() {
  }

  /**
   * Initializes something at special place, which defines by slot id.
   * @param {string} slotId
   * @param {function} initializer
   */
  mount(slotId, initializer) {
    const slot = this.getElement(slotId);
    const component = initializer(slot);
    slot.replaceWith(component.rootElement);
  }

  /**
   * Shows some components in {@link parentElement parent element}.
   */
  render() {
    const {markup} = this;
    const tempElement = document.createElement('div');
    tempElement.innerHTML = markup;

    if (this.rootElement) {
      const existElement = tempElement.firstElementChild;
      this.rootElement.replaceWith(existElement);
      this.rootElement = existElement;
    } else {
      this.rootElement = tempElement.firstElementChild;
      this.parentElement.appendChild(this.rootElement);
    }
    this.addEventListeners();
    this.initNestedComponents();
  }
}
