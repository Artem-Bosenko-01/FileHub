import {FileMimeType} from './file-mime-type.js';

export const FILE_MIME_TYPES_LIST = [
  new FileMimeType('image/jpeg', 'JPEG Image', 'glyphicon-picture'),
  new FileMimeType('application/pdf', 'Pdf Document', 'glyphicon-book'),
  new FileMimeType('text/plain', 'Text Document', 'glyphicon-list-alt'),
  new FileMimeType('video/x-msvideo', 'Movie', 'glyphicon-film'),
  new FileMimeType('application/x-zip-compressed', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('application/zip', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('application/vnd.rar', 'Archive', 'glyphicon-inbox'),
  new FileMimeType('audio/mpeg', 'Music', 'glyphicon-music'),
  new FileMimeType('video/mpeg', 'Video', 'glyphicon-film'),
  new FileMimeType('application/vnd.ms-powerpoint', 'Presentation', 'glyphicon-file'),
  new FileMimeType('image/png', 'Png Image', 'glyphicon-picture'),
  new FileMimeType('image/gif', 'Gif Image', 'glyphicon-picture'),
  new FileMimeType('application/msword', 'Msword Document', 'glyphicon-list-alt'),
  new FileMimeType('image/bmp', 'Bmp Image', 'glyphicon-picture'),
  new FileMimeType('application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      'Msword Document', 'glyphicon-list-alt'),
  new FileMimeType('application/vnd.openxmlformats-officedocument.presentationml.presentation',
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
