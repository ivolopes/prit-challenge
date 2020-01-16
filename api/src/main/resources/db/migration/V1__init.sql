--Usuario

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(100) NOT NULL unique,
  password VARCHAR(250) NOT NULL,
  active INT NOT NULL
);

INSERT INTO user (name, email, password, active) VALUES
  ('Ivo', 'ivolopes.o@outlook.com', '$2a$10$ukLREsh/0nzoKFq/zAbqX.Pn161RMJcVV3QH7MORwaNmQzuBENEai',1);

--Produto

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(150) NOT NULL,
  price DOUBLE NOT NULL,
  active INT NOT NULL
);