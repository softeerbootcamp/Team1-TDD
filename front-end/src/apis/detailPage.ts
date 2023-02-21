import { axiosInstance } from '.';

export const getPosts = async (postId: number) => {
  return axiosInstance.get(`/test-driving/${postId}`);
};

export const patchAppoinment = async (appoinmentId: string) => {
  return axiosInstance.patch(`/appointments/${appoinmentId}`);
};
