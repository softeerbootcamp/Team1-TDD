import { getPosts, patchAppoinment } from '@/apis/detailPage';
import { routeGaurd } from '@/apis/login';
import Component from '@/core/Component';
import { goto } from '@/utils/navigatator';
import { showNotification } from '@/utils/notification';
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
        const postId = location.pathname.split('/').at(-1)!;
        getPosts(+postId).then((res) => {
          this.setState({ res: res.data, login: true });
        });
      },
      () => {
        showNotification('로그인이 필요합니다.');
        history.go(-1);
      }
    );
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['confirm']}`, () => {
      const temp = this.findAppointmentId(this.state.res.appointments)[0];
      patchAppoinment(temp.id)
        .then(() => {
          goto('/mypage');
          showNotification('신청 되었습니다.');
        })
        .catch(() => {
          showNotification('신청에 실패했습니다.');
        });
    });
    this.addEvent('click', `.${styles['cancel']}`, () => {
      window.history.go(-1);
    });
    this.addEvent('change', `#dates`, () => {
      const dates = qs('#dates') as HTMLSelectElement;
      this.state.selectedDate = dates.options[dates.selectedIndex].value;
    });
  }

  template(): string {
    if (!this.state.login) return '';
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
    if (!this.state.login) return;
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
    return appointments.filter(({ date }) => date === this.state.selectedDate);
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
