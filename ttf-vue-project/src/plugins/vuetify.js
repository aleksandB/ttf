import Vue from 'vue';
import '@mdi/font/css/materialdesignicons.css';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
    iconfont: 'md',    
    theme: {
        themes:{
            light: {
                primary: '#FF7F50',
                secondary: "#008080",
                success: '#3cd1c2',
                info: '#ffaa2c',
                error: '#f83e70'
            }
        }
        
    },
    icons: {
        iconfont: 'mdi'
      },
});
