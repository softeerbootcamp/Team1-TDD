import { DetailPage } from '@/components/DetailPage/DetailPage';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class Details extends Component {
  template(): string {
    return '<div id="detail-page"></div>';
  }
  mounted(): void {
    const $details = qs('#detail-page', this.target);
    new DetailPage($details as HTMLDivElement);
  }
}
