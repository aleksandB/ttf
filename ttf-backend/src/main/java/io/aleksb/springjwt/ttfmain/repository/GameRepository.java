package io.aleksb.springjwt.ttfmain.repository;

import io.aleksb.springjwt.ttfmain.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
