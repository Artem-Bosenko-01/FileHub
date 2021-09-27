/**
 * Type of file, that saves the full name and special icon for it.
 */
export class FileMimeType {
  /**
   * @constructor
   * @param {string} typeAbbreviation
   * @param {string} typeName
   * @param {string} typeIcon
   */
  constructor(typeAbbreviation, typeName, typeIcon) {
    this._typeAbbreviation = typeAbbreviation;
    this._typeName = typeName;
    this._typeIcon = typeIcon;
  }

  /**
   * @returns {string}
   */
  get typeAbbreviation() {
    return this._typeAbbreviation;
  }

  /**
   * @returns {string}
   */
  get typeName() {
    return this._typeName;
  }

  /**
   * @returns {string}
   */
  get typeIcon() {
    return this._typeIcon;
  }
}
