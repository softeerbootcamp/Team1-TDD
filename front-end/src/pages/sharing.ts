import { routeGaurd } from '@/apis/login';
import { SharingForm } from '@/components/SharingForm/SharingForm';
import Component from '@/core/Component';
import { goto } from '@/utils/navigatator';
import { qs } from '@/utils/querySelector';

export class Sharing extends Component {
  setup(): void {
    this.state.isLogin = false;
    routeGaurd(
      () => {
        this.setState({ isLogin: true });
      },
      () => {
        goto('/');
      }
    );
  }
  template(): string {
    return this.state.isLogin ? '<div id="sharing-form"></div>' : '';
  }
  mounted(): void {
    const $sharingForm = qs('#sharing-form', this.target);
    if ($sharingForm) new SharingForm($sharingForm as HTMLDivElement);
  }
}
