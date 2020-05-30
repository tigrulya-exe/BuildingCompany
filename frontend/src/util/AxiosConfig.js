import axios from 'axios'
import history from '../routing/History'
import {tokenStorage} from "../context/AuthContextProvider";

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
    if (token) {
        request.headers.Authorization = `Bearer ${token}`
    }
    return request
};

const logResponse = (response) => {
    console.log(response);
    return response;
};

const redirectIfNotAuthorized = (err) => {
    console.log(err.response);
    if (err.response.status === 401) {
        tokenStorage.refreshTokens();
    }
    if(err.response.status === 403){
        // alert("Forbidden");
        // history.goBack();
    }
    return Promise.reject(err);
};

AXIOS.interceptors.request.use(injectToken);
axiosNonApi.interceptors.request.use(injectToken);

AXIOS.interceptors.response.use(logResponse, redirectIfNotAuthorized);
axiosNonApi.interceptors.response.use(logResponse, redirectIfNotAuthorized);

