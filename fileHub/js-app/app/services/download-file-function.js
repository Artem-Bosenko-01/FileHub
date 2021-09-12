/**
 * Allows the user to download file.
 * @param {Document} document
 * @param {File} fileContent
 */
export function downloadFile(document, fileContent) {
  const link = document.createElement('a');
  const objectURL = URL.createObjectURL(fileContent);
  link.href = objectURL;
  link.download = fileContent.name;

  link.click();
  URL.revokeObjectURL(objectURL);
}
