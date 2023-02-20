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
  const response = await axiosInstance.post('/test-driving/posts', reqBody);
  console.log(response.data);
  //return 할 때 data 배열로 넘겨줘야 함. option store의 datahander 참고
  return { ...state };
}
