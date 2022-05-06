CREATE TABLE IF NOT EXISTS seasons (
  id INT NOT NULL AUTO_INCREMENT,
  nbr_players INT,
  nbr_leagues INT,
  nbr_teams INT,
  nbr_games INT,
  nbr_weeks INT,
  win_score INT,
  win_score3 INT,
  lost_score2 INT,
  start_date DATETIME,
  end_date DATETIME,
  start_status TINYINT NOT NULL,
  end_status TINYINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO seasons (id, nbr_players, nbr_leagues, nbr_teams,nbr_games,nbr_weeks,win_score,win_score3,lost_score2,start_date,end_date,start_status, end_status)
VALUES (1, null,null,null,null,NULL,3,2,1, NULL, NULL,0, 0);


