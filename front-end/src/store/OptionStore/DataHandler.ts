import { axiosInstance } from '@/apis';
import { ItestDrivingReq, ItestDrivingRes } from './interface';
import { IOptionState } from './OptionStore';
export async function totalDataHandler(state: IOptionState) {
  const reqBody: ItestDrivingReq = {
    carName: state.carModel,
    dateList: state.dates,
    optionList: state.options,
  };
  const response: ItestDrivingRes[] = await axiosInstance.post(
    '/test-driving/posts',
    reqBody
  );
  const { latHi, latLo, lngHi, lngLo } = state.mapInfo;
  const filteredPost = response.filter((ele) => {
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

const dummyReq = {
  carName: 'sonata',
  dateList: [],
  optionList: [],
};

const dummyReturn = [
  {
    appointments: [
      {
        date: 'string',
        id: 0,
        status: 'string',
      },
    ],
    location: {
      latitude: 'string',
      longitude: 'string',
    },
    options: [
      {
        category: 'string',
        name: 'string',
      },
    ],
    post: {
      carName: 'string',
      requirement: 'string',
      rideOption: 'string',
    },
  },
];
