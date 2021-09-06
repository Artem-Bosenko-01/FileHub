import {ActionInfo} from '../action-info.js';

/**
 *
 */
export class CloseModalWindow extends ActionInfo {
  /** @inheritDoc */
  static typeName = 'closeModalWindow'

  /** @inheritDoc */
  get typeName() {
    return CloseModalWindow.typeName;
  }
}
