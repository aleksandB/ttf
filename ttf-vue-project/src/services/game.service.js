import api from './api';
class GameService {
    addPlayer({userId}) {
        return api.post('/game/addnewmember',{
            userId
        })
        .then((response) => {
            console.log('Player Created')
            console.log(response.data.playerId)            
        });
    }
    
}
export default new GameService();