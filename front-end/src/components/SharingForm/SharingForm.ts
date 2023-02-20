import { sendGetMyCarRequest } from '@/apis/sharing';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import { Calendar } from '../Calendar/Calendar';
import { SharingMap } from '../SharingMap/SharingMap';
import styles from './SharingForm.module.scss';

export class SharingForm extends Component {
  setup(): void {
    this.state.dates = [];
    this.state.location = null;
    this.state.myCars = null;
    sendGetMyCarRequest().then((res) => {
      this.setState({ myCars: res.data });
    });
  }
  template(): string {
    return `
    <div class="${styles.container}">
      <div class="${styles.row} ${styles.header}">
        <h1>CONTACT US &nbsp;</h1>
        <h3>Fill out the form below to learn more!</h3>
      </div>
      <div class="${styles.row} ${styles.body}">
        <form action="submit" id="sharing-form">
          <ul>
            <li>
              <p>
                <label for="carModel"
                >내 차 고르기 <span class="${styles.req}">*</span></label
                >
                <select id="select-car" name="Field106" tabindex="11">
                ${this.state.myCars
                  .map(
                    (ele: { mycarId: number; carName: string }) =>
                      `<option value="${ele.mycarId}">${ele.carName}</option>`
                  )
                  .join('')}
                </select>
              </p>
            </li>


            <li>
              <p>
                <label for="dates"
                  >날짜 고르기 <span class="${styles.req}">*</span></label
                >
                <div id="calendar" class="${styles.calendar}"></div>
              </p>
            </li>

            <li>
              <p>
                <label for="location"
                  >위치 고르기 <span class="${styles.req}">*</span></label
                >
                <button id="map-button"> open map</button>
                <div id="sharing-overlay" class="${styles.hidden} ${
      styles.overlay
    }"></div>
                <div id="sharing-map" class="${styles.map} ${
      styles.hidden
    }"></div>
              </p>
            </li>

            <li><div class="${styles.divider}"></div></li>
            <li>
              <label for="comments">comments</label>
              <textarea cols="46" rows="3" name="comments"></textarea>
            </li>

            <li>
              <input
                class="${styles.btn} ${styles['btn-submit']}"
                type="submit"
                value="Submit"
              />
              <small>or press <strong>enter</strong></small>
            </li>
          </ul>
        </form>
      </div>
    </div>

    `;
  }
  mounted(): void {
    const $calendar = qs('#calendar', this.target);
    const $map = qs('#sharing-map', this.target);
    new Calendar($calendar as HTMLDivElement, {
      dates: this.state.dates,
      onChangeDates: this.onChangeDates.bind(this),
    });
    new SharingMap($map as HTMLDivElement);
  }

  onChangeDates(dates: string[]) {
    this.state.dates = [...dates];
  }

  setEvent(): void {
    this.addEvent('click', '#map-button', this.openMap.bind(this));
    this.addEvent('click', '#sharing-overlay', ({ target }: Event) => {
      if (!(target instanceof HTMLDivElement)) return;
      if (target.closest(`.${styles.map}`)) return;
      this.closeMap();
    });
    this.addEvent('submit', '#sharing-form', (e) => {
      e.preventDefault();
      const $select = qs('#select-car', this.target) as HTMLSelectElement;
      const myCarId = $select.value;
      console.log(myCarId, this.state.dates);
    });
  }

  openMap(e: Event) {
    e.preventDefault();
    const $overlay = qs('#sharing-overlay', this.target);
    const $map = qs('#sharing-map', this.target);
    $overlay?.classList.toggle(styles.hidden);
    $map?.classList.toggle(styles.hidden);
    document.body.classList.add('block-scroll');
  }

  closeMap() {
    const $overlay = qs('#sharing-overlay', this.target);
    const $map = qs('#sharing-map', this.target);
    $overlay?.classList.toggle(styles.hidden);
    $map?.classList.toggle(styles.hidden);
    document.body.classList.remove('block-scroll');
  }
}
