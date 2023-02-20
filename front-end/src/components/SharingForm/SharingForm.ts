import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import { Calendar } from '../Calendar/Calendar';
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
              <p class="${styles.left}">
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
              </p>
              <div id="calendar" class="${styles.calendar}"></div>
            </li>

            <li>
              <p>
                <label for="location"
                  >위치 고르기 <span class="${styles.req}">*</span></label
                >
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
    new Calendar($calendar as HTMLDivElement, {
      dates: this.state.dates,
      onChangeDates: () => {},
    });
  }
}
