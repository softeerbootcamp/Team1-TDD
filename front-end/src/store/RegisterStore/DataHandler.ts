import { axiosInstance } from '@/apis';
import { IRegisterCarReq } from './interface';
import { IRegisterState } from './RegisterStore';
export async function dataHandler(state: IRegisterState) {
  const reqBody: IRegisterCarReq = {
    carName: state.carModel,
    dateList: [],
    optionList: state.options.map((ele) => ele.name),
  };
  //api 받아오면 수정
  await axiosInstance.post('/test-driving/posts', reqBody);

  return { ...state };
}
