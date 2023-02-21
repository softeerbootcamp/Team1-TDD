export interface ItestDrivingReq {
  carName: string;
  dateList: string[];
  optionList: string[];
  quadOne: Ilocation;
  quadThree: Ilocation;
}
export interface ItestDrivingRes {
  appointments: Iappointment[];
  location: Ilocation;
  options: Ioption[];
  post: Ipost;
}

interface Ioption {
  category: string;
  name: string;
}
interface Iappointment {
  date: string;
  id: number;
  status: string;
}
export interface Ilocation {
  latitude: string;
  longitude: string;
}
interface Ipost {
  id: number;
  carName: string;
  requirement: string;
  rideOption: string;
}
