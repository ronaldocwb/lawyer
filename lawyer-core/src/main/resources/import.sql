-- You can use this file to load seed data into the database using SQL statements

-- Localidades
insert into vkb_pais values (1, 'BRASIL', 'BR', 55);

insert into vkb_estado values (16, 1, 'PARANÁ', 'PR');
insert into vkb_estado values (21, 1, 'RIO GRANDE DO SUL', 'RS');
insert into vkb_estado values (24, 1, 'SANTA CATARINA', 'SC');
insert into vkb_estado values (25, 1, 'SÃO PAULO', 'SP');

insert into vkb_municipio values (2853, 16, 'CURITIBA');

-- Usuário padrao e permissões
insert into usuario values ('1', 'developer@lawyer.com.br', '123')

-- Áreas do Direito
insert into areaatuacao values ('1', 'Ambiental') 
insert into areaatuacao values ('2', 'Cível') 
insert into areaatuacao values ('3', 'Consumidor') 
insert into areaatuacao values ('4', 'Importação e Exportação') 

INSERT INTO public.usuario (uid, email, senha) VALUES ('1', 'developer@lawyer.com.br', '88653165aa4afc1571802bbfe61e27b2daa38c626ea01fbed35413aadb635723');
INSERT INTO public.usuario_permissoes (usuario_uid, value) VALUES ('1', 'MANAGER');

