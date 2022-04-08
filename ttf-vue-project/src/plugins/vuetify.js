import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    iconfont: 'md',
    theme: {
        themes:{
            light: {
                primary: '#fff3e0',
                secondary: "#cfd8dc",
                success: '#3cd1c2',
                info: '#ffaa2c',
                error: '#f83e70'
            }
        }
        
    }
});
