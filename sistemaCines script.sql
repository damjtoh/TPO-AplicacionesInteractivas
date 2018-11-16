CREATE TABLE APIDB.`establecimientos` (
  `cuit` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit`),
  UNIQUE KEY `cuit_UNIQUE` (`cuit`)
) 
CREATE TABLE APIDB.`peliculas` (
  `id` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `director` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `duracion` int(11) NOT NULL,
  `idioma` varchar(50) NOT NULL,
  `subtitulos` bit(1) NOT NULL DEFAULT b'0',
  `calificacion` decimal(3,2) DEFAULT '0.00',
  `observaciones` varchar(100) DEFAULT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`nombre`,`idioma`,`subtitulos`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) 
CREATE TABLE APIDB.`salas` (
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit_establecimiento`,`nombre`),
  KEY `salas_asientos` (`nombre`,`cuit_establecimiento`),
  CONSTRAINT `establecimiento_sala` FOREIGN KEY (`cuit_establecimiento`) REFERENCES APIDB.`establecimientos` (`cuit`) ON DELETE CASCADE ON UPDATE CASCADE
) 
CREATE TABLE APIDB.`sala_asientos` (
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `nombre_sala` varchar(50) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `columna` varchar(10) NOT NULL,
  `nro_fila` int(11) NOT NULL,
  `nro_columna` int(11) NOT NULL,
  `usable` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit_establecimiento`,`nombre_sala`,`fila`,`columna`),
  KEY `salas_asientos` (`nombre_sala`,`cuit_establecimiento`),
  CONSTRAINT `salas_asientos` FOREIGN KEY (`nombre_sala`, `cuit_establecimiento`) REFERENCES APIDB.`salas` (`nombre`, `cuit_establecimiento`) ON DELETE CASCADE ON UPDATE CASCADE
) 
CREATE TABLE APIDB.`funciones` (
  `id` int(11) unsigned NOT NULL,
  `id_pelicula` int(11) unsigned NOT NULL,
  `nombre_sala` varchar(50) NOT NULL,
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `sala_funcion` (`cuit_establecimiento`,`nombre_sala`) /*!80000 INVISIBLE */,
  KEY `pelicula_funcion_idx` (`id_pelicula`) /*!80000 INVISIBLE */,
  CONSTRAINT `pelicula_funcion` FOREIGN KEY (`id_pelicula`) REFERENCES APIDB. `peliculas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sala_funcion` FOREIGN KEY (`cuit_establecimiento`, `nombre_sala`) REFERENCES APIDB.`salas` (`cuit_establecimiento`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) 
CREATE TABLE APIDB.`entradas` (
  `id_funcion` int(11) unsigned NOT NULL,
  `columna` varchar(10) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `ocupado` bit(1) NOT NULL DEFAULT b'0',
  `ventaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_funcion`,`columna`,`fila`),
  CONSTRAINT `entrada_funcion` FOREIGN KEY (`id_funcion`) REFERENCES APIDB.`funciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) 



INSERT INTO `APIDB`.`establecimientos`
(`cuit`,
`nombre`,
`direccion`,
`capacidad`,
`activa`)
VALUES
(0,
'init',
'thaStreet',
50,
0);



INSERT INTO `APIDB`.`salas`
(`cuit_establecimiento`,
`nombre`,
`activa`)
VALUES
(0,
'sala_1',
0);


INSERT INTO `APIDB`.`peliculas`
(`id`,
`nombre`,
`director`,
`genero`,
`duracion`,
`idioma`,
`subtitulos`,
`calificacion`,
`observaciones`,
`activa`)
VALUES
(0,
'predator',
'yo',
'none',
200,
'spanglish',
0,
0,
'imblind',
0);



INSERT INTO `APIDB`.`funciones`
(`id`,
`id_pelicula`,
`nombre_sala`,
`cuit_establecimiento`,
`fecha_hora`,
`valor`,
`activa`)
VALUES
(0,
0,
'sala_1',
0,
'2000-01-01',
100000,
0);

