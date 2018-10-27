CREATE TABLE `establecimientos` (
  `cuit` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`cuit`),
  UNIQUE KEY `cuit_UNIQUE` (`cuit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





CREATE TABLE `salas` (
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`cuit_establecimiento`,`nombre`),
  KEY `salas_asientos` (`nombre`,`cuit_establecimiento`),
  CONSTRAINT `establecimiento_sala` FOREIGN KEY (`cuit_establecimiento`) REFERENCES `establecimientos` (`cuit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `sala_asientos` (
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `nombre_sala` varchar(50) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `columna` varchar(10) NOT NULL,
  `nro_fila` int(11) NOT NULL,
  `nro_columna` int(11) NOT NULL,
  `usable` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit_establecimiento`,`nombre_sala`,`fila`,`columna`),
  KEY `salas_asientos` (`nombre_sala`,`cuit_establecimiento`),
  CONSTRAINT `salas_asientos` FOREIGN KEY (`nombre_sala`, `cuit_establecimiento`) REFERENCES `salas` (`nombre`, `cuit_establecimiento`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE `peliculas` (
  `id` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `director` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `duracion` int(11) NOT NULL,
  `idioma` varchar(50) NOT NULL,
  `subtitulos` bit(1) NOT NULL DEFAULT b'0',
  `calificacion` decimal(3,2) DEFAULT '0.00',
  `observaciones` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`nombre`,`idioma`,`subtitulos`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE `funciones` (
  `id` int(11) unsigned NOT NULL,
  `id_pelicula` int(11) unsigned NOT NULL,
  `nombre_sala` varchar(50) NOT NULL,
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `sala_funcion` (`cuit_establecimiento`,`nombre_sala`) /*!80000 INVISIBLE */,
  KEY `pelicula_funcion_idx` (`id_pelicula`) /*!80000 INVISIBLE */,
  CONSTRAINT `pelicula_funcion` FOREIGN KEY (`id_pelicula`) REFERENCES `peliculas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sala_funcion` FOREIGN KEY (`cuit_establecimiento`, `nombre_sala`) REFERENCES `salas` (`cuit_establecimiento`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `entradas` (
  `id_funcion` int(11) unsigned NOT NULL,
  `columna` varchar(10) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `ocupado` bit(1) NOT NULL DEFAULT b'0',
  `ventaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_funcion`,`columna`,`fila`),
  CONSTRAINT `entrada_funcion` FOREIGN KEY (`id_funcion`) REFERENCES `funciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
