CREATE TABLE `APIDB`.`Usuarios` (
  `usuarioId` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `dni` BIGINT NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `domicilio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuarioId`));

CREATE TABLE `APIDB`.`Roles` (
  `rolId` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rolId`));


CREATE TABLE `APIDB`.`UsuarioRol` (
  `usuarioRolId` INT NOT NULL AUTO_INCREMENT,
  `rolId` INT NOT NULL,
  `usuarioId` INT NOT NULL,
  PRIMARY KEY (`usuarioRolId`),

  INDEX `fk_UsuarioRol_1_idx` (`rolId` ASC),
  INDEX `fk_UsuarioRol_2_idx` (`usuarioId` ASC),
  CONSTRAINT `fk_UsuarioRol_1`
    FOREIGN KEY (`rolId`)
    REFERENCES `APIDB`.`Roles` (`rolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  CONSTRAINT `fk_UsuarioRol_2`
    FOREIGN KEY (`usuarioId`)
    REFERENCES `APIDB`.`Usuarios` (`usuarioId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
