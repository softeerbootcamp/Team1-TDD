import { Ilocation } from './OptionStore/interface';

class MarkerController {
  listener: any;
  setListener(func: Function) {
    this.listener = func;
  }
  excuteListener(location: Ilocation) {
    const { latitude, longitude } = location;
    this.listener(+latitude, +longitude);
  }
}
export const markerController = new MarkerController();
