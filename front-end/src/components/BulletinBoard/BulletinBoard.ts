import Component from '@/core/Component';
import styles from './BulletinBoard.module.scss';
import { ItestDrivingRes } from '@/store/OptionStore/interface';
interface list {
  title: string;
  options: string;
  dates: string;
  carName: string;
  postId: number;
}

export class BulletinBoard extends Component {
  setup(): void {
    this.props.store.subscribe(this.render.bind(this), this.constructor.name);
  }
  template(): string {
    const filteredPost = this.props.store.getState().filteredPost;
    const filteredData = filteredPost.map((ele: ItestDrivingRes) => {
      const title = ele.post.requirement;
      const options = ele.options.map(({ name }) => name).join(' / ');
      const dates = ele.appointments.map(({ date }) => date).join(' / ');
      const postId = ele.post.id;
      const carName = ele.post.carName;
      return { title, options, dates, postId, carName };
    });
    return filteredData.map((data: any) => this.listTemplate(data)).join('');
  }

  listTemplate(data: list) {
    const { options, dates, carName, postId } = data;
    return `
    <a data-link href="/details/${postId}" class="${styles.wrapper}">
      <div class="${styles.left}">
        <div class="${styles.title}">${carName || '제목 없음'}</div>
        <div class="${styles.contents}">${options}</div>
        <div class="${styles.info}">${dates}</div>
      </div>
    </a>
    `;
  }
}
