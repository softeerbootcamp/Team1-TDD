import { axiosInstance } from '@/apis';
import { mapInfo } from '@/components/ExperienceMap/interface';
import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
import { totalDataHandler } from './DataHandler';
import { ItestDrivingRes } from './interface';
export interface IOptionState {
  carModel: string;
  rideTogether: boolean;
  options: IOption[];
  openState: boolean[];
  dates: string[];
  mapInfo: mapInfo;
  optionList: any;
  filteredPost: ItestDrivingRes[];
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
  openState: [],
  dates: [],
  mapInfo: null,
  optionList: [],
  filteredPost: [],
};

const reducer = async (
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
      const updatedOptionState: IOptionState = {
        ...state,
        carModel: payload.name,
        optionList,
        options: result,
      };
      const updatedPostState = await totalDataHandler(updatedOptionState);
      console.log(updatedPostState);
      return { ...state, carModel: payload.name, optionList, options: result };

    case 'UPDATE_OPEN_STATE':
      return { ...state, openState: payload.openState };

    case 'CHANGE_DATES':
      return { ...state, dates: payload.dates };

    case 'MOVE_MAP':
      const { mapInfo } = payload;
      return { ...state, mapInfo };

    default:
      return { ...state };
  }
};

export const OptionStore = new Store(initState, reducer);
