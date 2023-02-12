import Component from '@/core/Component';
import { AuthStore } from '@/store/AuthStore';
import { openLoginModal } from '@/utils/loginModal';
import { qs } from '@/utils/querySelector';
import styles from './Navbar.module.scss';

export class Navbar extends Component {
  setup(): void {
    AuthStore.subscribe(this.render.bind(this));
  }
  template(): string {
    const isLogin = AuthStore.getState().isLogin;
    return `
    <div class="${styles['progress-container']}">
      <div class="${styles['progress-bar']}"></div>
    </div>
    <nav class="${styles.navbar}">
      <a data-link href="/" class="${styles.logo}">T D D</a>
      <ul>
        <li><a data-link href="/aboutus">ABOUT US</a></li>
        ${isLogin ? '<li><a data-link href="/sharing">공유하기</a></li>' : ''}
        <li><a data-link href="/experiencing">경험하기</a></li>
        ${
          isLogin
            ? `<button class="${styles['logout-button']}">logout</button>`
            : `<button class="${styles['login-button']}">login</button>`
        }
      </ul>
    </nav>
    `;
  }
  mounted(): void {
    this.updateProgress();
    window.addEventListener('scroll', this.updateProgress);
  }

  updateProgress() {
    const $progressBar = qs(`.${styles['progress-bar']}`) as HTMLDivElement;
    const { scrollY, innerHeight } = window;
    const { offsetHeight } = document.body;
    if (offsetHeight === innerHeight) {
      $progressBar.style.width = '0%';
      return;
    }
    const scrollPercentage = (scrollY / (offsetHeight - innerHeight)) * 100;
    $progressBar.style.width = `${scrollPercentage}%`;
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['login-button']}`, openLoginModal);
    this.addEvent('click', `.${styles['logout-button']}`, () => {
      AuthStore.dispatch('LOGOUT');
    });
  }
}
