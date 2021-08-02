import {FileMimeType} from './file-mime-type.js';

export const FILE_MIME_TYPES_LIST = [
  new FileMimeType('image', 'JPEG Image', 'glyphicon-picture'),
  new FileMimeType('pdf', 'Pdf Document', 'glyphicon-book'),
  new FileMimeType('text', 'Text Document', 'glyphicon-list-alt'),
  new FileMimeType('video', 'Movie', 'glyphicon-film'),
  new FileMimeType('audio', 'Audio', 'glyphicon-music'),
];

/**
 * Gets full name from types list using type short name.
 * @param {string} abbreviation
 * @returns {string}
 */
export function typeFullName(abbreviation) {
  if (abbreviation) {
    return FILE_MIME_TYPES_LIST.find((shortName) => shortName.typeAbbreviation === abbreviation).typeName;
  }
  return '';
}

/**
 * Gets icon from types list using type short name.
 * @param {string} abbreviation
 * @returns {string}
 */
export function typeIcon(abbreviation) {
  if (abbreviation) {
    return FILE_MIME_TYPES_LIST.find((shortName) => shortName.typeAbbreviation === abbreviation).typeIcon;
  }
  return '';
}
