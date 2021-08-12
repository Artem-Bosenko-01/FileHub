/**
 * Allows the user to download file.
 * @param {Document} document
 * @param {{name: string, mimeType: string, content: string,}} fileContent
 */
export function downloadFile(document, fileContent) {
  const link = document.createElement('a');
  const blob = new Blob([fileContent.content], {type: fileContent.mimeType});
  const objectURL = URL.createObjectURL(blob);
  link.href = objectURL;
  link.download = fileContent.name;

  link.click();
  URL.revokeObjectURL(objectURL);
}
