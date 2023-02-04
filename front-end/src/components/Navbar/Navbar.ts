import Component from '@/core/Component';
import { Router } from '@/core/Router';
import { qs } from '@/utils/querySelector';
import styles from './Navbar.module.scss';

export class Navbar extends Component {
  setup(): void {
    const $main = qs('main') as HTMLElement;
    this.state = {
      routes: [
        {
          path: /^\/$/,
          element: () => console.log('main'),
        },
        { path: /^\/aboutus$/, element: () => console.log('about us') },
        { path: /^\/share$/, element: () => console.log('share page') },
        {
          path: /^\/experience$/,
          element: () => console.log('experience page'),
        },
        {
          path: /^\/post\/[\w]+$/,
          element: () => console.log('query example'),
        },
      ],
    };
  }
  template(): string {
    return `
    <div class="${styles['progress-container']}">
      <div class="${styles['progress-bar']}"></div>
    </div>
    <nav class="${styles.navbar}">
      <a href="/" class="${styles.logo}">T D D</a>
      <ul>
        <li><a href="/aboutus">ABOUT US</a></li>
        <li><a href="/share">공유하기</a></li>
        <li><a href="/experience">경험하기</a></li>
        <button class="${styles['login-button']}">login</button>
        <button class="${styles['logout-button']} ${styles.hidden}">logout</button>
      </ul>
    </nav>
    `;
  }
  mounted(): void {
    this.updateProgress();
    window.addEventListener('scroll', this.updateProgress);
  }

  setEvent(): void {
    const router = new Router(this.state.routes);
    const BASE_URL = 'http://localhost:5173';
    this.addEvent('click', 'a', (e: Event) => {
      e.preventDefault();
      const target = e.target as HTMLAnchorElement;
      const targetURL = target.href.replace(BASE_URL, '');
      router.navigate(targetURL);
    });
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
}
