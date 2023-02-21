import { getPosts, patchAppoinment } from '@/apis/detailPage';
import { routeGaurd } from '@/apis/login';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
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
  setup(): void {
    this.state.login = false;
    routeGaurd(
      () => {
        this.setState({ login: true });
      },
      () => {
        location.replace('/');
      }
    );
  }
  async render() {
    const postId = location.pathname.split('/').at(-1)!;
    const res = await getPosts(+postId);
    this.setState({ res: res.data });
    this.target.innerHTML = this.template();
    this.mounted();
  }

  setState(newState: object) {
    this.state = { ...this.state, ...newState };
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['confirm']}`, (_) => {
      const temp = this.findAppointmentId(this.state.res.appointments);
      patchAppoinment(temp[0].id)
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
      window.location.href = '/mypage';
    });
    this.addEvent('click', `.${styles['cancel']}`, (_) => {
      window.location.href = '/';
    });
    this.addEvent('change', `#dates`, () => {
      const dates = qs('#dates') as HTMLSelectElement;
      this.state.selectedDate = dates.options[dates.selectedIndex].value;
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
            <div class="${styles['left']}">가능날짜</div>
            <div class="${styles['right']}">${this.createDatesButton(
      appointments
    )}</div>
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

  mounted(): void {
    const dates = qs('#dates') as HTMLSelectElement;
    this.state.selectedDate = dates.options[dates.selectedIndex].value;
  }

  optionCreator(option: string): string {
    const literal = `
      <div class="${styles['opt']}">${option}</div>
    `;
    return literal;
  }

  findAppointmentId(appointments: IAppointment[]) {
    return appointments.filter((ele) => ele.date === this.state.selectedDate);
  }

  createDatesButton(appointments: IAppointment[]) {
    const wrapper = `
    <select id="dates" class="${styles['dates']}">
      ${appointments
        .map((ele, idx) =>
          idx === 0
            ? `<option value="${ele.date}" selected>${ele.date}</option>`
            : `<option value="${ele.date}">${ele.date}</option>`
        )
        .join('')}
    </select>`;

    return wrapper;
  }
}
