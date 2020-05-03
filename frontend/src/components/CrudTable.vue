<template>
    <div>
        CRUD TABLE VIEW
    </div>
    <crud-navigation-bar/>
    <router-view name="entity"></router-view>>
</template>

<script>
    import {AXIOS} from './http-common'
    import CrudNavigationBar from "@/components/CrudNavigationBar";
    export default {
        name: 'CrudTable',
        data() {
            return {
                customers: [{
                    id: 0,
                    name: ''
                }]
            }
        },
        methods: {
            getCustomers() {
                AXIOS.get('/customers')
                    .then(response => {
                        alert(response.data);
                        this.$data.customers = response.data
                    })
                    .catch(error => {
                        console.log('ERROR: ' + error.response.data);
                    })
            }
        },
        components:{
            CrudNavigationBar
        },
        mounted() {
            this.getCustomers();
        }
    }
</script>

