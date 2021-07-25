import {ListItemViewParser} from '../../../../app/user-page/services/list-item-view-parser.js';

const {module, test} = QUnit;

module('List item view parser', () => {
  test('Should return folder marker if it necessary', (assert) => {
    assert.expect(2);
    const parser = new ListItemViewParser();

    const markerForFolderType = parser.getFolderMarker('folder');
    const markerForFileType = parser.getFolderMarker('file');
    assert.ok(markerForFolderType, 'Should return folder marker tag');
    assert.notOk(markerForFileType, 'Shouldn\'t return folder marker tag');
  });

  test('Should return link with name if it necessary', (assert) => {
    assert.expect(2);
    const parser = new ListItemViewParser();
    const folderName = 'folder';
    const fileName = 'file';

    const nameForFolder = parser.getItemName('folder', folderName);
    const nameForFile = parser.getItemName('file', fileName);
    assert.equal(nameForFolder, `<a data-fh="folder-name" class="highlight" href="">${folderName}</a>`,
        'Should return link for folder name');
    assert.equal(nameForFile, fileName, 'Should return file name');
  });

  test('Should return special item icon by type or mime type', (assert) => {
    assert.expect(6);
    const parser = new ListItemViewParser();

    const folderIcon = parser.getItemIcon('folder');
    const pdfIcon = parser.getItemIcon('file', 'pdf');
    const imageIcon = parser.getItemIcon('file', 'image');
    const textIcon = parser.getItemIcon('file', 'text');
    const videoIcon = parser.getItemIcon('file', 'video');
    const audioIcon = parser.getItemIcon('file', 'audio');

    assert.equal(folderIcon, 'glyphicon-folder-close', 'Should return special folder icon');
    assert.equal(pdfIcon, 'glyphicon-book', 'Should return special pdf document icon');
    assert.equal(imageIcon, 'glyphicon-picture', 'Should return special image icon');
    assert.equal(textIcon, 'glyphicon-list-alt', 'Should return special text icon');
    assert.equal(videoIcon, 'glyphicon-film', 'Should return special video icon');
    assert.equal(audioIcon, 'glyphicon-music', 'Should return special audio icon');
  });

  test('Should return special full item type name by type or mime type', (assert) => {
    assert.expect(6);
    const parser = new ListItemViewParser();

    const folderTypeName = parser.getItemType('folder');
    const pdfTypeName = parser.getItemType('file', 'pdf');
    const imageTypeName = parser.getItemType('file', 'image');
    const textTypeName = parser.getItemType('file', 'text');
    const videoTypeName = parser.getItemType('file', 'video');
    const audioTypeName = parser.getItemType('file', 'audio');

    assert.equal(folderTypeName, 'Folder', 'Should return folder full type name');
    assert.equal(pdfTypeName, 'Pdf Document', 'Should return pdf document full type name');
    assert.equal(imageTypeName, 'JPEG Image', 'Should return image full type name');
    assert.equal(textTypeName, 'Text Document', 'Should return text full type name');
    assert.equal(videoTypeName, 'Movie', 'Should return video full type name');
    assert.equal(audioTypeName, 'Audio', 'Should return audio full type name');
  });

  test('Should return size for file or inner items amount for folder', (assert) => {
    assert.expect(5);
    const parser = new ListItemViewParser();
    const itemAmounts = 65;

    const folderInnerItemsAmount = parser.getItemSize('folder', 0, itemAmounts);
    const fileSizeBytes = parser.getItemSize('file', 564);
    const fileSizeKBytes = parser.getItemSize('file', 5645);
    const fileSizeMBytes = parser.getItemSize('file', 5647555);
    const fileSizeGBytes = parser.getItemSize('file', 5644587955);

    assert.equal(folderInnerItemsAmount, itemAmounts, 'Should return inner items amount in folder');
    assert.equal(fileSizeBytes, '564 Bytes', 'Should return file size in bytes');
    assert.equal(fileSizeKBytes, '5.513 KB', 'Should return file size in kilo bytes');
    assert.equal(fileSizeMBytes, '5.386 MB', 'Should return file size in mega bytes');
    assert.equal(fileSizeGBytes, '5.257 GB', 'Should return file size in giga bytes');
  });
});
