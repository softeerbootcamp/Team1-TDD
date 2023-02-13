import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
interface IOptionState {
  carModal: string | null;
  rideTogether: boolean;
  options: IOption[];
  openState: boolean[];
  dates: string[];
}
interface IOption {
  name: string;
  category: string;
  open: boolean;
}
interface DynamicObject {
  [property: string]: any;
}
const initState = {
  carModel: carList[0].title,
  rideTogether: false,
  options: [],
  openState: [],
  dates: [],
};

const reducer = (
  state: IOptionState,
  actionKey: string,
  payload: DynamicObject = {}
) => {
  switch (actionKey) {
    case 'OPTION_INIT':
      return initState;

    case 'UPDATE_ACTIVE_CAR_OPTION':
      return { ...state, options: payload.options };

    case 'SELECT_CAR_MODEL':
      return { ...state, carModel: payload.name };

    case 'UPDATE_OPEN_STATE':
      return { ...state, openState: payload.openState };

    case 'CHANGE_DATES':
      console.log(payload.dates);
      return { ...state, dates: payload.dates };

    default:
      return { ...state };
  }
};

export const OptionStore = new Store(initState, reducer);
