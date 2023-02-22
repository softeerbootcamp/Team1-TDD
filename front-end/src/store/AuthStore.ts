import { Store } from '@/core/Store.js';
import { goto } from '@/utils/navigatator';
import axios from 'axios';
interface IAuthState {
  isLogin: boolean;
}
const initState = { isLogin: false };

const reducer = (state: IAuthState, actionKey: string) => {
  switch (actionKey) {
    case 'LOGIN':
      return { ...state, isLogin: true };
    case 'LOGOUT':
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      goto('/');
      return { ...state, isLogin: false };
    case 'CHECK':
      const accessToken = localStorage.getItem('accessToken');
      if (accessToken) {
        axios
          .get(`${process.env.VITE_PUBLIC_API_BASEURL}/test/auth`, {
            headers: { Authorization: accessToken },
          })
          .then(() => {
            return { ...state, isLogin: true };
          })
          .catch(() => {
            localStorage.removeItem('accessToken');
            return { ...state, isLogin: false };
          });
      } else {
        return { ...state, isLogin: false };
      }

    default:
      return { ...state };
  }
};

export const AuthStore = new Store(initState, reducer);
