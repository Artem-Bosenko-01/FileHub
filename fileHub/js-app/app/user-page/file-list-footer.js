import {Component} from '../components/component.js';

/**
 * Footer for user main page.
 */
export class FileListFooter extends Component {
  /** @inheritDoc */
  get _markup() {
    return `<footer class="footer">
        <ul class="social-icons">
            <li>
                <a title="linkedin" class="icon" href="https://www.linkedin.com/company/teamdev-ltd-/mycompany/"
                   target="_blank">
                    <img src="./images/icon-linkedin.png" alt="linkedin">
                </a>
            </li>
            <li>
                <a title="facebook" class="icon" href="https://www.facebook.com/TeamDev" target="_blank">
                    <img src="./images/icon-facebook.png" alt="facebook">
                </a>
            </li>
            <li>
                <a title="instagram" class="icon" href="https://www.instagram.com/teamdev_ltd/?hl=ru"
                   target="_blank">
                    <img src="./images/icon-instagram.png" alt="instagram">
                </a>
            </li>
        </ul>
        <p class="copyright">Copyright &copy; 2021 <a title="TeamDev" class="highlight"
                                                     href="https://www.teamdev.com/"
                                                     target="_blank">TeamDev</a>. All
            rights reserved.</p>
    </footer>`;
  }
}
