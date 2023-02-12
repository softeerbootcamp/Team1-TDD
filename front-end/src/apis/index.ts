import axios from 'axios';

export const axiosInstance = axios.create({
  baseURL: process.env.VITE_PUBLIC_API_BASEURL,
});
