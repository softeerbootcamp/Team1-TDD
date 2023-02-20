import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import { Calendar } from '../Calendar/Calendar';
import { SharingMap } from '../SharingMap/SharingMap';
import styles from './SharingForm.module.scss';

export class SharingForm extends Component {
  setup(): void {
    this.state.dates = [];
  }
  template(): string {
    return `
    <div class="${styles.container}">
      <div class="${styles.row} ${styles.header}">
        <h1>CONTACT US &nbsp;</h1>
        <h3>Fill out the form below to learn more!</h3>
      </div>
      <div class="${styles.row} ${styles.body}">
        <form action="#">
          <ul>
            <li>
              <p>
                <label for="carModel"
                >내 차 고르기 <span class="${styles.req}">*</span></label
                >
                <select id="Field106" name="Field106" tabindex="11">
                  <option value="First Choice">First Choice</option>
                  <option value="Second Choice">Second Choice</option>
                  <option value="Third Choice">Third Choice</option>
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
                <div id="sharing-overlay" class="${styles.hidden} ${styles.overlay}"></div>
                <div id="sharing-map" class="${styles.map} ${styles.hidden}"></div>
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
      onChangeDates: () => {},
    });
    new SharingMap($map as HTMLDivElement);
  }
  setEvent(): void {
    this.addEvent('click', '#map-button', this.openMap.bind(this));
    this.addEvent('click', '#sharing-overlay', ({ target }: Event) => {
      if (!(target instanceof HTMLDivElement)) return;
      if (target.closest(`.${styles.map}`)) return;
      this.closeMap();
    });
  }
  openMap(e: Event) {
    e.preventDefault();
    const $overlay = qs('#sharing-overlay', this.target);
    const $map = qs('#sharing-map', this.target);
    $overlay?.classList.toggle(styles.hidden);
    $map?.classList.toggle(styles.hidden);
  }
  closeMap() {
    const $overlay = qs('#sharing-overlay', this.target);
    const $map = qs('#sharing-map', this.target);
    $overlay?.classList.toggle(styles.hidden);
    $map?.classList.toggle(styles.hidden);
  }
}
