import Vue from 'vue'
import Router from 'vue-router'
import GetCustomers from '@/components/GetCustomers'
// import Greeting from '@/components/GetCustomers'

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        // {
        //     path: '/',
        //     name: 'Greeting',
        //     component: Greeting
        // },
        {
            path: '/api/v1/customers',
            name: 'GetCustomers',
            component: GetCustomers
        }
    ]
})