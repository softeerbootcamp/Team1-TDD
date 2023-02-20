import { getPosts } from '@/apis/detailPage';
import Component from '@/core/Component';
import styles from './DetailPage.module.scss';

interface IAppointment {
  date: string;
  id: number;
  status: string;
}

interface IOptions {
  name: string;
  category: string;
}

export class DetailPage extends Component {
  async render() {
    const res = await getPosts(1);
    this.setState({ res: res.data });
    this.target.innerHTML = this.template();
    this.mounted();
  }

  setState(newState: object) {
    this.state = { ...this.state, ...newState };
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['confirm']}`, (_) => {
      window.location.href = '/mypage';
    });
    this.addEvent('click', `.${styles['cancel']}`, (_) => {
      window.location.href = '/';
    });
  }

  template(): string {
    const { appointments, imageUrl, location, options, post } = this.state.res;
    return `
      <div class="${styles['container']}">
        <div class="${styles['infos']}">
          <div class="${styles['image-wrapper']}">
            <img class="${styles['image']}" src="${imageUrl}" />
          </div>
          <div class="${styles['text-wrapper']}">
            <div class="${styles['car-name']}">
              <div class="${styles['left']}">차종</div>
              <div class="${styles['right']}">${post.carName}</div>
            </div>
            <div class="${styles['options']}">
              <div class="${styles['left']}">옵션</div>
              <div class="${styles['right']}">${options
      .map((ele: IOptions) => this.optionCreator(ele.name))
      .join('')}
            </div>
          </div>
          <div class="${styles['date']}">
            <div class="${styles['left']}">시승날짜</div>
            <div class="${styles['right']}">2023-02-24</div>
          </div>
          <div class="${styles['location']}">
            <div class="${styles['left']}">시승위치</div>
            <div class="${styles['right']}">
              <a href="https://www.google.co.kr/maps?&z=18.5&q=${
                location.latitude
              },${location.longitude}&ll=${location.latitude},${
      location.longitude
    }z" target="_blank">위치</a>
            </div>
          </div>
          <div class="${styles['appointments']}">
            <div class="${styles['left']}">예약현황</div>
            <div class="${styles['right']}">${appointments
      .map((ele: IAppointment) => ele.date)
      .join(', ')}</div>
          </div>
        </div>
        </div>
        <div class="${styles['button-wrapper']}">
          <div class="${styles['confirm']}">확인</div>
          <div class="${styles['cancel']}">취소</div>
        </div>
      </div>
    `;
  }

  optionCreator(option: string): string {
    const literal = `
      <div class="${styles['opt']}">${option}</div>
    `;
    return literal;
  }
}
