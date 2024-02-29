DROP database IF EXISTS ciclismo;
CREATE database ciclismo;
USE ciclismo;

create table ciclista(
  dorsal int,
  nombre varchar(50),
  edad int,
  nomeq varchar(20))ENGINE=InnoDB;

create table equipo(
  nomeq varchar(20),
  director varchar(50))ENGINE=InnoDB;

create table llevar(
  dorsal int,
  netapa int,
  codigo varchar(3))ENGINE=InnoDB;

create table etapa(
  netapa int,
  km     int,
  salida varchar(20),
  llegada varchar(20),
  dorsal int)ENGINE=InnoDB;

create table puerto(
  nompuerto varchar(20),
  altura int,
  categoria varchar(1),
  pendiente int,
  netapa int,
  dorsal int)ENGINE=InnoDB;

create table maillot(
  codigo varchar(3),
  tipo   varchar(20),
  color varchar(20),
  premio int)ENGINE=InnoDB;





ALTER TABLE ciclista ADD PRIMARY KEY(dorsal);
ALTER TABLE etapa ADD PRIMARY KEY(netapa);
ALTER TABLE maillot ADD PRIMARY KEY(codigo);
ALTER TABLE puerto ADD PRIMARY KEY(nompuerto);
ALTER TABLE equipo ADD PRIMARY KEY(nomeq);
ALTER TABLE llevar ADD PRIMARY KEY (dorsal,netapa,codigo);





ALTER TABLE ciclista
  ADD CONSTRAINT Cl_1 FOREIGN KEY (nomeq) REFERENCES equipo(nomeq);


ALTER TABLE puerto
  ADD CONSTRAINT Cl_2 FOREIGN KEY (netapa) REFERENCES etapa(netapa);

ALTER TABLE puerto
  ADD CONSTRAINT Cl_3 FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal);

ALTER TABLE llevar
  ADD CONSTRAINT Cl_4 FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal);

ALTER TABLE llevar
  ADD CONSTRAINT Cl_5 FOREIGN KEY (netapa) REFERENCES etapa(netapa);

ALTER TABLE llevar
  ADD CONSTRAINT Cl_6 FOREIGN KEY (codigo) REFERENCES maillot(codigo);


ALTER TABLE etapa
  ADD CONSTRAINT Cl_7 FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal);








insert into equipo values('Amore Vita','Ricardo Padacci');
insert into equipo values('Banesto','Miguel Echevarria');
insert into equipo values('Bresciali-Refin','Pietro Armani');
insert into equipo values('Carrera','Luigi Petroni');
insert into equipo values('Gatorade','Gian Luca Pacceli');
insert into equipo values('Kelme','Alvaro Pino');
insert into equipo values('Mapei-Clas','Juan Fernandez');
insert into equipo values('Navigare','Lorenzo Sciacci');
insert into equipo values('Telecom','Morgan Reikacrd');
insert into equipo values('TVM','Steevens Henk');








insert into ciclista values(1,'Miguel Indurain',21,'Banesto');
insert into ciclista values(2,'Pedro Delgado',29,'Banesto');
insert into ciclista values(3,'Alex Zulle',20,'Navigare');
insert into ciclista values(4,'Alessio Di Basco',30,'TVM');
insert into ciclista values(5,'Armand',17,'Amore Vita');
insert into ciclista values(8,'Jean Van Poppel',24,'Bresciali-Refin');
insert into ciclista values(9,'Maximo Podel',17,'Telecom');
insert into ciclista values(10,'Mario Cipollini',31,'Carrera');
insert into ciclista values(11,'Eddy Seigneur',20,'Amore Vita');
insert into ciclista values(12,'Alessio Di Basco',34,'Bresciali-Refin');
insert into ciclista values(13,'Gianni Bugno',24,'Gatorade');
insert into ciclista values(15,'Jesus Montoya',25,'Amore Vita');
insert into ciclista values(16,'Dimitri Konishev',27,'Amore Vita');
insert into ciclista values(17,'Bruno Lealli',30,'Amore Vita');
insert into ciclista values(20,'Alfonso Gutiérrez',27,'Navigare');
insert into ciclista values(22,'Giorgio Furlan',22,'Kelme');
insert into ciclista values(26,'Mikel Zarrabeitia',30,'Carrera');
insert into ciclista values(27,'Laurent Jalabert',22,'Banesto');
insert into ciclista values(30,'Melchor Mauri',26,'Mapei-Clas');
insert into ciclista values(31,'Per Pedersen',33,'Banesto');
insert into ciclista values(32,'Tony Rominger',31,'Kelme');
insert into ciclista values(33,'Stefenao della Sveitia',26,'Amore Vita');
insert into ciclista values(34,'Clauido Chiapucci',23,'Amore Vita');
insert into ciclista values(35,'Gian Mateo Faluca',34,'TVM');



insert into etapa values(1,70,'Salamanca','Zamora',1);
insert into etapa values(2,184,'Calahorra','Burgos',1);
insert into etapa values(3,150,'Zamora','Almendralejo',10);
insert into etapa values(4,330,'Córdoba','Granada',3);
insert into etapa values(5,150,'Granada','Almeria',9);


insert into puerto values('Alto Pradilla',1240,'2',7,2,3);
insert into puerto values('Alto Valmala',1190,'2',8,2,3);
insert into puerto values('Cruz de la demanda',1850,'E',17,4,2);
insert into puerto values('Arcalis',2230,'E',17,4,2);
insert into puerto values('Coll de Ordino',1980,'2',17,4,3);
insert into puerto values('Puerto de Morcura',2500,'E',17,1,1);
insert into puerto values('Cerler',2500,'E',23,1,1);
insert into puerto values('Sierra Nevada',2400,'1',3,5,9);




insert into maillot values('MGE','General','Amarillo',1000000);
insert into maillot values('MMO','Montaña','Blanco y rojo',500000);
insert into maillot values('MMV','Metas volantes','Rojo',400000);
insert into maillot values('MRE','Regularidad','Verde',300000);
insert into maillot values('MSE','Sprint especial','Rosa',300000);




insert into llevar values(1,3,'MGE');
insert into llevar values(1,4,'MGE');
insert into llevar values(2,2,'MGE');
insert into llevar values(3,1,'MGE');
insert into llevar values(3,1,'MMV');
insert into llevar values(3,4,'MRE');
insert into llevar values(4,1,'MMO');









