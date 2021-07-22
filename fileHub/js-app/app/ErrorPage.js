import {Component} from './components/component.js';

export class ErrorPage extends Component {
  get _markup() {
    return `<div>
                <h1>NOT FOUND PAGE</h1>
                <div style="">
                    <a title="Link to log In page" href="#login">Link to log In page!</a>
                </div>
            </div>`;
  }
}
