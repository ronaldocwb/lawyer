-- You can use this file to load seed data into the database using SQL statements

-- Localidades
insert into vkb_pais values (1, 'BRASIL', 'BR', 55);

insert into vkb_estado values (16, 1, 'PARANÁ', 'PR');
insert into vkb_estado values (21, 1, 'RIO GRANDE DO SUL', 'RS');
insert into vkb_estado values (24, 1, 'SANTA CATARINA', 'SC');
insert into vkb_estado values (25, 1, 'SÃO PAULO', 'SP');

insert into vkb_municipio values (2853, 16, 'CURITIBA');

-- Usuário padrao e permissões
INSERT INTO public.usuario (uid, email, senha) VALUES ('1', 'developer@lawyer.com.br', '88653165aa4afc1571802bbfe61e27b2daa38c626ea01fbed35413aadb635723');
INSERT INTO public.usuario_permissoes (usuario_uid, permissoes) VALUES ('1', 'MANAGER');

-- Áreas do Direito
insert into areaatuacao values ('1', 'Ambiental') 
insert into areaatuacao values ('2', 'Cível') 
insert into areaatuacao values ('3', 'Consumidor') 
insert into areaatuacao values ('4', 'Importação e Exportação') 


INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('53c68bd8-aa4c-4f84-a687-013549106e5b', null, null, '3e3rr3');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('0a91a8b8-0d49-4638-9dd4-2a3c6ff17409', null, null, '1');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('7231a544-80e1-45c0-aa5a-79a0847e2ead', null, null, '2');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('16676029-bc0a-4aee-8be1-5cb4a9f500a8', null, null, '3');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('ad242971-b796-4a3c-8055-02f60b8673bf', null, null, '4');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('e8215aad-9cef-4d78-99bf-3703fe3212f8', null, null, '5');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('4e71f8e8-b679-44f7-a8a5-8daa19a7b9ce', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('892f871a-ed0f-4535-9fdc-04cb48eb57fc', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('1bf0fb7a-4180-4fd0-91ff-b3f47c1eadd8', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('9464ef62-f46c-4452-969d-2a2b9a4c528e', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('c7440f27-aa59-4e0a-88c9-31e52f9f9c57', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('16a09ed2-8118-4ae0-af49-1294241c7a4e', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('3fcca05b-9c08-409b-99c3-3f143884977d', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('3d2ea428-cb5f-4897-b741-9ec7523582f8', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('8595d053-c561-40aa-ba74-f55ed06649b0', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('2918f706-5f0a-49b0-b577-00bec5b39b75', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('232fc4d4-ed6f-4da1-b797-a13582e5381a', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('81ebf3ef-3a12-49ae-8f6a-efb185b8224b', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('b2045314-e95f-4595-a269-49c990818670', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('25e2d8ed-2853-41bf-aba0-1cae2966bbbd', null, null, '6');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('9e67bef3-b15b-4489-b9df-6a313d993541', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('df835fbd-f0aa-4947-9aa1-3c28f4408412', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('b1fa8532-27b4-40c0-82b6-93240d008136', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('1e6b82f8-5afb-4bd3-b1d6-f1090cfa7915', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('5f40fd5d-4568-4f89-8b91-d14eba46b272', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('c8a9bf5b-6b90-423a-ae73-1645121570fd', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('5e49c213-2ba0-4420-98e0-f145a295de36', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('656ddf00-da87-4c05-acc5-aad7996a22eb', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('5608d4bf-1d2c-4c6a-901a-f6c88506cce0', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('b4364309-aa9a-4221-a64f-43df3fe946cb', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('e223f205-caf0-42c2-9d5f-518e1df78da1', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('79c75e47-1b3d-4286-b9ab-4231b78d07d2', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('9d13d013-afff-49a3-9028-1c85e35c5eeb', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('172936f4-b57a-4275-8dbf-d11d17446a39', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('661e63c9-4f58-4686-8cd7-2358c5580431', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('c1f79827-2c45-4e77-a833-e16addcf49df', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('98396c08-5780-4a51-8d1f-9f557b11a06e', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('e9029a10-e72d-426a-bfe3-f92b6013aaa6', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('20f2d1eb-5f2c-493a-88e8-c11c8ced8a54', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('ba3e26f1-2795-4c23-995b-5386061d24c1', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('e689461d-0acd-4f23-9438-adec9a28d252', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('d9f367c3-a1fe-481f-807d-2a36e9b690d2', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('50daba77-79f2-4d78-bd94-047a9f538270', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('d2eed104-09f7-406d-b7d0-01ad2f064092', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('1ed8f1eb-16ec-43f2-b1e3-78160abb23ab', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('473c15a3-cd72-4048-a928-b6f1885f7d68', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('27ce18fc-26b7-4972-867f-0c70edee6ca6', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('77a1e877-6dc2-4e54-8025-6b8f651ab4bb', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('4f5acbf9-9b5a-4959-9e00-1308db864ed1', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('12c9b1df-568c-4398-8f73-80f27214f2d8', null, null, '21122121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('f2bcb27a-f36b-4692-8c92-9198438ca57d', null, null, '12212121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('37c36175-7c4f-4d23-94e1-9b9ff5d0994f', null, null, '12212121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('26a6ba3d-ad08-4365-8f42-b88ca9d92f06', null, null, '12212121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('5af70c56-f3c5-47b7-8343-8594a8310023', null, null, '12212121');
INSERT INTO public.empresa (uid, cnpj, nomefantasia, razaosocial) VALUES ('8641b96d-74fb-419f-bb21-43bf3cfb50bd', null, null, '12212121');

