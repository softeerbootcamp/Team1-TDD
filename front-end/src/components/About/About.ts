import Component from '@/core/Component';
import { literal } from './template';

export class About extends Component {
  template(): string {
    return literal();
  }

  mounted(): void {}
}
