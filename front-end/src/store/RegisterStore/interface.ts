export interface IRegisterCarRes {
  carName: string;
  options: IOption[];
}

export interface IRegisterCarReq {
  carName: string;
  dateList: string[];
  optionList: string[];
}

interface IOption {
  category: string;
  name: string;
}
