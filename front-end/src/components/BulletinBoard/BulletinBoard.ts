import Component from '@/core/Component';
import styles from './BulletinBoard.module.scss';
import { ItestDrivingRes } from '@/store/OptionStore/interface';
import { markerController } from '@/store/MarkerController';
interface list {
  title: string;
  options: string;
  dates: string;
  carName: string;
  postId: number;
  location: any;
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
      return { title, options, dates, postId, carName, location: ele.location };
    });
    if (filteredData.length === 0) return '<h4>검색 결과가 없습니다.</h4>';
    return filteredData.map((data: any) => this.listTemplate(data)).join('');
  }

  listTemplate(data: list) {
    const { options, dates, carName, location } = data;
    return `
    <div data-location=${JSON.stringify(location)} class="${
      styles.wrapper
    } id="bulletinList">
      <div class="${styles.left}">
        <div class="${styles.title}">${carName || '제목 없음'}</div>
        <div class="${styles.contents}">${options}</div>
        <div class="${styles.info}">${dates}</div>
      </div>
    </div>
    `;
  }
  setEvent(): void {
    this.addEvent('click', `.${styles.wrapper}`, (e) => {
      if (!(e.target instanceof Element)) return;
      const $wrapper = e.target.closest(`.${styles.wrapper}`) as HTMLDivElement;
      const location = JSON.parse($wrapper.dataset.location as string);
      markerController.excuteListener(location);
    });
  }
}
