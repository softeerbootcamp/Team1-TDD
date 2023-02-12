import Component from '@/core/Component';
import { AuthStore } from '@/store/AuthStore';

export class Sharing extends Component {
  setup(): void {
    const isLogin = AuthStore.getState().isLogin;
    if (!isLogin) {
      alert('로그인 후 이용하세요!');
      location.replace('/');
    }
  }
  template(): string {
    return `<div>Sharing page</div>`;
  }
}
