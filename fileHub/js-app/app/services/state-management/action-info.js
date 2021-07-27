/**
 * Abstract base contains info for some {@link ActionExecutor executor}.
 */
export class ActionInfo {
  /**
   * name of action type.
   * @type {string}
   */
  static typeName = '';

  /** @returns {string} */
  get typeName() {
    return ActionInfo.typeName
  }
}