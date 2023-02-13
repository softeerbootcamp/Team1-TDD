import Component from '@/core/Component';
import styles from './Calendar.module.scss';
import { qs, qsa } from '@/utils/querySelector';
import { months, daysOfWeek } from './constants';

let date = new Date();

export class Calendar extends Component {
  setup(): void {
    this.props.dates = [];
  }

  template(): string {
    return `
      <div id="${styles['calendar']}"></div>
      `;
  }

  setEvent(): void {
    this.addEvent('click', '#prev', this.movePrevMonth.bind(this));
    this.addEvent('click', '#next', this.moveNextMonth.bind(this));
    this.addEvent('change', '#year-select', this.selectYear.bind(this));
    this.addEvent('click', 'td', (e) => {
      const target = e.target as HTMLTableCellElement;
      const hasNumber = target.classList.contains(styles.hasNumber);
      if (!hasNumber) return;
      const isActive = target.classList.contains(`${styles['active']}`);
      if (!isActive) {
        target.classList.add(`${styles['active']}`);
        const dateString = this.getDateString(target);
        this.props.dates.push(dateString);
      } else {
        target.classList.remove(`${styles['active']}`);
        const dateString = this.getDateString(target);
        this.props.dates = this.props.dates.filter(
          (ele: string) => ele !== dateString
        );
      }
    });
  }
  getDateString(target: HTMLTableCellElement) {
    return `${date.getFullYear()}/${('0' + date.getMonth()).slice(-2)}/${(
      '0' + target.innerText
    ).slice(-2)}`;
  }
  markDates() {
    const yearMonth = `${date.getFullYear()}/${('0' + date.getMonth()).slice(
      -2
    )}`;

    const trimedDates: string[] = this.props.dates
      .filter((ele: string) => ele.includes(yearMonth))
      .map((day: string) => day.slice(-2));

    const tds = qsa('td', this.target) as NodeListOf<HTMLTableCellElement>;
    tds.forEach((td) => {
      if (trimedDates.includes(td.innerHTML)) {
        td.classList.add(`${styles['active']}`);
      }
    });
  }

  movePrevMonth() {
    date.setMonth(date.getMonth() - 1);
    this.setState(this.props.dates);
  }

  moveNextMonth() {
    date.setMonth(date.getMonth() + 1);
    this.setState(this.props.dates);
  }

  selectYear() {
    const year = qs('#year-select') as HTMLOptionElement;
    date.setFullYear(parseInt(year.value));
    this.setState(this.props.dates);
  }

  getDaysInMonth(year: number, month: number) {
    return new Date(year, month + 1, 0).getDate();
  }

  renderCalendar() {
    let year = date.getFullYear();
    let month = date.getMonth();

    let header = `
        <div id="${styles['month-header']}">
          <div id="prev" class="${styles.prev}"></div>
          <h2>${months[month]} ${year}</h2>
          <div id="next" class="${styles.next}"></div>
        </div>
        <table id="calendar">
          <thead>
            <tr>
              ${daysOfWeek.map((day) => `<th>${day}</th>`).join('')}
            </tr>
          </thead>
          <tbody>
            ${this.generateCalendar(date)}
          </tbody>
        </table>
        <div class=${styles['year-wrapper']}>
          <div id=${styles['year-selector']}>
            <select id="year-select">
              ${Array.from({ length: 10 }, (_, i) => year - 5 + i)
                .map(
                  (y) =>
                    `<option value="${y}" ${
                      y === year ? 'selected' : ''
                    }>${y}</option>`
                )
                .join('')}
            </select>
          </div>
        </div>
      `;
    qs(`#${styles['calendar']}`)!.innerHTML = header;
    this.markDates();
  }

  generateCalendar(date: Date) {
    let year = date.getFullYear();
    let month = date.getMonth();
    let firstDayOfMonth = new Date(year, month, 1).getDay();
    let daysInMonth = this.getDaysInMonth(year, month);
    let html = '';
    let week = 0;
    let day = 1;
    while (day <= daysInMonth) {
      html += '<tr>';
      for (let i = 0; i < 7; i++) {
        if (week === 0 && i < firstDayOfMonth) {
          html += '<td></td>';
        } else if (day <= daysInMonth) {
          html += `<td class="${styles.hasNumber}">${('0' + day).slice(
            -2
          )}</td>`;
          day++;
        } else {
          html += '<td></td>';
        }
      }
      html += '</tr>';
      week++;
    }
    return html;
  }

  mounted(): void {
    this.generateCalendar(date);
    this.renderCalendar();
  }
}
