/**
 * Allows the user to choose file for uploading into the application.
 * @param {Document} document
 * @returns {Promise<File, void>}
 */
export function uploadFile(document) {
  return new Promise((resolve) => {
    const inputElement = document.createElement('input');
    inputElement.type = 'file';

    inputElement.addEventListener('change', () => {
      resolve(inputElement.files[0]);
    });
    inputElement.click();
  });
}
