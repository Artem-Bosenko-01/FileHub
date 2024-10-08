/**
 * This function for searching element in tests container.
 * @param {string} searchDataElementClass
 * @param {HTMLElement} container
 * @returns {HTMLElement}
 */
export default function searchElement(searchDataElementClass, container) {
  return container.querySelector(`[data-fh="${searchDataElementClass}"]`);
}
