import { qsa } from '../utils/querySelector';
export default class Component {
  target: HTMLElement;
  props: object;
  state: object = {};

  constructor(target: HTMLElement, props: object = {}) {
    this.target = target;
    this.props = props;
    this.setup();
    this.setEvent();
    this.render();
  }
  setup() {}
  mounted() {}
  template() {
    return '';
  }
  render() {
    this.target.innerHTML = this.template();
    this.mounted();
  }
  setEvent() {}
  setState(newState: object) {
    this.state = { ...this.state, ...newState };
    this.render();
  }
  addEvent(eventType: string, selector: string, callback: (e: Event) => void) {
    const children = [...Array.from(qsa(selector, this.target))];

    const isTarget = (target: HTMLElement) =>
      children.includes(target) || target.closest(selector);

    this.target.addEventListener(eventType, (event: Event) => {
      const eventTarget = event.target as HTMLElement;
      if (!isTarget(eventTarget)) return false;
      callback(event);
      return;
    });
  }
}
