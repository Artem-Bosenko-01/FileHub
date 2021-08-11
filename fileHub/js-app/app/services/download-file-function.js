/**
 * Allows the user to download file.
 * @param {Document} document
 * @param {Blob} fileContent
 */
export function downloadFile(document, fileContent) {
  const link = document.createElement('a');
  const objectURL = URL.createObjectURL(new Blob(['svdvdsvd']));
  link.href = objectURL;
  link.download = 'true';

  link.click();
  URL.revokeObjectURL(objectURL);
}
