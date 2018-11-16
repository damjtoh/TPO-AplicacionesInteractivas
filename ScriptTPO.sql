CREATE TABLE `Descuentos` (
  `descuentoId` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioId` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `porcentaje` float DEFAULT NULL,
  `establecimientoCuit` int(11) unsigned NOT NULL,
  `activo` int(1) unsigned NOT NULL DEFAULT '1',
  `estaCombo` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`descuentoId`),
  UNIQUE KEY `descuentoId_UNIQUE` (`descuentoId`),
  KEY `fk_Descuentos_1_idx` (`establecimientoCuit`),
  CONSTRAINT `fk_Descuentos_1` FOREIGN KEY (`establecimientoCuit`) REFERENCES `establecimientos` (`cuit`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `DescuentosCombos` (
  `descuentosCombosId` int(11) NOT NULL AUTO_INCREMENT,
  `comboId` int(11) NOT NULL,
  `descuentoId` int(11) NOT NULL,
  PRIMARY KEY (`descuentosCombosId`),
  UNIQUE KEY `descuentosCombosId_UNIQUE` (`descuentosCombosId`),
  KEY `fk_DescuentosCombos_2_idx` (`comboId`,`descuentoId`),
  KEY `fk_DescuentosCombos_2_idx1` (`descuentoId`),
  CONSTRAINT `fk_DescuentosCombos_1` FOREIGN KEY (`comboId`) REFERENCES `Descuentos` (`descuentoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DescuentosCombos_2` FOREIGN KEY (`descuentoId`) REFERENCES `Descuentos` (`descuentoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `entrada` (
  `columna` varchar(10) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `id` int(11) NOT NULL,
  `ocupado` bit(1) NOT NULL,
  `VENTA_id` int(11) NOT NULL,
  `VENTA_TIPO_DE_PAGO_numero` int(11) NOT NULL,
  PRIMARY KEY (`columna`,`fila`,`VENTA_id`,`VENTA_TIPO_DE_PAGO_numero`),
  KEY `fk_entrada_VENTA1_idx` (`VENTA_id`,`VENTA_TIPO_DE_PAGO_numero`),
  CONSTRAINT `fk_entrada_VENTA1` FOREIGN KEY (`VENTA_id`, `VENTA_TIPO_DE_PAGO_numero`) REFERENCES `VENTA` (`id`, `TIPO_DE_PAGO_numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `entradas` (
  `id_funcion` int(11) unsigned NOT NULL,
  `columna` varchar(10) NOT NULL,
  `fila` varchar(10) NOT NULL,
  `ocupado` bit(1) NOT NULL DEFAULT b'0',
  `ventaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_funcion`,`columna`,`fila`),
  CONSTRAINT `entrada_funcion` FOREIGN KEY (`id_funcion`) REFERENCES `funciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `establecimientos` (
  `cuit` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit`),
  UNIQUE KEY `cuit_UNIQUE` (`cuit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `funciones` (
  `id` int(11) unsigned NOT NULL,
  `id_pelicula` int(11) unsigned NOT NULL,
  `nombre_sala` varchar(50) NOT NULL,
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `sala_funcion` (`cuit_establecimiento`,`nombre_sala`),
  KEY `pelicula_funcion_idx` (`id_pelicula`),
  CONSTRAINT `pelicula_funcion` FOREIGN KEY (`id_pelicula`) REFERENCES `peliculas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sala_funcion` FOREIGN KEY (`cuit_establecimiento`, `nombre_sala`) REFERENCES `salas` (`cuit_establecimiento`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`nombre`,`idioma`,`subtitulos`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Roles` (
  `rolId` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `salas` (
  `cuit_establecimiento` int(11) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `activa` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`cuit_establecimiento`,`nombre`),
  KEY `salas_asientos` (`nombre`,`cuit_establecimiento`),
  CONSTRAINT `establecimiento_sala` FOREIGN KEY (`cuit_establecimiento`) REFERENCES `establecimientos` (`cuit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `TIPO_DE_PAGO` (
  `numero` int(11) NOT NULL,
  `tipoDeTarjeta` varchar(30) NOT NULL,
  `fechaDeVencimiento` date NOT NULL,
  `codigoDeSeguridad` int(11) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `UsuarioRol` (
  `usuarioRolId` int(11) NOT NULL AUTO_INCREMENT,
  `rolId` int(11) NOT NULL,
  `usuarioId` int(11) NOT NULL,
  PRIMARY KEY (`usuarioRolId`),
  KEY `fk_UsuarioRol_1_idx` (`rolId`),
  KEY `fk_UsuarioRol_2_idx` (`usuarioId`),
  CONSTRAINT `fk_UsuarioRol_2` FOREIGN KEY (`usuarioId`) REFERENCES `Usuarios` (`usuarioId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UsuarioRol_1` FOREIGN KEY (`rolId`) REFERENCES `Roles` (`rolId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

CREATE TABLE `Usuarios` (
  `usuarioId` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `domicilio` varchar(45) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`usuarioId`),
  UNIQUE KEY `nombreUsuario_UNIQUE` (`nombreUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

CREATE TABLE `VENTA` (
  `id` int(11) NOT NULL,
  `fechaYHora` date NOT NULL,
  `numeroDeTarjeta` bigint(20) NOT NULL,
  `importe` double NOT NULL,
  `TIPO_DE_PAGO_numero` int(11) NOT NULL,
  PRIMARY KEY (`id`,`TIPO_DE_PAGO_numero`),
  KEY `fk_VENTA_TIPO_DE_PAGO_idx` (`TIPO_DE_PAGO_numero`),
  CONSTRAINT `fk_VENTA_TIPO_DE_PAGO` FOREIGN KEY (`TIPO_DE_PAGO_numero`) REFERENCES `TIPO_DE_PAGO` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
