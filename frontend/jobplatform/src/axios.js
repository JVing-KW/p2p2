import axios from 'axios';

// axios 인스턴스 생성
const apiInstance = axios.create({
  baseURL: 'http://localhost:80'
});

// 공통 HTTP 메서드 정의
const api = {
  async $get(url, params) {
    try {
        const response = await apiInstance.get(url, params);
        return response.data;
    } catch (err) {
        console.error(err);
        throw err; // 에러를 다시 던져서 호출한 곳에서 처리할 수 있게 함
    }
  },
  async $post(url, data) {
    try {
        const response = await apiInstance.post(url, data);
        return response.data;
    } catch (err) {
        console.error(err);
        throw err;
    }
  },
  async $put(url, data) {
    try {
        const response = await apiInstance.put(url, data);
        return response.data;
    } catch (err) {
        console.error(err);
        throw err;
    }
  },
  async $patch(url, data) {
    try {
        const response = await apiInstance.patch(url, data);
        return response.data;
    } catch (err) {
        console.error(err);
        throw err;
    }
  },
  async $delete(url) {
    try {
        const response = await apiInstance.delete(url);
        return response.data;
    } catch (err) {
        console.error(err);
        throw err;
    }
  }
};

export { api };
