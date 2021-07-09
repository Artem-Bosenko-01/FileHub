/**
 *
 */
export class Validator {
  /**
   *
   * @param {ValidationConfiguration} validationConfig
   */
  constructor(validationConfig) {
    this._configuration = validationConfig;
  }

  async validate() {
    const promises = [];
    this._configuration.rules.forEach((rule) => promises.push(rule.validationRule));
    return await Promise.allSettled(promises);
  }
}
