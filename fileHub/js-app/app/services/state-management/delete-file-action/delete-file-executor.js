import ActionExecutor from '../action-execution.js';

/**
 * Execute deleting file process.
 */
export default class DeleteFileExecutor extends ActionExecutor {
  /**
   * @inheritDoc
   * @param {DeleteFile} actionInfo
   * @param {object} services
   * @param {object} state
   * @param {function} mutate
   */
  apply(actionInfo, services, state, mutate) {
    super.apply(actionInfo);
  }
}
