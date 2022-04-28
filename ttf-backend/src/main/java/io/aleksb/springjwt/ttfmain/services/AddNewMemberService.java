package io.aleksb.springjwt.ttfmain.services;

import io.aleksb.springjwt.auth.repository.UserRepository;
import io.aleksb.springjwt.ttfmain.exceptions.ResourceNotFoundException;
import io.aleksb.springjwt.ttfmain.models.Player;
import io.aleksb.springjwt.ttfmain.repository.GlobalRepository;
import io.aleksb.springjwt.ttfmain.repository.LeagueRepository;
import io.aleksb.springjwt.ttfmain.repository.PlayerRepository;
import io.aleksb.springjwt.ttfmain.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddNewMemberService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GlobalRepository global;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    public int getPlayerId(Long userId){
        Optional<Player> playersData = Optional.ofNullable(playerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + userId)));
        if(playersData.isPresent()){
            return playersData.get().getId();
        } else {
            return createPlayer(userId).getId();
        }
    }

    public Player createPlayer(Long userId){

        Player player = new Player(null,
                                userRepository.findById(userId).get(),
                                teamRepository.findById(1).get(),
                                leagueRepository.findById(1).get());
        playerRepository.save(player);
        return player;

    }



}
