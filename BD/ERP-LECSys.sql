-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema LECSys1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LECSys1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LECSys1` DEFAULT CHARACTER SET utf8 ;
USE `LECSys1` ;

-- -----------------------------------------------------
-- Table `LECSys1`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `apellido` VARCHAR(20) NOT NULL,
  `dni` VARCHAR(10) NOT NULL,
  `dirección` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `teléfono` VARCHAR(20) NOT NULL,
  `email` VARCHAR(40) NULL,
  PRIMARY KEY (`idPersona`),
  INDEX `dni` (`dni` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`empleados` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NOT NULL,
  `sueldo` INT NULL,
  `fechaIngreso` DATE NOT NULL,
  `estado` INT NOT NULL,
  `sector` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpleado`),
  INDEX `idPersona_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `idPersona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `LECSys1`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`curso` (
  `idCurso` INT NOT NULL AUTO_INCREMENT,
  `año` VARCHAR(15) NOT NULL,
  `nivel` VARCHAR(15) NOT NULL,
  `idProfesor` INT NULL,
  `estado` INT NOT NULL,
  `aula` INT NULL,
  PRIMARY KEY (`idCurso`),
  INDEX `idProfesor_idx` (`idProfesor` ASC) VISIBLE,
  CONSTRAINT `idProfesor`
    FOREIGN KEY (`idProfesor`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`grupoFamiliar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`grupoFamiliar` (
  `idGrupoFamiliar` INT NOT NULL AUTO_INCREMENT,
  `nombreFamilia` VARCHAR(40) NOT NULL,
  `integrantes` INT NOT NULL,
  `deuda` INT NULL DEFAULT 0,
  `estado` INT NOT NULL DEFAULT 1,
  `descuento` INT NULL,
  PRIMARY KEY (`idGrupoFamiliar`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`alumnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`alumnos` (
  `idAlumno` INT NOT NULL AUTO_INCREMENT,
  `idCurso` INT NOT NULL,
  `idPersona` INT NOT NULL,
  `fechaIngreso` DATE NOT NULL,
  `estado` INT NOT NULL,
  `idGrupoFamiliar` INT NULL,
  PRIMARY KEY (`idAlumno`),
  INDEX `idCurso_idx` (`idCurso` ASC) VISIBLE,
  INDEX `idGrupoFamiliar_idx` (`idGrupoFamiliar` ASC) VISIBLE,
  INDEX `idPersonas_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `idCurso`
    FOREIGN KEY (`idCurso`)
    REFERENCES `LECSys1`.`curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idGrupoFamiliar`
    FOREIGN KEY (`idGrupoFamiliar`)
    REFERENCES `LECSys1`.`grupoFamiliar` (`idGrupoFamiliar`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `idPersonas`
    FOREIGN KEY (`idPersona`)
    REFERENCES `LECSys1`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`estadísticas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`estadísticas` (
  `idEstadísticas` INT NOT NULL AUTO_INCREMENT,
  `mes` INT NOT NULL,
  `año` INT NOT NULL,
  `estudiantesActivos` INT NULL,
  `profesoresActivos` INT NULL,
  `ingresosMes` INT NULL,
  `sueldos` INT NULL,
  `compras` INT NULL,
  `servicios` INT NULL,
  `faltasEmpleados` INT NULL,
  `faltasAlumnos` INT NULL,
  PRIMARY KEY (`idEstadísticas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`cobros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`cobros` (
  `idCobros` INT NOT NULL AUTO_INCREMENT,
  `idGrupoFamiliar` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `concepto` VARCHAR(80) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `monto` INT NOT NULL,
  `factura` VARCHAR(20) NULL,
  `idEstadisticas` INT NULL,
  PRIMARY KEY (`idCobros`),
  INDEX `idGrupoFamiliar_idx` (`idGrupoFamiliar` ASC) VISIBLE,
  INDEX `estadisticas_idx` (`idEstadisticas` ASC) VISIBLE,
  CONSTRAINT `idGrupoFamilia`
    FOREIGN KEY (`idGrupoFamiliar`)
    REFERENCES `LECSys1`.`grupoFamiliar` (`idGrupoFamiliar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `estadisticas`
    FOREIGN KEY (`idEstadisticas`)
    REFERENCES `LECSys1`.`estadísticas` (`idEstadísticas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `contraseña` VARCHAR(90) NOT NULL,
  `nivelAcceso` INT NOT NULL,
  `estado` INT NOT NULL,
  `idEmpleado` INT NOT NULL,
  `cambioContraseña` INT NULL,
  PRIMARY KEY (`idUsuarios`),
  INDEX `empleado_idx` (`idEmpleado` ASC) VISIBLE,
  CONSTRAINT `empleado`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`faltas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`faltas` (
  `idFaltas` INT NOT NULL AUTO_INCREMENT,
  `idAlumnos` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `estado` INT NOT NULL,
  `idCurso` INT NOT NULL,
  PRIMARY KEY (`idFaltas`),
  INDEX `idAlumnos_idx` (`idAlumnos` ASC) VISIBLE,
  INDEX `idCurso_idx` (`idCurso` ASC) VISIBLE,
  CONSTRAINT `idAlumnos`
    FOREIGN KEY (`idAlumnos`)
    REFERENCES `LECSys1`.`alumnos` (`idAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idCursoFaltas`
    FOREIGN KEY (`idCurso`)
    REFERENCES `LECSys1`.`curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`proveedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`proveedores` (
  `idProveedores` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(90) NULL,
  `direccion` VARCHAR(90) NULL,
  `cuit` VARCHAR(20) NULL,
  `telefono` VARCHAR(20) NULL,
  `tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idProveedores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`pagos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`pagos` (
  `idPagos` INT NOT NULL AUTO_INCREMENT,
  `idEmpleados` INT NULL,
  `idProveedor` INT NULL,
  `idEstadisticas` INT NULL,
  `nombre` VARCHAR(40) NULL,
  `concepto` VARCHAR(80) NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `monto` INT NOT NULL,
  `factura` VARCHAR(20) NULL,
  `comentario` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `formaPago` VARCHAR(100) NULL,
  PRIMARY KEY (`idPagos`),
  INDEX `idProfesores_idx` (`idEmpleados` ASC) VISIBLE,
  INDEX `idEstadisticas_idx` (`idEstadisticas` ASC) VISIBLE,
  INDEX `idProveedor_idx` (`idProveedor` ASC) VISIBLE,
  CONSTRAINT `idEmpleado`
    FOREIGN KEY (`idEmpleados`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idEstadisticas`
    FOREIGN KEY (`idEstadisticas`)
    REFERENCES `LECSys1`.`estadísticas` (`idEstadísticas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idProveedor`
    FOREIGN KEY (`idProveedor`)
    REFERENCES `LECSys1`.`proveedores` (`idProveedores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`valorCuota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`valorCuota` (
  `idValorCuota` INT NOT NULL AUTO_INCREMENT,
  `idCurso` INT NOT NULL,
  `precio` INT NOT NULL,
  PRIMARY KEY (`idValorCuota`),
  INDEX `fk_valorCuota_curso1_idx` (`idCurso` ASC) VISIBLE,
  CONSTRAINT `idCurso1`
    FOREIGN KEY (`idCurso`)
    REFERENCES `LECSys1`.`curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`diasCursado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`diasCursado` (
  `idDíasCursado` INT NOT NULL AUTO_INCREMENT,
  `día` INT NOT NULL,
  `horario` INT NOT NULL,
  `duración` INT NOT NULL,
  `idCurso` INT NOT NULL,
  PRIMARY KEY (`idDíasCursado`),
  INDEX `idCursos_idx` (`idCurso` ASC) VISIBLE,
  CONSTRAINT `idCursos`
    FOREIGN KEY (`idCurso`)
    REFERENCES `LECSys1`.`curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`registroActividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`registroActividad` (
  `idRegistroActividad` INT NOT NULL AUTO_INCREMENT,
  `idUsuarios` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `acción` VARCHAR(80) NOT NULL,
  `modulo` VARCHAR(40) NOT NULL,
  `ip` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idRegistroActividad`),
  INDEX `idUsuarios_idx` (`idUsuarios` ASC) INVISIBLE,
  CONSTRAINT `idUsuario`
    FOREIGN KEY (`idUsuarios`)
    REFERENCES `LECSys1`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`examenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`examenes` (
  `idExamenes` INT NOT NULL AUTO_INCREMENT,
  `idAlumno` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `tipo` VARCHAR(25) NOT NULL,
  `nota` INT NOT NULL,
  `idProfesor` INT NOT NULL,
  `idCurso` INT NOT NULL,
  PRIMARY KEY (`idExamenes`),
  INDEX `alumno_idx` (`idAlumno` ASC) VISIBLE,
  INDEX `profesor_idx` (`idProfesor` ASC) VISIBLE,
  INDEX `curso_idx` (`idCurso` ASC) VISIBLE,
  CONSTRAINT `alumno`
    FOREIGN KEY (`idAlumno`)
    REFERENCES `LECSys1`.`alumnos` (`idAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `profesor`
    FOREIGN KEY (`idProfesor`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `curso`
    FOREIGN KEY (`idCurso`)
    REFERENCES `LECSys1`.`curso` (`idCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`insumos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`insumos` (
  `idinsumos` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`idinsumos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`compras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`compras` (
  `idCompras` INT NOT NULL AUTO_INCREMENT,
  `idInsumo` INT NULL,
  `idProveedor` INT NULL,
  `cantidad` INT NULL,
  `precio` INT NULL,
  `fecha` DATE NULL,
  `idAutoriza` INT NULL,
  PRIMARY KEY (`idCompras`),
  INDEX `idProveedor_idx` (`idProveedor` ASC) VISIBLE,
  INDEX `idInsumo_idx` (`idInsumo` ASC) VISIBLE,
  INDEX `idAutoriza_idx` (`idAutoriza` ASC) VISIBLE,
  CONSTRAINT `idProveedores`
    FOREIGN KEY (`idProveedor`)
    REFERENCES `LECSys1`.`proveedores` (`idProveedores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idInsumo`
    FOREIGN KEY (`idInsumo`)
    REFERENCES `LECSys1`.`insumos` (`idinsumos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAutoriza`
    FOREIGN KEY (`idAutoriza`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LECSys1`.`puntualidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LECSys1`.`puntualidad` (
  `idPuntualidad` INT NOT NULL AUTO_INCREMENT,
  `idEmpleado` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `horaEntrada` TIME NULL,
  `horaSalida` TIME NULL,
  `comentario` VARCHAR(90) NULL,
  PRIMARY KEY (`idPuntualidad`),
  INDEX `empleado_idx` (`idEmpleado` ASC) VISIBLE,
  CONSTRAINT `empleados`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `LECSys1`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;