import { axiosInstance } from '.';

export const getUserInfo = async () => {
  return axiosInstance.get('/mypage', {
    headers: {
      Authorization: localStorage.getItem('accessToken'),
    },
  });
};

export const getMyCar = async () => {
  return axiosInstance.get('/mycars', {
    headers: {
      Authorization: localStorage.getItem('accessToken'),
    },
  });
};
