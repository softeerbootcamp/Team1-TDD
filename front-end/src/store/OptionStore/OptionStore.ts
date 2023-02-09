import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
interface IOptionState {
  carModal: string | null;
  rideTogether: boolean;
  options: IOption[];
}
interface IOption {
  name: string;
  category: string;
}
interface DynamicObject {
  [property: string]: any;
}
const initState = {
  carModel: carList[0].title,
  rideTogether: false,
  options: [],
};

const reducer = (
  state: IOptionState,
  actionKey: string,
  payload: DynamicObject = {}
) => {
  switch (actionKey) {
    case 'OPTION_INIT':
      return initState;

    case 'ADD_CAR_OPTION':
      const newOptions = [...state.options, payload.option];
      return { state, options: newOptions };

    case 'DELETE_CAR_OPTION':
      const filteredOptions = state.options.filter(
        (ele) => JSON.stringify(ele) !== JSON.stringify(payload.option)
      );
      return { ...state, options: filteredOptions };

    case 'SELECT_CAR_MODEL':
      return { ...state, carModel: payload.name };

    default:
      return { ...state };
  }
};

export const OptionStore = new Store(initState, reducer);
