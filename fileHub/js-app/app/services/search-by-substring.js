/**
 * Searches items that includes searched line.
 * @param {string} searchLine
 * @param {FileListItem[]} innerMassive
 * @returns {FileListItem[]}
 */
export function search(searchLine, innerMassive) {
  if (!innerMassive || innerMassive.length === 0) {
    throw new Error('You try to filter empty massive');
  }

  if (searchLine === '') {
    return innerMassive;
  }
  const modifiedLine = searchLine.toLowerCase();
  return innerMassive.filter((item) => _checkFields(item, modifiedLine));
}

function _checkFields(item, searchLine) {
  if (item.type === 'folder') {
    return _checkFolderFields(item, searchLine);
  }
  return _checkFileFields(item, searchLine);
}

function _checkFileFields(item, searchLine) {
  return (item.name.toLowerCase().includes(searchLine) ||
      item.mimeType.toLowerCase().includes(searchLine) ||
      item.size.toString().toLowerCase().includes(searchLine));
}

function _checkFolderFields(item, searchLine) {
  return (item.name.toLowerCase().includes(searchLine) ||
      item.itemsAmount.toString().toLowerCase().includes(searchLine) ||
      item.type.toLowerCase().includes(searchLine));
}
