<template>
    <ul id="example-1">
        <li v-for="customer in customers" :key="customer.id">
            <p>Id: {{ customer.id }}</p>
            <p>Name: {{ customer.name }}</p>
        </li>
    </ul>
</template>

<script>
    import {AXIOS} from './http-common'

    export default {
        name: 'Customer',
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
                AXIOS.get('http://localhost:8080/api/v1/customers')
                    .then(response => {
                        alert(response.data);
                        this.$data.customers = response.data
                    })
                    .catch(error => {
                        console.log('ERROR: ' + error.response.data);
                    })
            }
        },
        mounted() {
            this.getCustomers();
        }
    }
</script>