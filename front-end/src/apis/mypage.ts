import { axiosInstance } from ".";

export const getUserInfo = async () => {
  const accessToken = localStorage.getItem("accessToken");
  return axiosInstance.get("/test/auth", {
    headers: { Authorization: accessToken },
  });
};
