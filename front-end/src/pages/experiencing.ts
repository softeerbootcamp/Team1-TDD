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
    <div id="ex-imageSlider"></div>
    <div class="${styles['option-calendar-container']}">
      <div id="ex-optionSelector"></div>
      <div id="ex-calendar"></div>
    </div>
    <div id="ex-map"></div>
    <button id="copy-link" class="${styles['copy-link']} ${styles['copy-link-flip-down']}">이 검색결과를 공유하기
      <div class="${styles.front}">이 검색결과를 공유하기</div>
      <div class="${styles.back}">CLICK!</div>
    </button>
    <div id="bulletin-board"><div>
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
  setEvent(): void {
    this.addEvent('click', `.${styles.back}`, () => {
      console.log('a');
    });
  }
}
