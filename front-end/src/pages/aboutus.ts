import { OptionForm } from '@/components/OptionForm/OptionForm';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class AboutUs extends Component {
  template(): string {
    return `<div id="option-form">about us page</div>`;
  }
  mounted(): void {
    const form = qs('#option-form', this.target) as HTMLDivElement;
    new OptionForm(form);
  }
}
