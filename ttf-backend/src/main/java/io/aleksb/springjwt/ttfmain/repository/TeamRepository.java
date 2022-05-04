package io.aleksb.springjwt.ttfmain.repository;

import io.aleksb.springjwt.ttfmain.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository <Team, Integer> {

}
