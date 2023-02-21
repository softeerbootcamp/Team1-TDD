import { axiosInstance } from '@/apis';
import { ItestDrivingReq, ItestDrivingRes } from './interface';
import { IOptionState } from './OptionStore';

export async function totalDataHandler(state: IOptionState) {
  const { mapInfo } = state;
  const quadThree = {
    latitude: mapInfo.latLo.toString(),
    longitude: mapInfo.lngLo.toString(),
  };
  const quadOne = {
    latitude: mapInfo.latHi.toString(),
    longitude: mapInfo.lngHi.toString(),
  };

  const reqBody: ItestDrivingReq = {
    carName: state.carModel,
    dateList: state.dates,
    optionList: state.options.map((ele) => ele.name),
    quadOne,
    quadThree,
  };

  const response = await axiosInstance.post('/test-driving/posts', reqBody);
  const filteredPost: ItestDrivingRes = response.data;
  return { ...state, filteredPost };
}
