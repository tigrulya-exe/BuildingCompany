<template>
    <div class="crud_nav">
        <router-link to="/crud/customers">emails</router-link>
        <br>
        <router-link to="/settings/profile">profile</router-link>
    </div>
</template>

<script>
    export default {
        name: 'NavBar'
    }
</script>

<style scoped>
    .crud_nav {
        grid-area: nav;
        border: 1px dotted;
        margin-right: .75rem;
        padding: .3rem;
    }
</style>