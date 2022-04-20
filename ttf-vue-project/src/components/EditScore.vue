<template>    
    <v-dialog max-width="600px" v-model="show">        
        <v-card>
            <v-card-title>
                <h2>Edit form</h2>
            </v-card-title>
            <v-card-text>
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
                          <div class="">
                            {{ scoreCalc.score1 }}
                          </div>
                          <div class="">
                            {{ scoreCalc.score2 }}
                          </div>                
                      </div>
                      <div class="table__round_ d-flex flex-column ma-5 my-auto">
                            <h3>Score</h3>
                          <div class="table__round d-flex flex-row ma-5 my-auto">
                          <div 
                          class="table__round d-flex flex-column my-auto"
                          v-for="index in 5"
                          :key="index"
                          >
                              <div class="pa-1 table__round2">
                                <v-text-field
                                    class="text__field"               
                                    type="number"
                                    name="name"                              
                                    outlined
                                    dense
                                    style = "width: 60px;"                            
                                ></v-text-field>
                              </div>
                              <div class="pa-1 table__round2">
                                <v-text-field
                                    class="text__field"               
                                    type="number"
                                    name="player_id2s"                                
                                    outlined
                                    dense
                                    style = "width: 60px;"                            
                                ></v-text-field>
                              </div>
                          </div>                                               
                      </div>
                      </div>
                        </v-row>
                        <validation-provider
                            v-slot="{ errors }"
                            name="Name"
                            rules="required"
                        >
                            <v-text-field                                
                                label="Name" 
                                prepend-icon="mdi-folder"                               
                                type="text"
                                name="username2"
                                :error-messages="errors"
                                required                            
                            ></v-text-field>
                        </validation-provider>                        
                                         
                        <v-btn type="submit" class="form-group success rounded-pill" :loading="loading" :disabled ="invalid">Login in</v-btn>                    
                        <v-btn @click="clear" class="pa-2 ma-2 success rounded-pill">Clear</v-btn>
                        <v-btn color="primary" @click.stop="show=false">{{ gameInc.player_id2 }}</v-btn>
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
import { required, digits} from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'

setInteractionMode('eager')

  extend('digits', {
    ...digits,
    message: '{_field_} needs to be {length} digits. ({_value_})',
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
            scoreId1: [],
            scoreId2: []
        }
    },
    watch:{

    },
    computed: {        
        show: {
            get(){
                //console.log("this point:"+ this.value)
                
                return this.value
            },
            set(value) {
                this.$emit('input', value)
            }
        },
        scoreCalc(){
            let score1 = 0;
            let score2 = 0;            
            
            
            return {
                score1,
                score2
            }
        }
    },
    created() {
        console.log("this point:" + this.value)
       
        
    },
    methods: {
        submit(){
            console.log("submit")
            this.$emit('update:gameInc', this.setData())
            this.show = false
            console.log(this.name1_player1)
        },
        clear() {
                   
            this.$refs.observer.reset();
            //this.message='';
            console.log("clear")
        },
        setData() {
               let data = {                
                      game_id: this.gameInc.game_id,
                      player_id1: this.gameInc.player_id1,
                      player_id2: this.gameInc.player_id2,
                      game_total_player1: 3,
                      game_total_player2: 0,
                      score_set1_player1: 11,
                      score_set1_player2: 1,
                      score_set2_player1: 11,
                      score_set2_player2: 3,
                      score_set3_player1: 11,
                      score_set3_player2: 3,
                      score_set4_player1: null,
                      score_set4_player2: null,
                      score_set5_player1: null,
                      score_set5_player2: null,          
                      done: false,                               
                    };
            return data;
        }        
       
    }
}
</script>
<style scoped>
.text__field input[type='number'] {
    -moz-appearance:textfield;
}
.text__field input::-webkit-outer-spin-button,
.text__field input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>