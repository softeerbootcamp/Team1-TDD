<<<<<<< HEAD
import { sharing } from '@/components/SharingForm/interface';
=======
>>>>>>> 4a1ee4e4841462295f90b97aa609857821152723
import { axiosInstance } from '.';

export const sendGetMyCarRequest = async () => {
  const accessToken = localStorage.getItem('accessToken');
  return axiosInstance.get('/mycars', {
    headers: { Authorization: accessToken },
  });
};
<<<<<<< HEAD

export const sendSharingRequest = async (reqBody: sharing) => {
  const accessToken = localStorage.getItem('accessToken');
  axiosInstance.post('/sharing', reqBody, {
    headers: { Authorization: accessToken },
  });
};
=======
>>>>>>> 4a1ee4e4841462295f90b97aa609857821152723
