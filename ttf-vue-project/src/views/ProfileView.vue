<template>
    <div v-if="currentUser" class="profile mx-5">Dashboard
        <div class="container">
        <header class="jumbotron">
        <h3>
            <strong>{{currentUser.username}}</strong> Profile
        </h3>
        </header>
        <p>
        <strong>Token:</strong>
               {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
        </p>
        <p>
        <strong>Id:</strong>
               {{currentUser.id}}
        </p>
        <p>
        <strong>Email:</strong>
              {{currentUser.email}}
        </p>
        <strong>Authorities:</strong>
        <ul>
        <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
        </ul>
        </div>
    </div>
    <div v-else>"Auth required"</div>

</template>

<script>
export default {
    name: 'ProfileView',
    computed: {
        currentUser() {
           return this.$store.state.auth.user 
        }
    },
    mounted() {
        if(!this.currentUser){
            console.log('Auth required')
            //this.$router.push('/profile') //change this method
        }
    }
}
</script>