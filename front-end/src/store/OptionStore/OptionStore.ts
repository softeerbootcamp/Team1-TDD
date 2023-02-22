import { axiosInstance } from '@/apis';
import { mapInfo } from '@/components/ExperienceMap/interface';
import { carList } from '@/constants/carList';
import { Store } from '@/core/Store.js';
import { getQueryStringParameter } from '@/utils/urlParser';
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
const carModel = getQueryStringParameter('car')?.toLocaleUpperCase();
const options = getQueryStringParameter('options');
const dates = getQueryStringParameter('dates');
const location = getQueryStringParameter('location');
const initMapInfo = {
  centerLat: 37.54033806744333,
  centerLng: 127.03921341756678,
  zoom: 15,
  latHi: 37.552247136775996,
  latLo: 37.5284270956494,
  lngHi: 127.0723011479745,
  lngLo: 127.00612568715907,
};

const initState = {
  carModel: carModel || carList[0].name,
  rideTogether: false,
  options: options ? JSON.parse(options) : [],
  openState: [],
  dates: dates ? JSON.parse(dates) : [],
  mapInfo: location ? JSON.parse(location) : initMapInfo,
  optionList: [],
  filteredPost: [],
};

const reducer = async (
  state: IOptionState,
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
      return await totalDataHandler(newOptionState);

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
      return await totalDataHandler(updatedOptionState);

    case 'UPDATE_OPEN_STATE':
      return { ...state, openState: payload.openState };

    case 'CHANGE_DATES':
      const newDateState = { ...state, dates: payload.dates };
      return await totalDataHandler(newDateState);

    case 'MOVE_MAP':
      const { mapInfo } = payload;
      const newMapState = { ...state, mapInfo };
      return await totalDataHandler(newMapState);

    default:
      return { ...state };
  }
};

export const OptionStore = new Store(initState, reducer);
