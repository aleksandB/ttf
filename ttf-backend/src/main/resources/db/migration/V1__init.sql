CREATE TABLE IF NOT EXISTS users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL,
  password varchar(120) NOT NULL,
  username varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (username, email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO roles (id, name) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_MODERATOR'),
	(3, 'ROLE_ADMIN');
CREATE TABLE IF NOT EXISTS user_roles (
  user_id bigint(20) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (user_id,role_id),
  CONSTRAINT FK_RoleId FOREIGN KEY (role_id) REFERENCES roles(id),
  CONSTRAINT FK_UserId FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


