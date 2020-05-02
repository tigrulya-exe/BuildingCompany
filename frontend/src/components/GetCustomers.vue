<template>
    <ul id="example-1">
        <li v-for="customer in customers" :key="customer.id">
            <p>Id: {{ id }}</p>
            <p>Name: {{ name }}</p>
        </li>
    </ul>
</template>

<script>
    import {AXIOS} from './http-common'

    export default {
        name: 'GetCustomers',
        data() {
            return {
                customers: [{
                    id: 0,
                    name: ''
                }]
            }
        },
        methods: {
            loadGreeting() {
                AXIOS.get('/customers')
                    .then(response => {
                        this.$data.id = response.data.id;
                        this.$data.name = response.data.name;
                    })
                    .catch(error => {
                        console.log('ERROR: ' + error.response.data);
                    })
            }
        },
        mounted() {
            this.loadGreeting();
        }
    }
</script>