<template>
  <div class="table__main">
    <div class="button__main">
      <v-row class="justify-end" style="height: 80px;">            
              <v-btn @click="addPlayerAct()" color="info" class="pa-2 ma-5">Create a new request</v-btn> 
          </v-row> 
     </div>
      <v-data-table
        :headers="headers"
        :items="teams"
        single-expand    
        :expanded.sync="expanded"
        item-key="names"
        show-expand
        class="elevation-1"
    >
        <template v-slot:top>
        <v-toolbar flat>
            <v-toolbar-title>Expandable Table</v-toolbar-title>
            <v-spacer></v-spacer>              
        </v-toolbar>
        </template>
        <template v-slot:expanded-item="{ headers, item }">
        <td :colspan="headers.length">
            More info about {{ item.names }}
        </td>
        </template>
        <template v-slot:[`item.places`]="{ item }">
        <v-chip
            :color="getColor(item.places)"
            dark
        >
            {{ item.places }}
        </v-chip>
        </template>
    </v-data-table>
  </div>
</template>

<script>
import UserService from '../services/user.service';
import GameService from '../services/game.service';
import { showSnackbar } from "../globalActions";
export default {
    name: 'DashboardView',
    data () {
      return {
        expanded: [],             
        headers: [
          {
            text: '',
            align: 'start',
            sortable: false,
            value: 'places',
          },
          { text: 'Команда', value: 'names' },
          { text: 'И', value: 'games' },
          { text: 'В', value: 'wins' },
          { text: 'П', value: 'false' },
          { text: 'Мячи', value: 'balls' },
          { text: 'Разница', value: 'differences' },
          { text: 'Очки', value: 'scores' },
          { text: '', value: 'data-table-expand' },
        ],
        teams: [
          {
            places: 1,
            names: 'Frozen Yogurt',
            games: 10,
            wins: 9,
            false: 1,
            balls: '53-10',
            differences: 53-10,
            scores: 25            
          },
          {            
            places: 2,
            names: 'Ice cream sandwich',
            games: 10,
            wins: 8,
            false: 2,
            balls: '23-20',
            differences: 23-20,
            scores: 21     
          },
          {            
            places: 3,
            names: 'Eclair',
            games: 10,
            wins: 7,
            false: 3,
            balls: '43-30',
            differences: 43-30,
            scores: 20  
          },
          {           
            places: 4,
            names: 'Cupcake',
            games: 10,
            wins: 6,
            false: 4,
            balls: '45-40',
            differences: 45-40,
            scores: 16  
          },
          {            
            places: 5,
            names: 'Gingerbread',
            games: 10,
            wins: 6,
            false: 4,
            balls: '15-40',
            differences: 15-40,
            scores: 16  
          },
          {            
            places: 6,
            names: 'Jelly bean',
            games: 10,
            wins: 5,
            false: 5,
            balls: '64-64',
            differences: 64-64,
            scores: 14
          },
          {
            places: 7,
            names: 'Lollipop',
            games: 10,
            wins: 2,
            false: 8,
            balls: '14-24',
            differences: 14-24,
            scores: 10
          },
          {
            places: 8,
            names: 'Donut',
            games: 10,
            wins: 0,
            false: 10,
            balls: '17-14',
            differences: 17-14,
            scores: 0
          }
        ],
      }
    },
    methods: {
        getColor (places) {
        if (places > 6) return 'red'
        else if (places > 2) return 'orange'
        else return 'green'
      },
        addPlayerAct(){          
          console.log(this.$store.state.auth.user.id)
          GameService.addPlayer(this.$store.state.auth.user.id).then(
            (response) => {
                this.content = response.data;                
                showSnackbar(response.data.message)  
            },
            (error) => {
                this.content =
                (error.response && error.response.data) ||
                error.message || error.toString();
            }
        );
        },
    }, 
    mounted() {
      UserService.getPublicContent().then(
            (response) => {
                this.content = response.data;                
            },
            (error) => {
                this.content =
                (error.response && error.response.data) ||
                error.message || error.toString();
            }
        );
    }
};
</script>
<style>

</style>
