package io.aleksb.springjwt.ttfmain.repository;

import io.aleksb.springjwt.ttfmain.models.EStatus;
import io.aleksb.springjwt.ttfmain.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findByStatus(EStatus eStatus);

}
