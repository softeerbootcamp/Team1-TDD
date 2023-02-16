import { axiosInstance } from '@/apis';
import { ItestDrivingReq, ItestDrivingRes } from './interface';
import { IOptionState } from './OptionStore';
export async function totalDataHandler(state: IOptionState) {
  const reqBody: ItestDrivingReq = {
    carName: state.carModel,
    dateList: state.dates,
    optionList: state.options,
  };

  const response = await axiosInstance.post('/test-driving/posts', reqBody);
  const { latHi, latLo, lngHi, lngLo } = state.mapInfo;
  const filteredPost = response.data.filter((ele: ItestDrivingRes) => {
    const { latitude, longitude } = ele.location;
    return (
      latHi > +latitude &&
      latLo < +latitude &&
      lngHi > +longitude &&
      lngLo < +longitude
    );
  });
  return { ...state, filteredPost };
}
