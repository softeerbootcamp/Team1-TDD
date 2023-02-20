import { axiosInstance } from '@/apis';
import { carList } from '@/constants/carList';
import Component from '@/core/Component';
import { RegisterStore } from '@/store/RegisterStore/RegisterStore';
import { qs, qsa } from '@/utils/querySelector';
import { ImageSlider } from '../ImageSlider/ImageSlider';
import { OptionForm } from '../OptionForm/OptionForm';
import styles from './RegisterCar.module.scss';

export class RegisterCar extends Component {
  setup(): void {}

  setEvent(): void {
    this.addEvent('click', `.${styles['confirm']}`, (_) => {
      const options = Array.from(
        qsa(`[data-state]`, this.target) as NodeListOf<HTMLButtonElement>
      ).filter(
        (option: HTMLButtonElement) => option.dataset.state === 'active'
      );
      const selectedOptions = options.map((ele) => ele.innerText);
      this.register(selectedOptions);
      window.location.href = '/mypage';
    });
    this.addEvent('click', `.${styles['cancel']}`, (_) => {
      window.location.href = '/mypage';
    });
  }

  template(): string {
    return `
      <div id="imageSlider"></div>
      <div id="optionSelector"></div>
      <div class="${styles['button-wrapper']}">
        <div class="${styles['confirm']}">확인</div>
        <div class="${styles['cancel']}">취소</div>
      </div>
      `;
  }

  mounted(): void {
    const $imageSlider = qs('#imageSlider', this.target);
    const $optionSelector = qs('#optionSelector', this.target);
    const list = carList.filter((ele) => !!ele.name);

    new ImageSlider($imageSlider as HTMLDivElement, {
      list,
      store: RegisterStore,
    });
    new OptionForm($optionSelector as HTMLDivElement, {
      store: RegisterStore,
    });
  }

  register(selectedOptions: string[]) {
    const body = {
      carName: qs(`.${styles['title']}`, this.target),
      optionDtoList: selectedOptions,
    };
    //api가 나오면 바로 연동
    axiosInstance.post('/mycar', body);
  }
}
