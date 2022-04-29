import api from './api';
class GameService {
    addPlayer(userId) {
        return api.post('/game/addnewmember',{
            userId
        })        
    }
    
}
export default new GameService();