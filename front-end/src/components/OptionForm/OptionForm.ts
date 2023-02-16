import Component from '@/core/Component';
import styles from './OptionForm.module.scss';
import { qsa } from '@/utils/querySelector';

interface ICategory {
  category: string;
  options: IOptions[];
}
interface IOptions {
  category: string;
  name: string;
}

export class OptionForm extends Component {
  setup(): void {
    this.state.optionTimer = null;
    this.state.openStateTimer = null;
    this.props.store.subscribe(this.render.bind(this), this.constructor.name);
  }
  template(): string {
    const data = this.props.store.getState().optionList;
    const openState = this.props.store.getState().openState;
    return `
    <div class="${styles['form-container']}">
      ${data
        .map((ele: any, idx: any) => this.categoryTemplate(ele, openState[idx]))
        .join('')}
    </div>
    `;
  }

  categoryTemplate({ category, options }: ICategory, isOpen: boolean) {
    const openClass = isOpen ? styles.close : '';
    return `
    <div class="${styles.container}">
        <div class="${styles.container__title}">${category}</div>      
        <div class="${styles['filter-container']} ${openClass}">
            ${options.map((ele) => this.optionBtnTemplate(ele)).join('')}
        </div>
    </div>    
    `;
  }

  optionBtnTemplate({ category, name }: IOptions) {
    const isActive =
      this.props.store.getState().options.findIndex((ele: IOptions) => {
        return (
          ele.category.trim() === category.trim() &&
          ele.name.trim() === name.trim()
        );
      }) !== -1;
    const buttonClass = isActive ? styles['is-active'] : '';
    const buttonState = isActive ? 'active' : 'inactive';
    return `<button class="${styles['filter-button']} ${buttonClass}" data-state="${buttonState}">${name}</button>`;
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['filter-button']}`, ({ target }) => {
      const $button = target as HTMLButtonElement;
      const buttonState = $button.getAttribute('data-state');
      if (buttonState === 'inactive') this.activeBtn($button);
      if (buttonState === 'active') this.inactiveBtn($button);
    });

    this.addEvent('click', `.${styles.container__title}`, ({ target }) => {
      const $target = target as HTMLDivElement;
      $target.nextElementSibling?.classList.toggle(styles.close);
      this.dispatchOpenState();
    });
  }

  activeBtn($button: HTMLButtonElement) {
    $button.classList.add(styles['is-active']);
    $button.setAttribute('data-state', 'active');
    this.dispatchOption();
  }

  inactiveBtn($button: HTMLButtonElement) {
    $button.classList.remove(styles['is-active']);
    $button.setAttribute('data-state', 'inactive');
    this.dispatchOption();
  }
  getAllCategoryState() {
    const $categories = qsa(`.${styles.container__title}`, this.target);
    const a = Array.from($categories).map(($category) =>
      $category.nextElementSibling?.classList.contains(styles.close)
    );
    return a;
  }
  getActiveBtnProperty() {
    const $activeButtons = this.getAllActiveBtn();
    return $activeButtons.map(($button) => {
      const $category = $button.parentElement
        ?.previousElementSibling as HTMLDivElement;
      return { name: $button.innerText, category: $category.innerText };
    });
  }

  getAllActiveBtn(): HTMLButtonElement[] {
    const $buttons = qsa(`.${styles['filter-button']}`, this.target);
    const $activeButtons = Array.from($buttons).filter(($button) => {
      const buttonState = $button.getAttribute('data-state');
      return buttonState === 'active';
    });
    return $activeButtons as HTMLButtonElement[];
  }

  dispatchOpenState() {
    if (this.state.openStateTimer) {
      clearTimeout(this.state.openStateTimer);
    }
    this.state.openStateTimer = setTimeout(() => {
      this.props.store.dispatch('UPDATE_OPEN_STATE', {
        openState: this.getAllCategoryState(),
      });
    }, 500);
  }

  dispatchOption() {
    if (this.state.optionTimer) {
      clearTimeout(this.state.optionTimer);
    }
    this.state.optionTimer = setTimeout(() => {
      this.props.store.dispatch('UPDATE_ACTIVE_CAR_OPTION', {
        options: this.getActiveBtnProperty(),
      });
    }, 400);
  }
}
