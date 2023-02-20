import Component from '@/core/Component';
import styles from './SharingForm.module.scss';

export class SharingForm extends Component {
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
              <p class="${styles.left}">
                <label for="first_name">first name</label>
                <input type="text" name="first_name" placeholder="John" />
              </p>
              <p class="${styles['pull-right']}">
                <label for="last_name">last name</label>
                <input type="text" name="last_name" placeholder="Smith" />
              </p>
            </li>

            <li>
              <p>
                <label for="email"
                  >email <span class="${styles.req}">*</span></label
                >
                <input
                  type="email"
                  name="email"
                  placeholder="john.smith@gmail.com"
                />
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
}
