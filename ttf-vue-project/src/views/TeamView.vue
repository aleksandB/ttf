<template>
    
    <div class="team mx-5">Team Admin page
        <h3>{{ content }}</h3>
    </div>
    
</template>

<script>
import UserService from '../services/user.service';
export default {
    name: 'TeamView',
    data() {
        return {
            content: ''
        };
    },
    mounted() {
        UserService.getAdminBoard().then(
            response => {
                this.content = response.data;
            },
            error => {
                this.content =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
        );
    }
}
</script>
