import { axiosInstance } from '.';

export const sendGetMyCarRequest = async () => {
  const accessToken = localStorage.getItem('accessToken');
  return axiosInstance.get('/mycars', {
    headers: { Authorization: accessToken },
  });
};
