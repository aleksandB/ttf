<template> 
    <v-dialog max-width="550px" v-model="show">               
        <v-card>
            <v-card-title>
                <h2>Edit form</h2>                            
            </v-card-title>
            <v-card-text class="mt-5">
                <validation-observer
                    ref="observer"
                    v-slot="{ invalid }"
                >
                    <v-form class="px-3" @submit.prevent="submit">
                        <v-row>
                      <div class="table__names d-flex flex-column ma-5 my-auto">
                          <div class="">
                            <h3>Players</h3>
                          </div>
                          <div class="">
                            {{ gameInc.player_id1 }}
                          </div>
                          <div class="">
                            {{ gameInc.player_id2 }}
                          </div>                
                      </div>            
                      <div class="table__score d-flex flex-column ma-5 my-auto">
                          <div class="">
                            <h3>Total score</h3>
                          </div>
                          <div class="mx-auto">
                            {{ score1 }}
                          </div>
                          <div class="mx-auto">
                            {{ score2 }}
                          </div>                
                      </div>
                      <div class="table__round_ d-flex flex-column ma-5 mt-5 my-auto">
                            <h3>Score</h3>
                          <div class="table__round d-flex flex-row ma-5 my-auto">
                          <div 
                          class="table__round d-flex flex-column my-auto"
                          v-for="(n,index) in 5"
                          :key="index"
                          >
                           <validation-provider
                            v-slot="{ errors }"
                            name="text_field"
                            :rules="{
                                        required: false,                                        
                                        regex: '^[0-9]*$'
                            }"
                        >
                              <div class="pa-1 table__round2">
                                <v-text-field                                    
                                    name="text_field"
                                    v-model="scoresImm.pl1[n-1]"                             
                                    outlined
                                    dense                                    
                                    :error-messages="errors"
                                    style = "width: 45px;"                                    
                                    :disabled="submitted"                      
                                ></v-text-field>
                              </div>
                           
                              <div class="pa-1 table__round2">
                                <v-text-field                                    
                                    name="text_field"
                                    v-model="scoresImm.pl2[n-1]"                                
                                    outlined
                                    dense
                                    :error-messages="errors"                                    
                                    style = "width: 45px;"
                                    :disabled="submitted"                           
                                ></v-text-field>
                              </div>
                             </validation-provider>
                          </div>                                               
                      </div>
                      </div>
                        </v-row>                       

                         <validation-provider
                            v-slot="{ errors }"
                            rules="required"
                            name="checkbox"
                        >
                            <v-checkbox
                            v-model="checkbox"
                            @click="validateScr"
                            :error-messages="errors"
                            :input-value="checkbox"
                            label="Option"
                            type="checkbox"
                            required
                            disabled
                            ></v-checkbox>
                        </validation-provider>                     
                                         
                        <v-btn type="submit" class="form-group success rounded-pill" :loading="loading" :disabled ="invalid">Submit values</v-btn>                    
                        <v-btn @click="validateState" class="pa-2 ma-2 success rounded-pill">Verify values</v-btn>
                        <v-btn color="primary" @click.stop="show=false"> Close </v-btn>
                        <!--<v-btn @click="openSnackbar(msgSN)">Show snackbar</v-btn> -->
                        <div class="form-group">
                        <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
                        
                        </div>
                    </v-form>
                </validation-observer>
            </v-card-text>
        </v-card>
    </v-dialog>    
</template>

<script>
import { required, regex} from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
import { showSnackbar } from "../globalActions";

setInteractionMode('eager')

  extend('regex', {
    ...regex,
    message: '{_field_} {_value_} does not match {regex}',
  })

  extend('required', {
    ...required,
    message: '{_field_} can not be empty',
  })
  

