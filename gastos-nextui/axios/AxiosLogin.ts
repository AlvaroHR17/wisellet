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
    const response = await fetch(`${API_URL}${path}`, {
      method: 'POST',
      credentials: 'include',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(idToken),
    });
    if (!response.ok) throw new Error('bad server condition');
    return true;
  } catch (e) {
    if(e instanceof Error) console.error('postLoginToken Error: ', e.message);
    else console.error('postLoginToken Error: An unknow error occurred');
    return false;
  }
};