drop table t_personne  IF EXISTS ;

CREATE TABLE t_personne (
  PK_PERS int IDENTITY,
  Prenom varchar(100) ,
  Nom varchar(100),
  Date_naissance date,
  No_rue int ,
  Rue varchar(100),
  NPA int,
  Ville varchar(100) ,
  Actif boolean ,
  Salaire decimal(12,2) ,
  Date_modif  timestamp,
  No_modif int);
  
  INSERT INTO t_personne VALUES (1,'Max','Dupond','1968-02-29',444,'Pérolles',1701,'Fribourg',false,'25000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (2,'Bernard','Haller','1943-05-13',14,'Rte du Centre',1724,'Marly',true,'100000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (3,'Alexia','Baurer','1990-01-01',12,'Ch. de la Gare',1753,'Matran',true,'75000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (4,'Louis','Rossier','1932-07-31',25,'Rte des Murailles',1754,'Avry',true,'45000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (5,'André','Magnin','1988-08-08',65,'Les Dailles',1752,'Villars-sur-Glâne',false,'47500.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (6,'Edouard','Gumy','1972-12-01',23,'Gorges du Gotéron',1700,'Fribourg',false,'52134.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (9,'Jacques-André','de la Patelière','1958-10-15',1,'Rue du château',1700,'Fribourg',true,'125110.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (11,'Chen','Talow','1972-02-29',1,'School Road',87363,'New Dehli',true,'12000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (12,'Mike','Gandi','1966-06-06',236,'Bourbon Street',87456,'New Nehli',true,'14500.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (13,'Robert','Moret','1966-03-03',5,'La Jorettaz',1663,'Gruyère',true,'39000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (14,'Gabrielle','Vuarnoz','1955-05-05',12,'Gai-Logis',1745,'Lentigny',false,'63000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (15,'Francis','Fragnière','1969-02-12',1,'Le Buth',1669,'Lessoc',false,'41156.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (16,'Laurence','Toffel','1980-03-21',35,'Wilerweg',3280,'Morat',true,'57300.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES  (17,'Henri','Marmier','1976-10-13',8,'Rue du château',1530,'Payerne',false,'43050.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (18,'Christel','Charrière','2019-03-14',2,'Pl. de la Perraisa',1680,'Romont',true,'23000.00','2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (19,'Thomas','Weibel','1996-06-30',6,'Ober Tasberg',1717,'St. Ursen',false,NULL,'2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (20,'Frédéric','Bielmann','2000-01-01',35,'Le Chêne',1733,'Treyvaux',false,NULL,'2015-09-01 00:00:00',0);
  INSERT INTO t_personne VALUES (21,'Urs','Lehmann','2008-08-08',3,'Bernertor',3200,'Murten',false,NULL,'2015-09-01 00:00:00',0);

