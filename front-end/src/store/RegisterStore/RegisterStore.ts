import { axiosInstance } from '@/apis';
import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
import { dataHandler } from './DataHandler';
export interface IRegisterState {
  carModel: string;
  options: IOption[];
  dates: string[];
  openState: boolean[];
  optionList: any;
}
interface IOption {
  name: string;
  category: string;
}
interface DynamicObject {
  [property: string]: any;
}
const initState = {
  carModel: carList[0].name,
  rideTogether: false,
  options: [],
  openState: [],
  optionList: [],
};

const reducer = async (
  state: IRegisterState,
  actionKey: string,
  payload: DynamicObject = {}
) => {
  switch (actionKey) {
    case 'INIT':
      return { ...state, carModel: payload.carModel };
    case 'INIT_CAR':
      const response = await axiosInstance.get(`/options/${payload.name}`);
      return { ...state, optionList: response.data };

    case 'UPDATE_ACTIVE_CAR_OPTION':
      const newOptionState = { ...state, options: payload.options };
      return await dataHandler(newOptionState);

    case 'SELECT_CAR_MODEL':
      const res = await axiosInstance.get(`/options/${payload.name}`);
      const optionList = res.data;
      const result = [];
      for (const item of state.options) {
        const name = item.name;
        for (const obj of optionList) {
          for (const option of obj.options) {
            if (option.name === name) {
              result.push({ category: obj.category, name });
            }
          }
        }
      }
      const updatedOptionState: IRegisterState = {
        ...state,
        carModel: payload.name,
        optionList,
        options: result,
      };
      return await dataHandler(updatedOptionState);

    case 'UPDATE_OPEN_STATE':
      return { ...state, openState: payload.openState };

    default:
      return { ...state };
  }
};

export const RegisterStore = new Store(initState, reducer);
