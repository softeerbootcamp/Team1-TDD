import { MyPage } from '@/components/MyPage/MyPage';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class PersonalPage extends Component {
  template(): string {
    return '<div id="personal-page"></div>';
  }
  mounted(): void {
    const $personalPage = qs('#personal-page', this.target);
    new MyPage($personalPage as HTMLDivElement);
  }
}
