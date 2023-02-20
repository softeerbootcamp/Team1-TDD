import { SharingForm } from '@/components/SharingForm/SharingForm';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class Sharing extends Component {
  setup(): void {}
  template(): string {
    return `<div id="sharing-form"></div>`;
  }
  mounted(): void {
    const $sharingForm = qs('#sharing-form', this.target);
    new SharingForm($sharingForm as HTMLDivElement);
  }
}
