import Component from '@/core/Component';
import { sendAuthTestRequest } from './apis/login';
import { Footer } from './components/Footer/Footer';
import { Navbar } from './components/Navbar/Navbar';
import { Router } from './core/Router';
import { AuthStore } from './store/AuthStore';
import { attachRouterToAnchor } from './utils/navigatator';
import { qs } from './utils/querySelector';

export class App extends Component {
  setup(): void {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      sendAuthTestRequest()
        .then(() => {
          AuthStore.dispatch('LOGIN');
        })
        .catch(() => {
          localStorage.removeItem('accessToken');
        });
    }
  }
  template(): string {
    return `
    <header></header>
    <main></main>
    <footer></footer>
    `;
  }

  setEvent(): void {
    this.addEvent('click', 'a', attachRouterToAnchor);
  }
  mounted(): void {
    const $header = qs('header') as HTMLElement;
    const $main = qs('main') as HTMLElement;
    const $footer = qs('footer') as HTMLElement;
    new Navbar($header);
    new Router($main);
    new Footer($footer);
  }
}
