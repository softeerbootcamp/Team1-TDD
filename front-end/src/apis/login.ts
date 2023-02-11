import { axiosInstance } from '.';

export const sendLogInRequest = async (email: string, userPassword: string) => {
  return axiosInstance.post('/login', {
    email,
    userPassword,
  });
};
