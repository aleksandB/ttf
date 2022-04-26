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


