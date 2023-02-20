import { About } from '@/components/About/About';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class AboutUs extends Component {
  template(): string {
    return `<div id="about"></div>`;
  }
  mounted(): void {
    const $about = qs(`#about`, this.target);
    new About($about as HTMLDivElement);
  }
}
