<template>

     <v-container class="cont__main">    
       
      <h2 class="text-h4 success--text pl-4">
        Games:&nbsp;
        <v-fade-transition leave-absolute>
          <span :key="`games-${games.length}`">
            {{ games.length }}
          </span>
        </v-fade-transition>
      </h2> 
      <v-divider class="mt-4"></v-divider>

      <v-row
        class="my-1"
        align="center"
      >
        <strong class="mx-4 info--text text--darken-2">
          Remaining: {{ remainingTasks }}
        </strong>

        <v-divider vertical></v-divider>

        <strong class="mx-4 success--text text--darken-2">
          Completed: {{ completedTasks }}
        </strong>

        <v-spacer></v-spacer>

        <v-progress-circular
          :value="progress"
          class="mr-2"
        ></v-progress-circular>
      </v-row>

      <v-divider class="mb-4"></v-divider>

      <v-card v-if="games.length > 0" class="scroll">
        
          <template v-for="(game, i) in games">         
            <v-divider
              v-if="i !== 0"
              :key="`${i}-divider`"
            ></v-divider>
            
            <v-list-item :key="`${i}-${game.game_id}`">
              <v-list-item-action>               
                    <v-row>
                      <div class="table__names d-flex flex-column ma-5 my-auto">
                          <div class="">
                            <h3>Players</h3>
                          </div>
                          <div class="">
                            {{ game.player_id1 }}
                          </div>
                          <div class="">
                            {{ game.player_id2 }}
                          </div>                
                      </div>            
                      <div v-if="game.game_total_player1 !== null" class="table__score d-flex flex-column ma-5 my-auto">
                          <div class="">
                            <h3>Total score</h3>
                          </div>
                          <div class="">
                            {{ game.game_total_player1 }}
                          </div>
                          <div class="">
                            {{ game.game_total_player2 }}
                          </div>                
                      </div>
                      <div v-if="game.game_total_player1 !== null" class="table__round_ d-flex flex-column ma-5 my-auto">
                            <h3>Score</h3>
                          <div class="table__round d-flex flex-row ma-5 my-auto">
                          <div 
                          class="table__round d-flex flex-column my-auto"
                          v-for="index in 5"
                          :key="index"
                          >
                              <div v-if="[`game.score_set${index}_player1`]!==null" class="table__round2">
                                {{  game[`score_set${index}_player1`] }}
                              </div>
                              <div class="table__round2">
                                {{ game[`score_set${index}_player2`]}}
                              </div>
                          </div>                                               
                      </div>
                      </div>
                      <div v-else class="table__round__tt d-flex flex-column ma-5 my-auto">
                          <p>Waiting results</p>
                      </div>                      
                      
                      <v-spacer/>
                      <div v-if="!game.done && getCurrentUser(i)" class="table__btn d-flex flex-row justify-content-end ma-5 my-auto">  
                            <v-tooltip top>
                              <template v-slot:activator="{ on, attrs } ">
                              <v-btn
                              class="ma-1"  
                              small                     
                              color="primary"
                              v-bind="attrs"
                              v-on="on"
                              @click ="showScheduleForm=true"
                              >
                              <EditScore
                                v-model="showScheduleForm"
                                :gameInc.sync ="games[i]"                                
                              >
                              </EditScore>
                              
                                <v-icon fab centered>mdi-pencil</v-icon>
                                <span class="caption text-lowercase"></span>
                              </v-btn>                    
                              </template>
                              <span>Edit score</span>
                            </v-tooltip>
                            <v-tooltip top>
                              <template v-slot:activator="{ on, attrs } ">
                              <v-btn
                              class="ma-1" 
                              small                      
                              color="info"
                              v-bind="attrs"
                              v-on="on"
                              @click="saveState(i)"
                              >
                                <v-icon fab centered>mdi-package-down</v-icon>
                                <span class="caption text-lowercase"></span>
                              </v-btn>                    
                              </template>
                              <span>Save score</span>
                            </v-tooltip>
                        </div>    
                        <div v-else-if="game.done" class="table__btn d-flex flex-row justify-content-end ma-5 my-auto">
                          <v-spacer></v-spacer>
                          <v-scroll-x-transition>
                          <v-icon
                            fab
                            large                  
                            color="success"
                          >
                            mdi-check
                          </v-icon>
                        </v-scroll-x-transition>

                      </div>
                    </v-row>
              </v-list-item-action>             
              
              
            </v-list-item>          
          </template>
         

       
      </v-card>
  </v-container>
</template>

<script>
import UserService from '../services/user.service';
import EditScore from '../components/EditScore.vue';


