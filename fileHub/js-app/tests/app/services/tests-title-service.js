import {TitleService} from '../../../app/services/title-service.js';

const {module, test} = QUnit;

const APPLICATION_NAME = 'application';
const PAGE_NAME = 'page';

module('TitleService', () => {
  const documentMock = {
    title: '',
  };

  test('Should successfully add title to application page', (assert) => {
    const service = new TitleService(APPLICATION_NAME, documentMock);
    service.addTitleForPage(PAGE_NAME);
    const FULL_TITLE_NAME = PAGE_NAME + ' - ' + APPLICATION_NAME;
    assert.equal(documentMock.title, FULL_TITLE_NAME, 'Should add title to document');
  });
});
