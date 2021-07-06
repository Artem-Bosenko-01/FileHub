import {Link} from '../../../app/components/link.js';

const TITLE = 'value';
const TEXT = 'This is link';
const REFERENCE = 'reference';

const {module, test} = QUnit;
let fixture;

module('Test link', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
  },
});

test('Should check create 1 link', (assert) => {
  new Link(fixture);
  assert.strictEqual(fixture.getElementsByClassName('reference').length, 1,
      'Should create 1 new link');
});

test('Should check create 1 link with title', (assert) => {
  const link = new Link(fixture);
  link.title(TITLE);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].title, TITLE,
      'Should create new link with inner text: ' + TITLE);
});

test('Should check create 1 link with inner message', (assert) => {
  const link = new Link(fixture);
  link.message(TEXT);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].innerHTML, TEXT,
      'Should create new link with inner message: ' + TEXT);
});

test('Should check create 1 link with reference', (assert) => {
  const link = new Link(fixture);
  link.reference(REFERENCE);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].href, 'http://localhost:63342/fileHub/js-app/tests/' + REFERENCE,
      'Should create new link with reference: ' + REFERENCE);
});

test('Should check create 1 link with message, title and reference', (assert) => {
  assert.expect(3);
  const link = new Link(fixture);
  link.title(TITLE);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].title, TITLE,
      'Should create new link with inner text: ' + TITLE);

  link.message(TEXT);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].innerHTML, TEXT,
      'Should create new link with inner message: ' + TEXT);

  link.reference(REFERENCE);
  assert.strictEqual(fixture.getElementsByClassName('reference')[0].href, 'http://localhost:63342/fileHub/js-app/tests/' + REFERENCE,
      'Should create new link with reference: ' + REFERENCE);
});
