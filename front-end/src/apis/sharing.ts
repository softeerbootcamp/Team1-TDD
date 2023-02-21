import { sharing } from '@/components/SharingForm/interface';
import { axiosInstance } from '.';

export const sendGetMyCarRequest = async () => {
  const accessToken = localStorage.getItem('accessToken');
  return axiosInstance.get('/mycars', {
    headers: { Authorization: accessToken },
  });
};

export const sendSharingRequest = async (reqBody: sharing) => {
  const accessToken = localStorage.getItem('accessToken');
  axiosInstance.post('/sharing', reqBody, {
    headers: { Authorization: accessToken },
  });
};
