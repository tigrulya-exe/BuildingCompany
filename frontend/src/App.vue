<template>
    <div id="app">
        <h1> MAIN MENU </h1>
        <!--    <router-link to="/crud/customers">Customers</router-link>-->
        <br>
        <div>
            <router-link to="/container">link</router-link>
        </div>

        <router-view></router-view>

    </div>
</template>

<script>
    export default {
        name: 'app',
    }
</script>

<style>
    #app {
        margin-top: 60px
    }
</style>