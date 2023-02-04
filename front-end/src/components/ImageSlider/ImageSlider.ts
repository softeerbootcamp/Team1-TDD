import Component from '@/core/Component';
import { qs, qsa } from '@/utils/querySelector';
import styles from './ImageSlider.module.scss';
import { ICar } from '@/constants/carList';
import { leftBtn, rightBtn } from './icon';

export class ImageSlider extends Component {
  setup(): void {
    this.state.imgIdx = 0;
  }

  template(): string {
    return `
    <div class="${styles.wrapper}">
      <div class="${styles['slider-container']}">
        <button id="left-btn">${leftBtn}</button>
        <div class="${styles.slider}">
          ${this.props.list
            .map(
              (car: ICar) =>
                `<img style="left: 100%" src="./src/assets/${car.fileName}" />`
            )
            .join('')}
        </div>
        <button id="right-btn">${rightBtn}</button>
      </div>
      <div class="${styles.title} ${styles.fade}">
        ${this.props.list[0].title}
      </div>
    </div>
    `;
  }

  mounted(): void {
    this.state.$images = Array.from(
      qsa('img', this.target)
    ) as HTMLImageElement[];

    this.sliderSizeInit();
    this.imageInit();
    this.toggleLeftBtn();
  }

  setEvent(): void {
    this.addEvent('click', '#left-btn', this.movePrev.bind(this));
    this.addEvent('click', '#right-btn', this.moveNext.bind(this));
  }

  movePrev() {
    if (this.isStartIdx()) return;

    if (this.isEndIdx()) this.toggleRightBtn();
    this.state.$images[this.state.imgIdx].style.left = '100%';

    this.state.imgIdx -= 1;
    this.state.$images[this.state.imgIdx].style.left = '0';
    this.updateTitle();

    if (this.isStartIdx()) this.toggleLeftBtn();
  }

  moveNext() {
    if (this.isEndIdx()) return;

    if (this.isStartIdx()) this.toggleLeftBtn();
    this.state.$images[this.state.imgIdx].style.left = '-100%';
    this.state.imgIdx += 1;
    this.state.$images[this.state.imgIdx].style.left = '0';
    this.updateTitle();

    if (this.isEndIdx()) this.toggleRightBtn();
  }

  sliderSizeInit() {
    const $slider = qs(`.${styles.slider}`, this.target) as HTMLDivElement;
    if (this.props.width) {
      $slider.style.width = this.props.width;
      $slider.style.height = this.props.height;
      return;
    }

    const img = new Image();
    img.src = this.state.$images[0].src;
    img.onload = function () {
      $slider.style.width = img.width + 'px';
      $slider.style.height = img.height + 'px';
    };
  }

  imageInit() {
    this.imageSizeInit();
    this.state.$images[0].style.left = '0';
  }

  imageSizeInit() {
    const $images = qsa('img', this.target);
    const imageArray = Array.from($images) as HTMLElement[];
    imageArray.forEach((img) => {
      img.style.width = this.props.sizeX + 'px';
      img.style.height = this.props.sizeY + 'px';
    });
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

  updateTitle() {
    const $title = qs(`.${styles.title}`) as HTMLDivElement;
    $title.classList.remove(styles.fade);
    setTimeout(() => {
      requestAnimationFrame(() => {
        $title.innerHTML = this.props.list[this.state.imgIdx].title;
        $title.classList.add(styles.fade);
      });
    }, 300);
  }
  getIdx() {
    return this.state.imgIdx;
  }
}
