CREATE TABLE IF NOT EXISTS refreshtoken (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20) NOT NULL,
  token varchar(50) NOT NULL,
  expiry_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (token),
  FOREIGN KEY(user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;