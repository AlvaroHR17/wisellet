import axios from "axios";

export const getAuth = async(): Promise<boolean> => {
  const API_URL = 'http://localhost:8080';
  const path = '/v1/oauth/auth';

  try {
    const response = await axios.get(`${API_URL}${path}`, {
      withCredentials: true,
      headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
      }
    });
    return response.data as boolean;
      
  } catch (e:any) {
    console.error('getUserInfo Error: ', e.message);
    return false;
  }
}

export const postLoginToken = async (idToken: string): Promise<boolean> => {
  const API_URL = 'http://localhost:8080';
  const path = '/v1/oauth/login';

  try {
    const response = await axios.post(`${API_URL}${path}`, idToken, {
      withCredentials: true,
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    });
    if (response.status !== 200) throw new Error('bad server condition');
    return true;
  } catch (e) {
    if(e instanceof Error) console.error('postLoginToken Error: ', e.message);
    else console.error('postLoginToken Error: An unknow error occurred');
    return false;
  }
};

export const invalidateToken = async() => {
  const API_URL = 'http://localhost:8080';
  const path = '/v2/oauth/logout';

  try {
    const response = await axios.get(`${API_URL}${path}`, {
      withCredentials: true,
      headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
      }
    });
    if (response.status !== 200) throw new Error('bad server condition');
  } catch (e:any) {
    console.error('Logout Error: ', e.message);
  }

}