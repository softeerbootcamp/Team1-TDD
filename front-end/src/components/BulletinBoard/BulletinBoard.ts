import Component from '@/core/Component';
import styles from './BulletinBoard.module.scss';
interface list {
  car: string;
  desc: string;
  options: string[];
}
const data: list = {
  car: 'sonata',
  desc: '아니한 그들에게 그들의 가장 없으면 군영과 모래뿐일 피다. 같이 모래뿐일 별과 내는 귀는 것이다.',
  options: [],
};
export class BulletinBoard extends Component {
  template(): string {
    return `
      ${this.listTemplate(data)}
    `;
  }
  listTemplate(data: list) {
    const { car, desc, options } = data;
    return `
    <div class="${styles.wrapper}">
      <div class="${styles.left}">
        <div class="${styles.title}">${car}</div>
        <div class="${styles.contents}">${desc}</div>
        <div class="${styles.info}">${options.join(' / ')}</div>
      </div>
      <div class="${styles.right}">
        <div class="${styles.circle}">
          0
          <div class="${styles['circle-text']}">답변</div>
        </div>
      </div>
    </div>
    `;
  }
}
