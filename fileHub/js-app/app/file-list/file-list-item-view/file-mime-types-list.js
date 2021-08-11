import {FileMimeType} from './file-mime-type.js';

export const FILE_MIME_TYPES_LIST = [
  new FileMimeType('jpeg', 'JPEG Image', 'glyphicon-picture'),
  new FileMimeType('pdf', 'Pdf Document', 'glyphicon-book'),
  new FileMimeType('plain', 'Text Document', 'glyphicon-list-alt'),
  new FileMimeType('video/x-msvideo', 'Movie', 'glyphicon-film'),
  new FileMimeType('x-zip-compressed', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('zip', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('vnd.rar', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('mpeg', 'Media file', 'glyphicon-music'),
  new FileMimeType('vnd.ms-powerpoint', 'Presentation', 'glyphicon-file'),
  new FileMimeType('png', 'Png Image', 'glyphicon-picture'),
  new FileMimeType('gif', 'Gif Image', 'glyphicon-picture'),
  new FileMimeType('msword', 'Msword Document', 'glyphicon-list-alt'),
  new FileMimeType('bmp', 'Bmp Image', 'glyphicon-picture'),
  new FileMimeType('vnd.openxmlformats-officedocument.wordprocessingml.document',
      'Msword Document', 'glyphicon-list-alt'),
  new FileMimeType('vnd.openxmlformats-officedocument.presentationml.presentation',
      'Presentation', 'glyphicon-file'),
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
