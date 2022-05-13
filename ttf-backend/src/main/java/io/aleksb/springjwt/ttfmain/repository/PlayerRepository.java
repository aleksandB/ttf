package io.aleksb.springjwt.ttfmain.repository;


import io.aleksb.springjwt.ttfmain.models.EStatus;
import io.aleksb.springjwt.ttfmain.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Optional<Player> findByUserIdAndStatus(Long userId, EStatus status);
    List<Player> findByStatus(EStatus status);
}
