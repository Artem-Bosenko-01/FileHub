import {Button} from '../../../app/components/button.js';
import {searchElement} from '../search-element-function.js';

const {module, test} = QUnit;
let button;
let fixture;

module('Button', {
  beforeEach: () => {
    fixture = document.getElementById('qunit-fixture');
    button = new Button(fixture);
  },
});

test('Should create button', (assert) => {
  assert.equal(fixture.getElementsByTagName('button').length, 1,
      'Should add 1 button with class button');
});

test('Should create button with title', (assert) => {
  const title = 'title';
  button.buttonTitle = title;

  assert.equal(searchElement('button', fixture).innerHTML, title,
      'Should add inner text to button: ' + title);
});

test('Should create button with icon and icon\'s classes', (assert) => {
  const icon = 'repeat';
  const classes = ['loading', 'buttons'];
  const singleIconClass = 'class';
  button.buttonIcon = icon;
  button.iconClasses = classes;
  button.addIconClass(singleIconClass);
  const iconElement = searchElement('button', fixture).getElementsByTagName('span');
  const iconClasses = iconElement[0].classList;
  const isExistIconClasses = iconClasses.contains(classes[0]) && iconClasses.contains(classes[1]) &&
      iconClasses.contains(singleIconClass);

  assert.equal(iconElement.length, 1, 'Should span with icon class to button: ');
  assert.ok(iconClasses.contains(`glyphicon-${icon}`), 'Should span special icon class');

  assert.true(isExistIconClasses, 'Should add 3 custom icon classes to span');
});
