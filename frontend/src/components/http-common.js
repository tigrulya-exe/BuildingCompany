import axios from 'axios'
import history from './history'

export const AXIOS = axios.create({
    baseURL: `http://localhost:8080/api/v1`
});

export const axiosNonApi = axios.create({
    baseURL: `http://localhost:8080/`
});

AXIOS.interceptors.request.use(request => {
    const token = localStorage.getItem("jwt");
    console.log(token);
    console.log(request);
    if(token){
        request.headers.Authorization = `Bearer ${token}`
    }
    return request
})

AXIOS.interceptors.response.use(null, function(err) {
    if ( err.response.status === 401 || err.response.status === 403 ) {
        history.push('/login');
    }

    return Promise.reject(err);
});
axiosNonApi.interceptors.response.use(request => {
    console.log('Starting Request', request)
    return request
})