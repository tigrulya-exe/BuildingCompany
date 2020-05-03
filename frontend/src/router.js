import Vue from 'vue'
import Router from 'vue-router'
import Container from "@/components/Container";

Vue.use(Router);

// export default new Router({
//     mode: 'history',
//     routes: [
//         {
//             path: '/crud/:entityName',
//             component: CrudTable,
//             children: [
//                 {
//                     path: 'customers',
//                     component:
//                 }
//             ]
//         }
//     ]
// })

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/container',
            component: Container,
        }
    ]
})