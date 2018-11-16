CREATE TABLE `APIDB`.`DescuentosCombos` (
  `descuentosCombosId` INT NOT NULL AUTO_INCREMENT,
  `comboId` INT NOT NULL,
  `descuentoId` INT NOT NULL,
  PRIMARY KEY (`descuentosCombosId`),
  UNIQUE INDEX `descuentosCombosId_UNIQUE` (`descuentosCombosId` ASC),
  UNIQUE INDEX `comboId_UNIQUE` (`comboId` ASC),
  UNIQUE INDEX `descuentoId_UNIQUE` (`descuentoId` ASC),
  INDEX `fk_DescuentosCombos_2_idx` (`comboId` ASC, `descuentoId` ASC),
  CONSTRAINT `fk_DescuentosCombos_1`
    FOREIGN KEY ()
    REFERENCES `APIDB`.`Descuentos` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DescuentosCombos_2`
    FOREIGN KEY (`comboId` , `descuentoId`)
    REFERENCES `APIDB`.`Descuentos` (`descuentoId` , `descuentoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

