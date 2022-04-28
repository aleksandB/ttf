CREATE TABLE IF NOT EXISTS teams (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO teams (id, name)
VALUES (1, 'Default');

CREATE TABLE IF NOT EXISTS leagues (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO leagues (id, name)
VALUES (1, 'Premier');

CREATE TABLE IF NOT EXISTS players (
  id INT NOT NULL AUTO_INCREMENT,
  parent_id INT,
  user_id BIGINT NOT NULL,
  team_id INT NOT NULL,
  league_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(team_id) REFERENCES teams(id),
  FOREIGN KEY(league_id) REFERENCES leagues(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
