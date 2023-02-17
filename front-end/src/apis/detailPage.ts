import { axiosInstance } from '.';

export const getPosts = async (postId: number) => {
  return axiosInstance.get(`/test-driving/${postId}`);
};
