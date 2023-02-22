import Component from '@/core/Component';
import { AuthStore } from '@/store/AuthStore';
import { openLoginModal } from '@/utils/loginModal';
import { qs } from '@/utils/querySelector';
import styles from './Navbar.module.scss';
import { userIcon } from './icon';

export class Navbar extends Component {
  setup(): void {
    AuthStore.subscribe(this.render.bind(this), this.constructor.name);
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
            ? `
          <div class="${styles.dropdown}">
          ${userIcon}
            <div class="${styles.options}">
              <a data-link href="/mypage"><div>마이페이지</div></a>
              <div class="${styles['logout-button']}">Logout</div>
            </div>
          </div>
          `
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
    this.addEvent('click', `.${styles['logout-button']}`, this.onLogout);
    this.addEvent('click', '#user-icon', this.toggleDropDown);
    document.addEventListener('click', this.closeDropDown);
  }

  toggleDropDown({ target }: Event) {
    if (!(target instanceof SVGElement)) return;
    target.closest(`.${styles.dropdown}`)?.classList.toggle(styles.active);
  }

  closeDropDown(e: Event) {
    const dropdownMenu = qs(`.${styles.dropdown}`) as HTMLDivElement;
    if (!dropdownMenu) return;
    const target = e.target as Element;
    if (
      !target.closest('#user-icon') &&
      dropdownMenu.classList.contains(styles.active)
    ) {
      dropdownMenu.classList.remove(styles.active);
    }
  }

  onLogout() {
    AuthStore.dispatch('LOGOUT');
  }
}
