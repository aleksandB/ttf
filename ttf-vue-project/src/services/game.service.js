import api from './api';
class GameService {
    addPlayer(userId) {
        return api.post('/game/addnewmember',{
            userId
        })        
    }
    getInfoPlayers(){
        return api.get('/game/getplayers')
    }
    
}
export default new GameService();