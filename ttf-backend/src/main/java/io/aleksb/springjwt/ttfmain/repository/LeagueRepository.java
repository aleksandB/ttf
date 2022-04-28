package io.aleksb.springjwt.ttfmain.repository;

import io.aleksb.springjwt.ttfmain.models.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Integer> {
}
