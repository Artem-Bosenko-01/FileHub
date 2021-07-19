/**
 * This.
 * @param {string} searchDataElementClass
 * @returns {HTMLElement}
 */
export function searchElement(searchDataElementClass) {
  return document.querySelector(`[data-fh="${searchDataElementClass}"]`);
}
