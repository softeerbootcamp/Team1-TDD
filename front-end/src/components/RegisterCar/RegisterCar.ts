import { axiosInstance } from '@/apis';
import { carList } from '@/constants/carList';
import Component from '@/core/Component';
import { RegisterStore } from '@/store/RegisterStore/RegisterStore';
import { qs, qsa } from '@/utils/querySelector';
import { ImageSlider } from '../ImageSlider/ImageSlider';
import { OptionForm } from '../OptionForm/OptionForm';
import { routeGaurd } from '@/apis/login';
import styles from './RegisterCar.module.scss';
import { goto } from '@/utils/navigatator';
import { showNotification } from '@/utils/notification';

interface ICar {
  fileName: string;
  title: string;
  name: string;
}

export class RegisterCar extends Component {
  setup(): void {
    this.state.login = false;
    this.state.carList = carList.filter((ele) => !!ele.name);
    routeGaurd(
      () => {
        this.setState({ login: true });
      },
      () => {
        goto('/');
      }
    );
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['confirm']}`, () => {
      const options = Array.from(
        qsa(`[data-state]`, this.target) as NodeListOf<HTMLButtonElement>
      ).filter(
        (option: HTMLButtonElement) => option.dataset.state === 'active'
      );

      const selectedOptions = options.map((ele) => ({
        name: ele.innerText,
        category: ele.parentElement?.previousElementSibling?.innerHTML,
      }));
      this.register(selectedOptions);
    });
    this.addEvent('click', `.${styles['cancel']}`, () => {
      showNotification('등록을 취소합니다.');
      goto('/mypage');
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

  findImage(slideWrapper: Element[]) {
    for (const i of slideWrapper) {
      const temp = i as HTMLImageElement;
      if (temp.style.left === '0px') {
        return temp.src;
      }
    }
    return null;
  }

  parseCarName(carUrl: string[]) {
    const carFileName = carUrl[carUrl.length - 1].split('.');
    const fileName = carFileName[0];
    if (fileName === 'palisade') return 'palisade';
    if (fileName === 'santafe') return 'santafe';
    if (fileName === 'tuscan') return 'tucson';
    return fileName.substring(4);
  }

  register(selectedOptions: object[]) {
    const accessToken = localStorage.getItem('accessToken');
    const img = Array.from(qsa('img', this.target));
    const carUrl = this.findImage(img)!.split('/');
    const carUrlName = this.parseCarName(carUrl);
    const f = this.state.carList.filter((ele: ICar) =>
      ele.fileName.includes(carUrlName)
    );
    const carName = f[0].name;

    const body = {
      carName,
      optionDtoList: selectedOptions,
    };
    axiosInstance
      .post('/mycars', body, { headers: { Authorization: accessToken } })
      .then(() => {
        showNotification(`${carName} 차량이 등록되었습니다.`);
        goto('/mypage');
      })
      .catch(() => {
        showNotification(`${carName}등록에 실패했습니다.`);
      });
  }
}
