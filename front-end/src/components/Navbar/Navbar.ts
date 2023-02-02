import Component from '@/core/Component';
import { Router } from '@/core/Router';
import { qs } from '@/utils/querySelector';
import './Navbar.scss';
export class Navbar extends Component {
  setup(): void {
    const $main = qs('main') as HTMLElement;
    this.state = {
      routes: [
        { path: /^\/$/, element: () => console.log('main') },
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
      <nav class="navbar">
        <a href="/" class="logo">T D D</a>
        <ul>
          <li><a href="/aboutus">ABOUT US</a></li>
          <li><a href="/share">공유하기</a></li>
          <li><a href="/experience">경험하기</a></li>
          <button class="login-button">login</button>
          <button class="logout-button hidden">logout</button>
        </ul>
      </nav>
    `;
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
}
