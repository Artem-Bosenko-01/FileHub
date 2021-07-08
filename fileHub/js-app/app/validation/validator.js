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
    const results = await Promise.allSettled(promises);
    results
        .filter((result) => result.status === 'rejected')
        .forEach((result) => result.reason.component.errorMessage = result.reason.message);

    throw new Error();
  }
}
