<template>
<v-container class="container__main">
  <v-row> 
  <v-col class="col__right"
          cols="12"
          sm="5">
      <v-simple-table dark>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Name
          </th>
          <th class="text-left">
            League
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="player in players"
          :key="player.username"
        >
          <td>{{ player.username }}</td>
          <td>{{ player.league }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <v-btn class="ma-5" color="success" @click="getPlayersInfo()">Get info</v-btn>
  </v-col>
  <v-col class="col__middle">
    <v-row>
          <v-col
          cols="12"
          sm="6"
          >
          <v-date-picker
            v-model="dates"
            multiple
          ></v-date-picker>
        </v-col>
        <v-col>
        <v-row>
          <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Количество команд
           </div>
           <div style="text-align: center;">
              {{ this.players.length }}
           </div> 
          </div>
          <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Количество кругов
           </div>
           <div>
              <v-text-field
                  class="ma-2"                                     
                  name="text_field"
                  v-model="cycles"                             
                  outlined
                  dense                                    
                  style = "width: 50px;">
              </v-text-field>
            </div>
          </div>
          <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Количество игр
           </div>
           <div style="text-align: center;">
              {{ this.getTotalGames() }}
           </div> 
          </div>
        </v-row>
        <v-row>        
          <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Win3clean
           </div>
           <div>
              <v-text-field
                  class="ma-2"                                     
                  name="text_field"
                  v-model="win_score"                             
                  outlined
                  dense                                    
                  style = "width: 50px;">
              </v-text-field>
            </div>
          </div>
            <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Win32
           </div>
           <div>
              <v-text-field
                  class="ma-2"                                     
                  name="text_field"
                  v-model="win_score3"                             
                  outlined
                  dense                                    
                  style = "width: 50px;">
              </v-text-field>
            </div>
          </div>
          <div class="flex-column ma-2 mt-5" style="width: 80px;" >
           <div class="ma-2">
             Lost2
           </div>
           <div>
              <v-text-field
                  class="ma-2"                                     
                  name="text_field"
                  v-model="lost_score2"                             
                  outlined
                  dense                                    
                  style = "width: 50px;">
              </v-text-field>
            </div>
          </div>       
        </v-row>
        </v-col>         
      </v-row>
  </v-col>
  </v-row>
  <v-row>
    <v-btn class="ma-5" color="success" @click="generateGames()">
      Generate
    </v-btn>
  </v-row>
  <v-row> 
  </v-row>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <v-data-table
      :headers="headers"
      :items="games"
      :items-per-page="15"
      class="elevation-1"
    ></v-data-table>
  </div>
</v-container>  
</template>
<script>
import UserService from '../services/user.service';
import GameService from '../services/game.service';
import { showSnackbar } from "../globalActions";
export default {
  name: 'AdminView',
  data() {
    return {
      content: '',
      players: [],
      cycles: '1',
      win_score: '',
      win_score3: '',
      lost_score2: '',
      dates:[],
      headers:[{
        text: 'Game',
        align: 'start',
        sortable: false,
        value: 'id'
      },
      {text: 'Player 1 Id', value: 'player1_id'},
      {text: 'Player 1 Name', value: 'player1_name'},
      {text: 'Player 2 Id', value: 'player2_id'},
      {text: 'Player 2 Name', value: 'player2_name'},
      {text: 'date', value: 'date'},
      ],
      games:[]
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
  },
  methods: {
    getPlayersInfo(){     
          GameService.getInfoPlayers().then(
            (response) => {
                this.players = response.data;                
                showSnackbar("Data loaded successfully")  
            },
            (error) => {
                this.content =
                (error.response && error.response.data) ||
                error.message || error.toString();
            }
        );      
    },
    generateGames(){   
      this.shuffleDates();   
      for(var i = 0; i < this.cycles; i++){
        i%2 ? this.putPlayersToTableList() : this.putPlayersToTableListReverse()
      }  
    },
    putPlayersToTableList(){
      let count = 1;      
      while(count < this.players.length){
        for(var i = 0; i < this.players.length - count; i++){         
          this.games.push({
              id: this.getTotalGames() - this.games.length,
              player1_id: this.players[i].playerId,
              player1_name: this.players[i].username,
              player2_id: this.players[i+count].playerId,
              player2_name: this.players[i+count].username,
              date: this.dates[this.getTotalGames() - this.games.length-1]              
          })          
        }
        count++
      }
    },
    putPlayersToTableListReverse(){
      let count = 1;      
      while(count < this.players.length){
        for(var i = 0; i < this.players.length - count; i++){         
          this.games.push({
              id: this.getTotalGames() - this.games.length,
              player1_id: this.players[i+count].playerId,
              player1_name: this.players[i+count].username,
              player2_id: this.players[i].playerId,
              player2_name: this.players[i].username,
              date: this.dates[this.getTotalGames() - this.games.length-1]              
          })          
        }
        count++
      }
    },
    getTotalGames(){
      return this.players.length*(this.players.length-1)*this.cycles/2
    },
    shuffleDates(){
      //let arr = [];      
      while(this.dates.length < this.getTotalGames()){
        this.dates.push(this.dates[Math.floor(Math.random()*this.dates.length)])
      }
      console.log(this.dates);

    }

  }
};
</script>

<style scoped>
.container__main{
  
  border-block: solid;
}
.col__right{
  width: 400px;
  border-block: solid;
}
</style>