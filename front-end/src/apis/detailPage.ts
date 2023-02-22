import { axiosInstance } from '.';

export const getPosts = async (postId: number) => {
  return axiosInstance.get(`/test-driving/${postId}`);
};

export const patchAppoinment = async (appoinmentId: number) => {
  const accessToken = localStorage.getItem('accessToken');

  return axiosInstance.patch(
    `/appointments/${appoinmentId}`,
    {},
    {
      headers: { Authorization: accessToken },
    }
  );
};
