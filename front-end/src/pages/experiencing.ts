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
import { seoulLocations } from '@/components/ExperienceMap/dummyData';

export class Experiencing extends Component {
  template(): string {
    return `
    <div id="ex-imageSlider"></div>
    <div class="${styles['option-calendar-container']}">
      <div id="ex-optionSelector"></div>
      <div id="ex-calendar"></div>
    </div>
    <div id="ex-map"></div>
    `;
  }
  mounted(): void {
    const $imageSlider = qs('#ex-imageSlider', this.target);
    const $optionSelector = qs('#ex-optionSelector', this.target);
    const $calendar = qs('#ex-calendar', this.target);
    const $map = qs('#ex-map', this.target);

    new ImageSlider($imageSlider as HTMLDivElement, {
      list: carList,
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
      locations: seoulLocations, //TODO:OptionStore.locations로 바꿔야됨
      store: OptionStore,
    });
  }

  onChangeDates(dates: string[]) {
    OptionStore.dispatch('CHANGE_DATES', { dates });
  }
  changePositionHandler(mapInfo: mapInfo) {
    OptionStore.dispatch('MOVE_MAP', { mapInfo });
  }
}
