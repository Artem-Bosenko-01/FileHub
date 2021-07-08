/**
 * This is abstract base for adding html-components to document.
 */
export class Component {
  /**
   * @constructor
   * @param {HTMLElement} parentElement - is element, in which necessary create or update some components.
   */
  constructor(parentElement) {
    this.init(parentElement);
  }

  init(parentElement) {
    this.parentElement = parentElement;
    this.render();
  }

  /**
   * This is property to render markup for special component.
   */
  get markup() {
  }

  /**
   * This is function for searching element in DOM.
   * @param {string} searchClass
   * @returns {HTMLElement}
   */
  getElement(searchClass) {
    return document.querySelector(`[data-fh="${searchClass}"]`);
  }

  /**
   *
   */
  addEventListeners() {
  }

  initNestedComponents() {
  }

  mount(slotId, initializer) {
    const slot = this.getElement(slotId);
    initializer(slot);
  }

  /**
   * This is function to render some components in {@link parentElement parent element}.
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
