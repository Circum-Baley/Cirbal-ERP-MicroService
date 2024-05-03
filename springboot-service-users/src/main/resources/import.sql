INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');







INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('gonzalo','$2a$10$zQ20OzvhvLmqSVB0u0HwCe90LReelKzHfVv1cNlhwaKuV0O/Y5Yby', true, 'John', 'Doe', 'john.doe@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('admin', '$2a$10$MUr.eNQRHJu8KLwZQzOBgOAYZl4gsckUPFptkuC4STAJTpWPIT0tm', true, 'Jane', 'Smith', 'jane.smith@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('jon_ston', '12345', true, 'Johnathan', 'Smithington', 'johnathan.smithington@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('Mar_ita', '12345', false, 'Maria', 'Riquelme', 'maria.rique@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('gon_ba', '12345', true, 'Gonzalo', 'Bahamondez', 'Gonzaba@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('j_ose', '12345', true, 'José', 'García', 'jose.g@email.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('andres','12345',true, 'Andres', 'Guzman','profesor@bolsadeideas.com');
INSERT INTO users (username, password, enable, name, lastname, email) VALUES ('nimda','12345',true, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO users_in_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (5, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (6, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (7, 1);
INSERT INTO users_in_roles (user_id, role_id) VALUES (8, 1);
