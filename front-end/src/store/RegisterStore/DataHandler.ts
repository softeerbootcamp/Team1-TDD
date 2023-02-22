import { IRegisterState } from './RegisterStore';
export function dataHandler(state: IRegisterState) {
  return { ...state };
}
