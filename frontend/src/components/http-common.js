import axios from 'axios'

export const AXIOS = axios.create({
    baseURL: `http://localhost:8080/api/v1`
});

export const axiosNonApi = axios.create({
    baseURL: `http://localhost:8080/`
});

AXIOS.interceptors.request.use(request => {
    console.log('Starting Request', request)
    return request
})