export interface ItestDrivingReq {
  carName: string;
  dateList: string[];
  optionList: string[];
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
interface Ilocation {
  latitude: string;
  longitude: string;
}
interface Ipost {
  id: number;
  carName: string;
  requirement: string;
  rideOption: string;
}
