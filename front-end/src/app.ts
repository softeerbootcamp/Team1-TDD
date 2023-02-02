import Component from '@/core/Component';
import { Footer } from './components/Footer/Footer';
import { Navbar } from './components/Navbar/Navbar';
import { qs } from './utils/querySelector';

export class App extends Component {
  template(): string {
    return `
    <header></header>
    <main></main>
    <footer></footer>
    `;
  }
  mounted(): void {
    const $header = qs('header') as HTMLElement;
    const $footer = qs('footer') as HTMLElement;
    new Navbar($header);
    new Footer($footer);
  }
}
