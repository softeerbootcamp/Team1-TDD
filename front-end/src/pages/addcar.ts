import { RegisterCar } from '@/components/RegisterCar/RegisterCar';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class RegisterPage extends Component {
  template(): string {
    return '<div id="register-car"></div>';
  }
  mounted(): void {
    const $registerCar = qs('#register-car', this.target);
    new RegisterCar($registerCar as HTMLDivElement);
  }
}
