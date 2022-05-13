package io.aleksb.springjwt.ttfmain.controllers;

import io.aleksb.springjwt.auth.repository.UserRepository;
import io.aleksb.springjwt.ttfmain.models.EStatus;
import io.aleksb.springjwt.ttfmain.models.Player;
import io.aleksb.springjwt.ttfmain.payload.request.AddNewMemberRequest;
import io.aleksb.springjwt.ttfmain.payload.response.NewMemberResponse;
import io.aleksb.springjwt.ttfmain.payload.response.PlayersInfoResponse;
import io.aleksb.springjwt.ttfmain.repository.PlayerRepository;
import io.aleksb.springjwt.ttfmain.repository.SeasonRepository;
import io.aleksb.springjwt.ttfmain.services.AddNewMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/game")
public class TTFController {
    private static final Logger logger = LoggerFactory.getLogger(TTFController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    AddNewMemberService addNewMemberService;

    //Registration new player to new season
  @PostMapping("/addnewmember")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<?> addNewMember(@RequestBody AddNewMemberRequest addNewMemberRequest){
        logger.info("User id is : " + addNewMemberRequest.getUserId());
        NewMemberResponse newMemberResponse = addNewMemberService.getPlayerId(addNewMemberRequest.getUserId());
        logger.info("Player id is : " + newMemberResponse.getPlayerId());
        logger.info(newMemberResponse.getMessage());
        if(newMemberResponse.getPlayerId() > 0){
            return new ResponseEntity<>(newMemberResponse, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(newMemberResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getplayers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PlayersInfoResponse>> getPlayersInfo(){
      List<Player> players = new ArrayList<>();
      playerRepository.findByStatus(EStatus.FUTURE).forEach(players::add);
      List<PlayersInfoResponse> playersInfoResponses= new ArrayList<>();
      playerRepository.findByStatus(EStatus.FUTURE).forEach(entity ->{
          playersInfoResponses.add(new PlayersInfoResponse(entity.getId(),entity.getUser().getUsername(),entity.getLeague().getId()));
      });
      if (playersInfoResponses.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
          return new ResponseEntity<>(playersInfoResponses, HttpStatus.OK);
      }



    }



}
