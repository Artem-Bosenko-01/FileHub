import {Component} from './components/component.js';

export class ErrorPage extends Component {
  get markup() {
    return `<h1>NOT FOUND PAGE</h1>
            <a href="#">Link to log In page!</a>`;
  }
}
