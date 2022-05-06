package io.aleksb.springjwt.ttfmain.repository;


import io.aleksb.springjwt.ttfmain.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Integer> {

    Optional<Player> findByUserId(Long userId);
}
