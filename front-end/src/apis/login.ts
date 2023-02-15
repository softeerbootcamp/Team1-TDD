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

export const sendAuthTestRequest = async () => {
  const accessToken = localStorage.getItem('accessToken');
  return axiosInstance.get('/auth', {
    headers: { Authorization: accessToken },
  });
};

export const checkEmailValidationRequest = async (email: string) => {
  return axiosInstance.get(`/users/validation/${email}`);
};
