-- You can use this file to load seed data into the database using SQL statements
-- Localidades
insert into pais values (1, 55, 'BRASIL', 'BR');

insert into estado values (16, 1, 'PARANÁ', 'PR', 1);
insert into estado values (21, 1, 'RIO GRANDE DO SUL', 'RS', 1);
insert into estado values (24, 1, 'SANTA CATARINA', 'SC', 1);
insert into estado values (25, 1, 'SÃO PAULO', 'SP', 1);

insert into municipio values (2853, 16, 'CURITIBA', 16);
insert into municipio values (2854, 16, 'SÃO JOSÉ DOS PINHAIS', 16);
insert into municipio values (2855, 25, 'SÃO PAULO', 25);

-- Áreas do Direito
insert into areaatuacao values ('1', 'Ambiental');
insert into areaatuacao values ('2', 'Cível');
insert into areaatuacao values ('3', 'Consumidor');
insert into areaatuacao values ('4', 'Importação e Exportação');

INSERT INTO setor (uid, nome) VALUES ('215cae92-a3eb-4a5d-a3a9-3b2b239700c1', 'Diretoria');
INSERT INTO setor (uid, nome) VALUES ('f2b2c7fa-641c-43b3-9caa-3e01746af7c7', 'Financeiro');
INSERT INTO empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('53c68bd8-aa4c-4f84-a687-013549106e5b', '12345678910112', 'Lawyer ADV', 'Lawyer ADV');
INSERT INTO empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('0a91a8b8-0d49-4638-9dd4-2a3c6ff17409', '32165498745632', 'Esparta Software', 'Esparta SW');
INSERT INTO empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('7231a544-80e1-45c0-aa5a-79a0847e2ead', '32559874512654', 'Vernalha', 'VR');

INSERT INTO pessoa (uid, identificacao, tipoidentificacao, nome, empresa_uid) VALUES ('22807067-9770-4ab6-b631-40d08eacfbac', '04330357909', 0, 'Deividi Cavarzan', '0a91a8b8-0d49-4638-9dd4-2a3c6ff17409');
INSERT INTO pessoa (uid, identificacao, tipoidentificacao, nome, empresa_uid) VALUES ('42117a72-fee2-46fc-ab2a-fc624b0c3ffb', '5452826963', 1, 'Ronaldo Campos', '53c68bd8-aa4c-4f84-a687-013549106e5b');
INSERT INTO pessoa_emails (pessoa_uid, value, principal) VALUES ('42117a72-fee2-46fc-ab2a-fc624b0c3ffb', 'ronaldocwb@gmail.com', true);
INSERT INTO pessoa_emails (pessoa_uid, value, principal) VALUES ('42117a72-fee2-46fc-ab2a-fc624b0c3ffb', 'beeeividi@gmail.com', true);

INSERT INTO cliente (uid, nome) VALUES ('1', 'Saul Law Corp.');
-- Usuário padrao e permissões
INSERT INTO usuario (uid, ativo, email, senha, cliente_uid, pessoa_uid) VALUES ('1', 1, 'developer@lawyer.com.br', '88653165aa4afc1571802bbfe61e27b2daa38c626ea01fbed35413aadb635723', '1', '42117a72-fee2-46fc-ab2a-fc624b0c3ffb');

INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'ADMIN');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'MANAGER');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'LAWYER');
INSERT INTO usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'USER');

INSERT INTO advogado (uid, numerooab, pessoa_uid) VALUES ('22807067-9770-4ab6-b631-40d08eacfxxx', '123456789', '22807067-9770-4ab6-b631-40d08eacfbac');
INSERT INTO advogado (uid, numerooab, pessoa_uid) VALUES ('42117a72-fee2-46fc-ab2a-fc624b0c3zzz', '98765432', '42117a72-fee2-46fc-ab2a-fc624b0c3ffb');



