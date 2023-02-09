import { ImageSlider } from '@/components/ImageSlider/ImageSlider';
import { OptionForm } from '@/components/OptionForm/OptionForm';
import { carList } from '@/constants/carList';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';

export class Experiencing extends Component {
  template(): string {
    return `
    <div id="imageSlider"></div>
    <div id="optionSelector"></div>
    `;
  }
  mounted(): void {
    const $imageSlider = qs('#imageSlider', this.target) as HTMLDivElement;
    const $optionSelector = qs(
      '#optionSelector',
      this.target
    ) as HTMLDivElement;
    new ImageSlider($imageSlider, { list: carList });
    new OptionForm($optionSelector);
  }
}
