import Component from '@/core/Component';
import { qs, qsa } from '@/utils/querySelector';
import styles from './ImageSlider.module.scss';
import { ICar } from '@/constants/carList';
import { leftBtn, rightBtn } from './icon';
import { OptionStore } from '@/store/OptionStore/OptionStore';

export class ImageSlider extends Component {
  setup(): void {
    this.state.timer = null;
    OptionStore.subscribe(this.render.bind(this), this.constructor.name);
  }
  template(): string {
    const { props } = this;
    const { list } = props;
    const imgIdx = list.findIndex(
      (ele: any) => ele.title === OptionStore.getState().carModel
    );
    this.state.imgIdx = imgIdx;
    const position = list.map((_: ICar, idx: number) => {
      if (idx < imgIdx) return 'left: -100%';
      if (idx === imgIdx) return 'left: 0';
      if (idx > imgIdx) return 'left: 100%';
      return '';
    });
    const leftBtnClass = this.isStartIdx() ? styles.disabled : '';
    const rightBtnClass = this.isEndIdx() ? styles.disabled : '';
    return `
    <div class="${styles.wrapper}">
      <div class="${styles['slider-container']}">
        <button id="left-btn" class="${leftBtnClass}">${leftBtn}</button>
        <div class="${styles.slider}">
          ${list
            .map(
              (car: ICar, idx: number) =>
                `<img style="${position[idx]}" src="${process.env.VITE_IMAGE_URL}/${car.fileName}" />`
            )
            .join('')}
        </div>
        <button id="right-btn" class="${rightBtnClass}">${rightBtn}</button>
      </div>
      <div class="${styles.title} ${styles.fade}">
        ${list[imgIdx].title}
      </div>
    </div>
    `;
  }

  mounted(): void {
    this.state.$images = Array.from(
      qsa('img', this.target)
    ) as HTMLImageElement[];
  }

  setEvent(): void {
    this.addEvent('click', '#left-btn', this.movePrev.bind(this));
    this.addEvent('click', '#right-btn', this.moveNext.bind(this));
  }

  movePrev() {
    if (this.isStartIdx()) return;

    if (this.isEndIdx()) this.toggleRightBtn();

    this.getCurrnetImg().style.left = '100%';
    this.state.imgIdx -= 1;
    this.getCurrnetImg().style.left = '0';
    this.updateTitle();

    if (this.isStartIdx()) this.toggleLeftBtn();

    this.dispatchModel();
  }

  moveNext() {
    if (this.isEndIdx()) return;

    if (this.isStartIdx()) this.toggleLeftBtn();
    this.getCurrnetImg().style.left = '-100%';
    this.state.imgIdx += 1;
    this.getCurrnetImg().style.left = '0';
    this.updateTitle();

    if (this.isEndIdx()) this.toggleRightBtn();

    this.dispatchModel();
  }

  dispatchModel() {
    if (this.state.timer) {
      clearTimeout(this.state.timer);
    }
    this.state.timer = setTimeout(() => {
      OptionStore.dispatch('SELECT_CAR_MODEL', {
        name: this.props.list[this.state.imgIdx].title,
      });
    }, 1000);
  }

  isStartIdx() {
    return this.state.imgIdx === 0;
  }

  isEndIdx() {
    return this.state.imgIdx === this.props.list.length - 1;
  }

  toggleLeftBtn() {
    const $leftBtn = qs('#left-btn', this.target) as HTMLButtonElement;
    $leftBtn.classList.toggle(styles.disabled);
  }

  toggleRightBtn() {
    const $rightBtn = qs('#right-btn', this.target) as HTMLButtonElement;
    $rightBtn.classList.toggle(styles.disabled);
  }

  updateTitle(): void {
    const $title = qs(`.${styles.title}`) as HTMLDivElement;
    $title.classList.remove(styles.fade);
    setTimeout(() => {
      requestAnimationFrame(() => {
        $title.innerHTML = this.props.list[this.state.imgIdx].title;
        $title.classList.add(styles.fade);
      });
    }, 300);
  }

  getCurrnetImg(): HTMLImageElement {
    return this.state.$images[this.state.imgIdx];
  }

  getIdx(): number {
    return this.state.imgIdx;
  }
}
