import Component from '@/core/Component';
import { qs, qsa } from '@/utils/querySelector';
import styles from './ImageSlider.module.scss';
import { ICar } from '@/constants/carList';
import { leftBtn, rightBtn } from './icon';

export class ImageSlider extends Component {
  imgIdx: number = 0;

  template(): string {
    return `
    <div class="${styles['slider-container']}">
      <button id="left-btn">${leftBtn}</button>
      <div class="${styles.slider}">
      ${this.props.list
        .map(
          (car: ICar) =>
            `<img style="left: 100%;" src="./src/assets/${car.fileName}.png" />`
        )
        .join('')}
      </div>
      <button id="right-btn">${rightBtn}</button>
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
    this.state.$images[this.imgIdx].style.left = '100%';
    this.imgIdx -= 1;
    this.state.$images[this.imgIdx].style.left = '0';

    if (this.isStartIdx()) this.toggleLeftBtn();
  }

  moveNext() {
    if (this.isEndIdx()) return;

    if (this.isStartIdx()) this.toggleLeftBtn();
    this.state.$images[this.imgIdx].style.left = '-100%';
    this.imgIdx += 1;
    this.state.$images[this.imgIdx].style.left = '0';

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
    return this.imgIdx === 0;
  }

  isEndIdx() {
    return this.imgIdx === this.props.list.length - 1;
  }

  toggleLeftBtn() {
    const $leftBtn = qs('#left-btn', this.target) as HTMLButtonElement;
    $leftBtn.classList.toggle(styles.disabled);
  }

  toggleRightBtn() {
    const $rightBtn = qs('#right-btn', this.target) as HTMLButtonElement;
    $rightBtn.classList.toggle(styles.disabled);
  }
}
