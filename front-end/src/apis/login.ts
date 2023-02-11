import { axiosInstance } from '.';

export const sendLogInRequest = async (email: string, userPassword: string) => {
  return axiosInstance.post('/login', {
    email,
    userPassword,
  });
};

interface IRegisterPayload {
  email: string;
  phoneNumber: string;
  userName: string;
  userPassword: string;
}

export const sendRegisterRequest = async (payload: IRegisterPayload) => {
  return axiosInstance.post('/users', payload);
};
