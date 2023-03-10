import { Calendar } from '@/components/Calendar/Calendar';
import { ExperienceMap } from '@/components/ExperienceMap/ExperienceMap';
import { ImageSlider } from '@/components/ImageSlider/ImageSlider';
import { OptionForm } from '@/components/OptionForm/OptionForm';
import { carList } from '@/constants/carList';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import styles from '@/styles/experiencing.module.scss';
import { OptionStore } from '@/store/OptionStore/OptionStore';
import { mapInfo } from '@/components/ExperienceMap/interface';
import { BulletinBoard } from '@/components/BulletinBoard/BulletinBoard';

export class Experiencing extends Component {
  template(): string {
    return `
    <div class="${styles.container}">
      <div class="${styles.left}">
        <div class="${styles.buttons}">
          <div class="${styles.dropdown}">
            <button class="${styles.dropbtn}">차종</button>
            <div id="ex-imageSlider" class="${styles['dropdown-content']} ${styles.backdrop}"></div>
          </div>
          <div class="${styles.dropdown}">
            <button class="${styles.dropbtn}">옵션</button>
            <div id="ex-optionSelector" class="${styles['dropdown-content']}"></div>
          </div>
          <div class="${styles.dropdown}">
            <button class="${styles.dropbtn}">날짜</button>
            <div id="ex-calendar" class="${styles['dropdown-content']}"></div>
          </div>
        </div>
        <div class="${styles['board-cover']}">
          <div id="bulletin-board" class="${styles.board}"></div>
        </div>
      </div>
      <div id="ex-map" class="${styles.map}"></div>
      </div>
    `;
  }
  mounted(): void {
    const $imageSlider = qs('#ex-imageSlider', this.target);
    const $optionSelector = qs('#ex-optionSelector', this.target);
    const $calendar = qs('#ex-calendar', this.target);
    const $map = qs('#ex-map', this.target);
    const $bulletinBoard = qs('#bulletin-board', this.target);
    const list = carList.filter((ele) => !!ele.name);

    new ImageSlider($imageSlider as HTMLDivElement, {
      list,
      store: OptionStore,
    });
    new OptionForm($optionSelector as HTMLDivElement, {
      store: OptionStore,
    });
    new Calendar($calendar as HTMLDivElement, {
      dates: OptionStore.getState().dates,
      store: OptionStore,
      onChangeDates: this.onChangeDates,
    });
    new ExperienceMap($map as HTMLDivElement, {
      changePositionHandler: this.changePositionHandler,
      store: OptionStore,
    });
    new BulletinBoard($bulletinBoard as HTMLDivElement, { store: OptionStore });
  }

  onChangeDates(dates: string[]) {
    OptionStore.dispatch('CHANGE_DATES', { dates });
  }
  changePositionHandler(mapInfo: mapInfo) {
    OptionStore.dispatch('MOVE_MAP', { mapInfo });
  }
}