export default {
    name: "EditScore",
    props: {
        value: {
            type: Boolean
        },
        gameInc: {
            type: Object
        }
    },
    components: {
        ValidationProvider,
        ValidationObserver
    },    
    data() {
        return {                 
            loading: false,
            submitted: false,
            successful: false,
            message: '',
            scoresImm: {
                pl1:[],
                pl2:[]
            },
            score1: 0,
            score2: 0,
            checkbox: null,                        
        }
    },   
    computed: {        
        show: {
            get(){                               
                return this.value
            },
            set(value) {
                this.$emit('input', value)
            }
        },

    },    
    created() {
        console.log("this point:" + this.value)
    },
    methods: {
        submit(){
            console.log("submit")
            this.$emit('update:gameInc', this.setData())
            this.show = false
            console.log(this.scoresImm.pl1[3])
            console.log(this.scoresImm.pl2.length)
        },
        validateState() {
                   
            this.$refs.observer.reset();
            this.score1=0
            this.score2=0
            this.validateScr();
            //this.message='';
            //this.checkbox = true,
            if(this.checkbox) this.submitted = true 
            console.log("clear")
        },        
        setData() {
               let data = {                
                      game_id: this.gameInc.game_id,
                      player_id1: this.gameInc.player_id1,
                      player_id2: this.gameInc.player_id2,
                      game_total_player1: this.score1,
                      game_total_player2: this.score2,
                      score_set1_player1: this.scoresImm.pl1[0],
                      score_set1_player2: this.scoresImm.pl2[0],
                      score_set2_player1: this.scoresImm.pl1[1],
                      score_set2_player2: this.scoresImm.pl2[1],
                      score_set3_player1: this.scoresImm.pl1[2],
                      score_set3_player2: this.scoresImm.pl2[2],
                      score_set4_player1: this.scoresImm.pl1[3],
                      score_set4_player2: this.scoresImm.pl2[3],
                      score_set5_player1: this.scoresImm.pl1[4],
                      score_set5_player2: this.scoresImm.pl2[4],          
                      done: false,                               
                    };
            return data;
        },
        validateScr(){
            console.log("first: " + this.scoresImm.pl1.length)
            console.log("second: " + this.scoresImm.pl2[0])
            if(this.scoresImm.pl1.length < 3 && this.scoresImm.pl2.length < 3){
               console.log("lenght < 3")
               this.openSnackbar("Неверно заполнены поля")
              
            } else {
                for(var i = 0; i < this.scoresImm.pl1.length; i++){                    
                    if(this.score1 === 3 || this.score2 === 3){
                        this.openSnackbar("Неверный счет для сета " + (i+1))
                        this.checkbox = false                        
                        break;
                    } else{
                        if(this.scoresImm.pl1[i] && this.scoresImm.pl2[i]){
                            console.log("Good lin: " + (i+1))
                            if(this.scoresImm.pl1[i] > 11 || this.scoresImm.pl2[i] > 11){
                                if(Math.abs(this.scoresImm.pl1[i]-this.scoresImm.pl2[i]) == 2){
                                    console.log("OK1")
                                    this.checkbox = true
                                    this.scoreCalc(this.scoresImm.pl1[i],this.scoresImm.pl2[i])
                                } else {
                                this.openSnackbar("Разница мячей должна быть равна 2 для сета " + (i+1))
                                this.checkbox = false  
                                break     
                                }
                            } else {
                                if(this.scoresImm.pl1[i] == 11 || this.scoresImm.pl2[i] == 11){
                                    if(Math.abs(this.scoresImm.pl1[i]-this.scoresImm.pl2[i]) > 1){
                                        console.log("OK2")
                                        this.checkbox = true
                                        this.scoreCalc(this.scoresImm.pl1[i],this.scoresImm.pl2[i])
                                    } else {
                                    this.openSnackbar("Разница мячей меньше 2 для сета " + (i+1))
                                    this.checkbox = false  
                                    break     
                                    }
                                } else {
                                    this.openSnackbar("Неверный счет для сета " + (i+1))
                                   this.checkbox = false  
                                    break
                                }
                            }
                        }else {

                            this.openSnackbar("Неверно заполнены поля для сета " + (i+1))
                            this.checkbox = false  
                        }
                    }
                } 
            }         
        },        
        scoreCalc(v1,v2) {
               if(Math.abs(this.score1-this.score2) < 3){                   
                   if(v1<v2){
                       console.log("v1 "+ v1)
                       console.log("v2 "+ v2)
                       this.score2++
                   } else {
                       console.log("v2 "+ v2)
                       console.log("v1 "+ v1)
                       this.score1++
                   }        
               }
        },
        openSnackbar(valueS) {
            showSnackbar(valueS);
        },      
    }
}
</script>
<style scoped>

</style>