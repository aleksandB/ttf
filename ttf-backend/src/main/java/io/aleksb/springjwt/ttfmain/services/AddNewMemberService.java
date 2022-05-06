package io.aleksb.springjwt.ttfmain.services;

import io.aleksb.springjwt.auth.repository.UserRepository;
import io.aleksb.springjwt.ttfmain.exceptions.ResourceNotFoundException;
import io.aleksb.springjwt.ttfmain.models.EStatus;
import io.aleksb.springjwt.ttfmain.models.Player;
import io.aleksb.springjwt.ttfmain.models.Season;
import io.aleksb.springjwt.ttfmain.payload.response.NewMemberResponse;
import io.aleksb.springjwt.ttfmain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Service
public class AddNewMemberService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    //Verify and create new player
    public NewMemberResponse getPlayerId(Long userId) {
        try {
            Optional<Player> playersData = playerRepository.findByUserId(userId);


            if (playersData.isPresent()) {
                return new NewMemberResponse(playersData.get().getId(), verifySeasonInfo(),"Player already registred to the next competition");
            } else {
                return new NewMemberResponse(createPlayer(userId).getId(), verifySeasonInfo(), "Player has been registred successfully");
            }
        } catch (Exception e) {
            StringWriter stringWriter= new StringWriter();
            PrintWriter printWriter= new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            return new NewMemberResponse(0,verifySeasonInfo(), "UserId not exists in DB" + stringWriter.toString());
        }
    }
    public Player createPlayer(Long userId){

        Player player = new Player(null,
                                userRepository.findById(userId).get(),
                                teamRepository.findById(1).get(),  // New player with temaId=1
                                leagueRepository.findById(1).get(),
                                seasonRepository.findById(1).get(),
                                EStatus.FUTURE);  // New player with leageId=1
        playerRepository.save(player);
        return player;

    }

    public boolean verifySeasonInfo(){
        //int actualSeasonNumber = globalRepository.findById(globalRepository.findAll().size()).get().getSeason();
        //List<Season> futureSeasons = seasonRepository.findByStatus(EStatus.FUTURE);
        //List<Season> seasonT = seasonRepository.findAll();
        //return seasonT.size();
       /* if(futureSeasons.size() > 0){
            return true;
        } else {
            return false;
        }*/
        return true;
    }

}
