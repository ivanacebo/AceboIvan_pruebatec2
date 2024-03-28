-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2024 a las 19:32:13
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ayuntamiento`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudadano`
--

CREATE TABLE `ciudadano` (
  `ID` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TELEFONO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ciudadano`
--

INSERT INTO `ciudadano` (`ID`, `APELLIDO`, `BORRADO`, `DIRECCION`, `DNI`, `FECHANACIMIENTO`, `NOMBRE`, `TELEFONO`) VALUES
(1, 'Garcia', 1, 'Calle Mayor 1', '12345678A', '1990-01-15', 'Juan', '123456789'),
(2, 'López', 0, 'Plaza España 2', '23456789B', '1985-05-20', 'María', '234567890'),
(3, 'Martínez', 0, 'Avenida Libertad 3', '34567890C', '1978-09-10', 'Pedro', '345678901'),
(4, 'Rodríguez', 0, 'Calle Sol 4', '45678901D', '1982-03-25', 'Ana', '456789012'),
(5, 'Fernández', 0, 'Paseo Marítimo 5', '56789012E', '1995-11-30', 'Carlos', '567890123'),
(6, 'Gómez', 0, 'Calle Gran Vía 6', '67890123F', '1973-07-08', 'Laura', '678901234'),
(7, 'Díaz', 0, 'Calle Alcalá 7', '78901234G', '1988-12-12', 'David', '789012345'),
(8, 'Perez', 0, 'Avenida Diagonal 8', '89012345H', '1992-06-18', 'Eleonor', '890123456'),
(9, 'Sánchez', 0, 'Calle Reyes Católicos 9', '90123456I', '1980-04-05', 'Miguel', '901234567'),
(10, 'Hernández', 0, 'Avenida del Parque 10', '01234567J', '1999-08-22', 'Sara', '012345678'),
(11, 'Jiménez', 0, 'Calle San José 11', '12345678K', '1975-02-14', 'Daniel', '123456789'),
(12, 'Ruiz', 0, 'Plaza de la Constitución 12', '23456789L', '1993-10-07', 'Lucía', '234567890'),
(13, 'González', 0, 'Avenida de Mayo 13', '34567890M', '1987-11-29', 'Javier', '345678901'),
(14, 'Fuentes', 0, 'Calle Mayor 14', '45678901N', '1984-07-03', 'Carmen', '456789012'),
(15, 'Ortega', 0, 'Paseo de la Castellana 15', '56789012O', '1979-09-28', 'Pablo', '567890123'),
(16, 'Vázquez', 0, 'Calle Real 16', '67890123P', '1991-03-17', 'Silvia', '678901234'),
(17, 'Molina', 0, 'Avenida Central 17', '78901234Q', '1986-05-11', 'Diego', '789012345'),
(18, 'Sanz', 0, 'Calle Valencia 18', '89012345R', '1997-12-09', 'Marina', '890123456'),
(19, 'Cruz', 0, 'Avenida de la Paz 19', '90123456S', '1977-01-26', 'Marcos', '901234567'),
(20, 'Blanco', 0, 'Calle Almirante 20', '01234567T', '1994-04-02', 'Paula', '012345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcionario`
--

CREATE TABLE `funcionario` (
  `ID` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `CARGO` varchar(255) DEFAULT NULL,
  `DEPARTAMENTO` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TELEFONO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `funcionario`
--

INSERT INTO `funcionario` (`ID`, `APELLIDO`, `BORRADO`, `CARGO`, `DEPARTAMENTO`, `DIRECCION`, `DNI`, `FECHANACIMIENTO`, `NOMBRE`, `TELEFONO`) VALUES
(1, 'González', 0, 'Oficial de Registro Civil', 'Registro Civil', 'Calle Principal 21', '34567890M', '1990-01-15', 'Javier', '123456781'),
(2, 'Fuentes', 1, 'Asesor de Impuestos', 'Oficina de Impuestos', 'Avenida Central 22', '45678901N', '1985-05-20', 'Carmen', '234567892'),
(3, 'Ortega', 0, 'Gestor de Licencias de Construcción', 'Departamento de Urbanismo', 'Plaza Mayor 23', '56789012O', '1978-09-10', 'Pablo', '345678903'),
(4, 'Vázquez', 0, 'Encargado de Expedición de Certificados', 'Oficina de Certificados', 'Calle Sol 24', '67890123P', '1982-03-25', 'Silvia', '456789014'),
(5, 'Molina', 0, 'Asistente de Trámites Administrativos', 'Oficina de Atención Ciudadana', 'Paseo Marítimo 25', '78901234Q', '1995-11-30', 'Diego', '567890125'),
(6, 'Sanz', 0, 'Recepcionista de Documentos', 'Oficina de Recepción', 'Calle Gran Vía 26', '89012345R', '1973-07-08', 'Marina', '678901236'),
(7, 'Cruz', 0, 'Coordinador de Eventos Culturales', 'Departamento de Cultura', 'Calle Alcalá 27', '90123456S', '1988-12-12', 'Marcos', '789012347'),
(8, 'Blanco', 0, 'Encargado de Inscripciones Deportivas', 'Oficina de Deportes', 'Avenida Diagonal 28', '01234567T', '1992-06-18', 'Paula', '890123458'),
(9, 'García', 0, 'Asesor de Asuntos Sociales', 'Departamento de Asuntos Sociales', 'Calle Reyes Católicos 29', '12345678U', '1980-04-05', 'Juan', '901234569'),
(10, 'López', 0, 'Responsable de Información Turística', 'Oficina de Turismo', 'Avenida del Parque 30', '23456789V', '1999-08-22', 'María', '012345670'),
(11, 'Martinez', 0, 'Asesor de Documentacion Legal', 'Departamento Legal', 'Calle San Jose 31', '34567890W', '1975-02-14', 'Pedro', '123456781'),
(12, 'Rodríguez', 0, 'Coordinador de Atención al Cliente', 'Oficina de Atención al Cliente', 'Plaza de la Constitución 32', '45678901X', '1993-10-07', 'Ana', '234567892');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `ID` bigint(20) NOT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `HORARIOFIN` varchar(255) DEFAULT NULL,
  `HORARIOINICIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `ID` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `FECHANACIMIENTO` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TELEFONO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tramite`
--

CREATE TABLE `tramite` (
  `ID` bigint(20) NOT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tramite`
--

INSERT INTO `tramite` (`ID`, `BORRADO`, `DESCRIPCION`, `NOMBRE`) VALUES
(1, 1, 'Proceso para obtener una licencia de construccion para proyectos inmobiliarios', 'Licencia de Construccion'),
(2, 0, 'Renovación anual de la licencia comercial para negocios establecidos.', 'Renovación de Licencia Comercial'),
(3, 0, 'Solicitud de autorización para realizar ventas ambulantes en áreas designadas.', 'Permiso de Venta Ambulante'),
(4, 0, 'Procedimiento para registrar un matrimonio civil.', 'Registro Civil para Matrimonio'),
(5, 0, 'Obtención o renovación de la licencia de conducir.', 'Licencia de Conducir'),
(6, 0, 'Registro oficial de nacimientos en el registro civil.', 'Registro de Nacimiento'),
(7, 0, 'Solicitud de ayuda social para personas necesitadas.', 'Solicitud de Ayuda Social'),
(8, 0, 'Solicitud de permiso de residencia para extranjeros.', 'Permiso de Residencia'),
(9, 0, 'Solicitud de certificado de antecedentes penales para trámites legales.', 'Certificado de Antecedentes Penales'),
(10, 0, 'Inscripción de estudiantes en escuelas públicas del municipio.', 'Inscripción a Escuelas Públicas'),
(11, 0, 'Pago de impuestos municipales sobre la propiedad y actividades comerciales.', 'Pago de Impuestos Municipales'),
(12, 0, 'Obtención de licencia de funcionamiento para establecimientos comerciales.', 'Licencia de Funcionamiento para Negocios'),
(13, 0, 'Registro oficial de cambio de domicilio para residentes.', 'Cambio de Domicilio'),
(14, 0, 'Solicitud de subvención para proyectos culturales y artísticos.', 'Solicitud de Subvención para Proyectos Culturales'),
(15, 0, 'Autorización para realizar obras menores en propiedades particulares.', 'Permiso de Obras Menores');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `ID` bigint(20) NOT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `ESTADO` varchar(255) DEFAULT NULL,
  `FECHATURNO` date DEFAULT NULL,
  `HORATURNO` time DEFAULT NULL,
  `id_ciudadano` bigint(20) DEFAULT NULL,
  `id_funcionario` bigint(20) DEFAULT NULL,
  `id_tramite` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`ID`, `BORRADO`, `ESTADO`, `FECHATURNO`, `HORATURNO`, `id_ciudadano`, `id_funcionario`, `id_tramite`) VALUES
(1, 0, 'Ya atendido', '2024-03-28', '12:00:00', 7, 8, 10),
(2, 0, 'Ya atendido', '2024-03-29', '07:55:00', 12, 8, 11),
(3, 1, 'Ya atendido', '2024-03-28', '19:00:00', 16, 9, 4),
(4, 0, 'Ya atendido', '2024-03-28', '20:00:00', 7, 12, 13),
(5, 0, 'Ya atendido', '2024-03-28', '07:00:00', 6, 3, 13),
(6, 0, 'Ya atendido', '2024-03-29', '19:00:00', 6, 8, 11),
(7, 0, 'Ya atendido', '2024-03-29', '18:00:00', 18, 11, 12),
(8, 0, 'Ya atendido', '2024-03-29', '17:00:00', 8, 12, 6),
(9, 0, 'Ya atendido', '2024-03-28', '07:00:00', 5, 12, 12),
(10, 0, 'Ya atendido', '2024-04-01', '17:00:00', 12, 7, 15),
(11, 0, 'En espera', '2024-04-02', '18:02:00', 8, 6, 13),
(12, 0, 'En espera', '2024-04-01', '20:59:00', 8, 5, 9),
(13, 0, 'En espera', '2024-04-02', '19:00:00', 9, 8, 10),
(14, 0, 'En espera', '2024-04-05', '18:00:00', 14, 9, 12),
(15, 0, 'En espera', '2024-04-03', '08:02:00', 10, 6, 11),
(16, 0, 'En espera', '2024-04-04', '19:05:00', 19, 9, 5),
(17, 0, 'En espera', '2024-04-04', '08:56:00', 10, 6, 7),
(18, 0, 'En espera', '2024-04-18', '20:04:00', 5, 7, 5),
(19, 0, 'En espera', '2024-04-10', '11:05:00', 14, 7, 12),
(20, 0, 'En espera', '2024-04-30', '19:00:00', 11, 12, 2),
(21, 0, 'En espera', '2024-04-30', '13:00:00', 20, 11, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `IDUSUARIO` int(11) NOT NULL,
  `BORRADO` tinyint(1) DEFAULT 0,
  `NOMBREUSUARIO` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IDUSUARIO`, `BORRADO`, `NOMBREUSUARIO`, `PASSWORD`, `ROL`) VALUES
(1, 0, 'Luisina', '1234', 'todocode'),
(2, 0, 'Ivan', '1234', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `tramite`
--
ALTER TABLE `tramite`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_TURNO_id_funcionario` (`id_funcionario`),
  ADD KEY `FK_TURNO_id_ciudadano` (`id_ciudadano`),
  ADD KEY `FK_TURNO_id_tramite` (`id_tramite`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IDUSUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tramite`
--
ALTER TABLE `tramite`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `FK_TURNO_id_ciudadano` FOREIGN KEY (`id_ciudadano`) REFERENCES `ciudadano` (`ID`),
  ADD CONSTRAINT `FK_TURNO_id_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`ID`),
  ADD CONSTRAINT `FK_TURNO_id_tramite` FOREIGN KEY (`id_tramite`) REFERENCES `tramite` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
