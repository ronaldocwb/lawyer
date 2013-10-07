-- You can use this file to load seed data into the database using SQL statements
-- Localidades
insert into pais values (1, 55, 'BRASIL', 'BR');

insert into estado values (16, 1, 'PARANÁ', 'PR', 1);
insert into estado values (21, 1, 'RIO GRANDE DO SUL', 'RS', 1);
insert into estado values (24, 1, 'SANTA CATARINA', 'SC', 1);
insert into estado values (25, 1, 'SÃO PAULO', 'SP', 1);

insert into municipio values (2853, 16, 'CURITIBA', 16);

-- Usuário padrao e permissões
INSERT INTO usuario (uid, email, senha) VALUES ('1', 'developer@lawyer.com.br', '88653165aa4afc1571802bbfe61e27b2daa38c626ea01fbed35413aadb635723');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'ADMIN');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'MANAGER');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'LAWYER');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'USER');

-- Áreas do Direito
insert into areaatuacao values ('1', 'Ambiental');
insert into areaatuacao values ('2', 'Cível');
insert into areaatuacao values ('3', 'Consumidor'); 
insert into areaatuacao values ('4', 'Importação e Exportação');


INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('53c68bd8-aa4c-4f84-a687-013549106e5b', '12345678910112', 'Lawyer ADV', 'Lawyer ADV');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('0a91a8b8-0d49-4638-9dd4-2a3c6ff17409', '32165498745632', 'Esparta Software', 'Esparta SW');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('7231a544-80e1-45c0-aa5a-79a0847e2ead', '32559874512654', 'Vernalha', 'VR');

