<template>
     <v-container class="cont__main">    

      <h2 class="text-h4 success--text pl-4">
        Tasks:&nbsp;
        <v-fade-transition leave-absolute>
          <span :key="`tasks-${tasks.length}`">
            {{ tasks.length }}
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

      <v-card v-if="tasks.length > 0">
        
          <template v-for="(task, i) in tasks">         
            <v-divider
              v-if="i !== 0"
              :key="`${i}-divider`"
            ></v-divider>
            
            <v-list-item :key="`${i}-${task.text}`">
              <v-list-item-action>
                <v-checkbox
                  v-model="task.done"
                  :color="task.done && 'grey' || 'primary'"
                >
                  <template v-slot:label>
                    <div
                      :class="task.done && 'grey--text' || 'primary--text'"
                      class="ml-4"
                      v-text="task.text"
                    ></div>
                  </template>
                </v-checkbox>
              </v-list-item-action>

              <v-spacer></v-spacer>

              <v-scroll-x-transition>
                <v-icon
                  v-if="task.done"
                  color="success"
                >
                  mdi-check
                </v-icon>
              </v-scroll-x-transition>
            </v-list-item>          
          </template>
          <v-row>
            <div class="table__names d-flex flex-column ma-5 my-auto">
                <div class="">
                  <h3>Puser1</h3>
                </div>
                <div class="">
                  user1
                </div>
                <div class="">
                  user2
                </div>                
            </div>            
            <div class="table__score d-flex flex-column ma-5 my-auto">
                <div class="">
                  <h3>Scr</h3>
                </div>
                <div class="">
                  3
                </div>
                <div class="">
                  1
                </div>                
            </div>
            <div class="table__round d-flex flex-column ma-5 my-auto">
                  <h3>Scr</h3>
                <div class="table__round d-flex flex-row ma-5 my-auto">
                <div 
                class="table__round d-flex flex-column my-auto"
                v-for="index in 5"
                :key="index"
                >
                    <div class="table__round2">
                      3
                    </div>
                    <div class="table__round2">
                      1
                    </div>
                </div>                                               
            </div>
            </div>
            <v-spacer/>
            <div class="table__btn d-flex flex-row justify-content-end ma-5 my-auto">  
                  <v-tooltip top>
                    <template v-slot:activator="{ on, attrs } ">
                    <v-btn 
                    small 
                    flat 
                    color="primary"
                    v-bind="attrs"
                    v-on="on"
                    >
                      <v-icon small left>mdi-folder</v-icon>
                      <span class="caption text-lowercase"></span>
                    </v-btn>                    
                    </template>
                    <span>Edit score</span>
                  </v-tooltip>
                  <v-tooltip top>
                    <template v-slot:activator="{ on, attrs } ">
                    <v-btn 
                    small 
                    flat 
                    color="primary"
                    v-bind="attrs"
                    v-on="on"
                    >
                      <v-icon small left>mdi-folder</v-icon>
                      <span class="caption text-lowercase"></span>
                    </v-btn>                    
                    </template>
                    <span>Save score</span>
                  </v-tooltip>
              
            
            </div>
             

          </v-row>

       
      </v-card>
  </v-container>
</template>

<script>
import UserService from '../services/user.service';
export default {
    name: 'ScheduleView',
    data() {
        return {
            tasks: [
        {
          done: false,
          text: 'Foobar',
        },
        {
          done: false,
          text: 'Fizzbuzz',
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
            }
        );
    },

    computed: {
      completedTasks () {
        return this.tasks.filter(task => task.done).length
      },
      progress () {
        return this.completedTasks / this.tasks.length * 100
      },
      remainingTasks () {
        return this.tasks.length - this.completedTasks
      },
    },

    methods: {     
    },
}
</script>
<style scoped>
.cont__main {
  max-width: 1200px;  
}

.table__names {
  width: 200px;
  border: solid;
  text-align: center;
}
.table__score {
  width: 100px;
  border: solid;
  text-align: center;
}
.table__round {
  
  border: solid;
  text-align: center;
}
.table__round2{
  width: 32px;  
  border: solid;
  text-align: center;
}
.table__btn{
  width: 120px;  
  border: solid;
  
}

</style>