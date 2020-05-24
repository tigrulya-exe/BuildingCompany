import axios from 'axios'
import history from '../routing/History'

export const AXIOS = axios.create({
    baseURL: `http://localhost:8080/api/v1`
});

export const axiosNonApi = axios.create({
    baseURL: `http://localhost:8080/`
});

/**
 * Если в локал сторадж есть жвт, пихаем его в каждый запрос
 */

const injectToken = (request) => {
    const token = localStorage.getItem("jwt");
    console.log(request);
    if(token){
        request.headers.Authorization = `Bearer ${token}`
    }
    return request
};

const logResponse = (response) => {
    console.log(response);
    return response;
}

/**
 * Если сервер вернул 401 или 403 (позже убрать) ошибку то редиректим на путь логина
 */

const redirectIfNotAuthorized = (err) => {
    console.log(err.response);
    if ( err.response.status === 401 || err.response.status === 403 ) {
        history.push('/login');
    }
    return Promise.reject(err);
};

AXIOS.interceptors.request.use(injectToken);
axiosNonApi.interceptors.request.use(injectToken);

AXIOS.interceptors.response.use(logResponse, redirectIfNotAuthorized);
axiosNonApi.interceptors.response.use(null, redirectIfNotAuthorized);