export default {
    components: {EditScore},
    name: 'ScheduleView',
    data() {
        return {
            showScheduleForm: false,            
            games: [
                    {
                      game_id: 1,
                      player_id1: "aleksB",
                      player_id2: "aleksB222222222222222222222222222222222222",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,                               
                    },
                    {
                      game_id: 2,
                      player_id1: "aleksB",
                      player_id2: "aleksB2222222222222222222222222222222222",
                      game_total_player1: 3,
                      game_total_player2: 2,
                      score_set1_player1: 2,
                      score_set1_player2: 11,
                      score_set2_player1: 11,
                      score_set2_player2: 1,
                      score_set3_player1: 11,
                      score_set3_player2: 5,
                      score_set4_player1: 3,
                      score_set4_player2: 11,
                      score_set5_player1: 13,
                      score_set5_player2: 11,          
                      done: true,                                 
                    },
                    {
                      game_id: 3,
                      player_id1: "aleksB3",
                      player_id2: "aleksB",
                      game_total_player1: 3,
                      game_total_player2: 0,
                      score_set1_player1: 13,
                      score_set1_player2: 11,
                      score_set2_player1: 11,
                      score_set2_player2: 1,
                      score_set3_player1: 11,
                      score_set3_player2: 5,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: true,           
                    },
                    {
                      game_id: 4,
                      player_id1: "aleksB321",
                      player_id2: "aleksB1",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,          
                    },
                    {
                      game_id: 5,
                      player_id1: "aleksB3",
                      player_id2: "aleks44B",
                      game_total_player1: 4,
                      game_total_player2: 1,
                      score_set1_player1: 13,
                      score_set1_player2: 11,
                      score_set2_player1: 11,
                      score_set2_player2: 1,
                      score_set3_player1: 11,
                      score_set3_player2: 5,
                      score_set4_player1: 11,
                      score_set4_player2: 6,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: true,          
                    },
                    {
                      game_id: 6,
                      player_id1: "aleksB3",
                      player_id2: "aleks44B",
                      game_total_player1: 4,
                      game_total_player2: 1,
                      score_set1_player1: 13,
                      score_set1_player2: 11,
                      score_set2_player1: 11,
                      score_set2_player2: 1,
                      score_set3_player1: 11,
                      score_set3_player2: 5,
                      score_set4_player1: 11,
                      score_set4_player2: 6,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: true,         
                    },
                    {
                      game_id: 7,
                      player_id1: "aleksB3",
                      player_id2: "aleks44B",
                      game_total_player1: 4,
                      game_total_player2: 1,
                      score_set1_player1: 13,
                      score_set1_player2: 11,
                      score_set2_player1: 11,
                      score_set2_player2: 1,
                      score_set3_player1: 11,
                      score_set3_player2: 5,
                      score_set4_player1: 11,
                      score_set4_player2: 6,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: true,          
                    },
                    {
                      game_id: 8,
                      player_id1: "aleksB321",
                      player_id2: "aleksB1",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,          
                    },
                    {
                      game_id: 9,
                      player_id1: "aleksB321",
                      player_id2: "aleksB1",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,        
                    },
                    {
                      game_id: 10,
                      player_id1: "aleksB321",
                      player_id2: "aleksB1",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,         
                    },
                    {
                      game_id: 14,
                      player_id1: "aleksB114",
                      player_id2: "aleksB1",
                      game_total_player1: null,
                      game_total_player2: null,
                      score_set1_player1: null,
                      score_set1_player2: null,
                      score_set2_player1: null,
                      score_set2_player2: null,
                      score_set3_player1: null,
                      score_set3_player2: null,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,         
                    },
      ]
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
                this.$router.push('/profile');
            }
        );
    },

    computed: {
      completedTasks () {
        return this.games.filter(game => game.done).length
      },
      progress () {
        return this.completedTasks / this.games.length * 100
      },
      remainingTasks () {        
        return this.games.length - this.completedTasks
      },
        
     
    },

    methods: {
      getCurrentUser(index){
        console.log(index)
        console.log(this.games[index].player_id1)
        if(this.games[index].player_id1 == this.$store.state.auth.user.username
        || this.games[index].player_id2 == this.$store.state.auth.user.username) {
          console.log("true")
          return true;
        } else {
          console.log("false")
          return false;
        }
      },
      editScore(){
        //console.log(this.games[index].game_id);
        console.log("test")         
      },
      saveState(index){
        this.games[index].done=true;
      },
      
    }    
}
</script>
<style scoped>
.cont__main {
  max-width: 900px;  
}

.table__names {
  width: 200px;
  
  text-align: center;
}
.table__score {
  
  width: 100px;
  
  text-align: center;
}
.table__round_ {  
  width: 230px;
  
  text-align: center;
}
.table__round {  
 
  text-align: center;
}
.table__round__tt { 
  width: 372px;
  height: 40px; 
  
  text-align: center;
}

.table__round2{
  width: 40px; 
  
  text-align: center;
}
.table__btn{
  width: 120px;  
  
}

.scroll{
  overflow-y: scroll;
  height: 400px
}
</style>