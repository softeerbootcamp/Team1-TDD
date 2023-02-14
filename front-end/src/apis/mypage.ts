import { axiosInstance } from ".";

export const getUserInfo = async () => {
  return axiosInstance.get("/mypage", {
    headers: {
      Authorization: localStorage.getItem("accessToken"),
    },
  });
};
