import { axiosInstance } from '.';

export const sendLogInRequest = async (email: string, userPassword: string) => {
  return axiosInstance.post('/login', {
    email,
    userPassword,
  });
};

interface IRegisterPayload {
  email: string;
  phoneNumber: string;
  userName: string;
  userPassword: string;
}

export const sendRegisterRequest = async (payload: IRegisterPayload) => {
  return axiosInstance.post('/users', payload);
};

export const sendAuthTestRequest = async () => {
  const accessToken = localStorage.getItem('accessToken');
  return axiosInstance.get('/auth', {
    headers: { Authorization: accessToken },
  });
};

export const checkEmailValidationRequest = async (email: string) => {
  return axiosInstance.get(`/users/validation/${email}`);
};
export const routeGaurd = (resolve: Function, reject: Function) => {
  HandleAuthAPICall(sendAuthTestRequest, resolve, reject);
};
export const HandleAuthAPICall = async (
  api: Function,
  resolve: Function,
  reject: Function
) => {
  api()
    .then((res: any) => {
      resolve(res);
    })
    .catch(({ response }: any) => {
      if (response.status === 302) {
        sendReissueRequest()
          .then((res: any) => {
            setToken(res);
            resolve(res);
          })
          .catch(() => {
            reject();
          });
      }
      reject();
    });
};

function setToken(res: any) {
  const { accessToken, refreshToken } = res.data;
  localStorage.setItem('accessToken', accessToken);
  localStorage.setItem('refreshToken', refreshToken);
}

function sendReissueRequest() {
  const refreshToken = localStorage.getItem('refreshToken');
  return axiosInstance.post('/reissue', null, {
    headers: { Authorization: refreshToken },
  });
}
