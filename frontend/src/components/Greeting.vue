<template>
    <div id="greeting">
        <h3>Greeting component</h3>
        <p>Username: {{ username }}</p>
    </div>
</template>

<script>
import {AXIOS} from '../http-common'

export default {
    name: 'Greeting',
    data() {
        return {
            username: ''
        }
    },
    methods: {
        loadGreeting() {
            AXIOS.get('/ping', { params: { name: 'Mike' } })
            .then(response => {
                this.$data.username = response.data.content;
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

<style>
</style>