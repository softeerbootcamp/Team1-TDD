import Component from '@/core/Component';
import { Footer } from './components/Footer/Footer';
import { Navbar } from './components/Navbar/Navbar';
import { Router } from './core/Router';
import { attachRouterToAnchor } from './utils/navigatator';
import { qs } from './utils/querySelector';

export class App extends Component {
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
