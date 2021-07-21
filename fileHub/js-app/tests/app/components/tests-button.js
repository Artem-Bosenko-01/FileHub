import {Button} from '../../../app/components/button.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;
let button;

module('Button', {
  beforeEach: () => {
    const fixture = document.getElementById('qunit-fixture');
    button = new Button(fixture);
  },
});

test('Should create button', (assert) => {
  assert.ok(searchElement('button'),
      'Should add 1 button with class button');
});

test('Should create button with title', (assert) => {
  const title = 'title';
  button.buttonTitle = title;

  assert.equal(searchElement('button').innerHTML, title,
      'Should add inner text to button: ' + title);
});

test('Should create button with icon and icon\'s classes', (assert) => {
  const icon = 'repeat';
  button.buttonIcon = icon;
  button.iconClasses = ['loading', 'buttons'];
  button.addIconClass('class');
  const iconElement = searchElement('button').getElementsByTagName('span');
  assert.equal(iconElement.length, 1, 'Should span with icon class to button: ');
  const iconClasses = iconElement[0].classList;

  assert.ok(iconClasses.contains(`glyphicon-${icon}`), 'Should span special icon class');
  const isExistIconClasses = iconClasses.contains('loading') && iconClasses.contains('buttons') &&
      iconClasses.contains('class');

  assert.true(isExistIconClasses, 'Should add 3 custom icon classes to span');
});
