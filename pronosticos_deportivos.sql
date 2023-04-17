-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-04-2023 a las 04:29:09
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pronosticos_deportivos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `idEquipo` varchar(5) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`idEquipo`, `nombre`) VALUES
('AR', 'Argentina'),
('AS', 'Arabia Saudita'),
('AU', 'Australia'),
('BE', 'Bélgica'),
('BRA', 'Brasil'),
('CA', 'Canada'),
('CN', 'Camerún'),
('CR', 'Costa Rica'),
('CRO', 'Croacia'),
('DE', 'Dinamarca'),
('EC', 'Ecuador'),
('EN', 'Inglaterra'),
('ES', 'España'),
('FR', 'Francia'),
('GE', 'Alemania'),
('GH', 'Ghana'),
('IR', 'Irán'),
('JP', 'Japón'),
('ME', 'Mexico'),
('MO', 'Morocco'),
('NE', 'Netherlands'),
('PG', 'Portugal'),
('PO', 'Polonia'),
('QA', 'Qatar'),
('RK', 'República de Korea'),
('SE', 'Senegal'),
('SER', 'Serbia'),
('SW', 'Suiza'),
('TU', 'Túnez'),
('UR', 'Uruguay'),
('US', 'USA'),
('WS', 'Wales');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fase`
--

CREATE TABLE `fase` (
  `idFase` int(2) NOT NULL,
  `ronda1` int(11) NOT NULL,
  `ronda2` int(11) NOT NULL,
  `ronda3` int(11) NOT NULL,
  `ronda4` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `fase`
--

INSERT INTO `fase` (`idFase`, `ronda1`, `ronda2`, `ronda3`, `ronda4`) VALUES
(1, 1, 2, 3, 4),
(3, 1, 2, 3, 4),
(2, 9, 10, 11, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `idPartido` int(2) NOT NULL,
  `equipo1` varchar(10) NOT NULL,
  `goles1` int(2) NOT NULL,
  `equipo2` varchar(10) NOT NULL,
  `goles2` int(2) NOT NULL,
  `idRonda` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`idPartido`, `equipo1`, `goles1`, `equipo2`, `goles2`, `idRonda`) VALUES
(1, 'QA', 0, 'EC', 2, 1),
(2, 'EN', 6, 'IR', 2, 1),
(3, 'SE', 0, 'NE', 1, 1),
(4, 'US', 1, 'WS', 1, 1),
(5, 'AR', 1, 'AS', 2, 1),
(6, 'DE', 0, 'TU', 0, 1),
(7, 'ME', 0, 'PO', 0, 1),
(8, 'FR', 4, 'AU', 1, 1),
(9, 'MO', 0, 'CRO', 0, 1),
(10, 'GE', 1, 'JP', 2, 1),
(11, 'ES', 7, 'CR', 0, 1),
(12, 'BE', 1, 'CA', 0, 1),
(13, 'SW', 1, 'CA', 0, 2),
(14, 'UR', 0, 'RK', 0, 2),
(15, 'PO', 3, 'GH', 2, 2),
(16, 'BRA', 2, 'SE', 0, 2),
(17, 'WS', 0, 'IR', 2, 2),
(18, 'QA', 1, 'SE', 3, 2),
(19, 'NE', 1, 'EC', 1, 2),
(20, 'EN', 0, 'US', 0, 2),
(21, 'TU', 0, 'AU', 1, 2),
(22, 'PO', 2, 'AS', 0, 2),
(23, 'FR', 2, 'DE', 1, 2),
(24, 'AR', 2, 'ME', 0, 2),
(25, 'JP', 0, 'CR', 1, 3),
(26, 'BE', 0, 'MO', 2, 3),
(27, 'CR', 4, 'CA', 1, 3),
(28, 'ES', 1, 'GE', 1, 3),
(29, 'CA', 3, 'SE', 3, 3),
(30, 'RK', 2, 'GH', 3, 3),
(31, 'BRA', 1, 'SW', 0, 3),
(32, 'PO', 2, 'UR', 0, 3),
(33, 'EC', 1, 'SE', 2, 3),
(34, 'NE', 2, 'QA', 0, 3),
(35, 'WS', 0, 'EN', 3, 3),
(36, 'IR', 0, 'US', 1, 3),
(37, 'AU', 1, 'DE', 0, 4),
(38, 'TU', 1, 'FR', 0, 4),
(39, 'PO', 0, 'AR', 2, 4),
(40, 'AS', 1, 'ME', 2, 4),
(41, 'CR', 0, 'BE', 0, 4),
(42, 'CA', 1, 'MO', 2, 4),
(43, 'JP', 2, 'ES', 1, 4),
(44, 'CR', 2, 'GE', 4, 4),
(45, 'GH', 0, 'UR', 2, 4),
(46, 'RK', 2, 'PO', 1, 4),
(47, 'SE', 2, 'SW', 3, 4),
(48, 'CA', 1, 'BRA', 0, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pronostico`
--

CREATE TABLE `pronostico` (
  `idPronostico` int(2) NOT NULL,
  `idUsuario` int(2) NOT NULL,
  `IdPartido` int(2) NOT NULL,
  `resultado` enum('gana1','gana2','empate','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pronostico`
--

INSERT INTO `pronostico` (`idPronostico`, `idUsuario`, `IdPartido`, `resultado`) VALUES
(1, 1, 1, 'gana1'),
(2, 1, 2, 'gana2'),
(3, 1, 3, 'empate'),
(4, 1, 4, 'gana1'),
(5, 1, 5, 'gana1'),
(6, 1, 6, 'gana2'),
(7, 1, 7, 'gana1'),
(8, 1, 8, 'gana2'),
(9, 2, 1, 'gana2'),
(10, 2, 2, 'gana1'),
(11, 2, 3, 'gana2'),
(12, 2, 4, 'gana1'),
(13, 2, 5, 'empate'),
(14, 2, 6, 'gana1'),
(15, 2, 7, 'empate'),
(16, 2, 8, 'gana1'),
(17, 3, 1, 'empate'),
(18, 3, 2, 'gana1'),
(19, 3, 3, 'empate'),
(20, 3, 4, 'gana2'),
(21, 3, 5, 'empate'),
(22, 3, 6, 'gana2'),
(23, 3, 7, 'gana1'),
(24, 3, 8, 'gana2'),
(25, 4, 1, 'gana2'),
(26, 4, 2, 'gana1'),
(27, 4, 3, 'gana2'),
(28, 4, 4, 'empate'),
(29, 4, 5, 'gana2'),
(30, 4, 6, 'empate'),
(31, 4, 7, 'empate'),
(32, 4, 8, 'gana1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ronda`
--

CREATE TABLE `ronda` (
  `idRonda` int(2) NOT NULL,
  `partido1` int(2) NOT NULL,
  `partido2` int(2) NOT NULL,
  `partido3` int(2) NOT NULL,
  `partido4` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ronda`
--

INSERT INTO `ronda` (`idRonda`, `partido1`, `partido2`, `partido3`, `partido4`) VALUES
(1, 1, 2, 3, 4),
(2, 5, 6, 7, 8),
(3, 9, 10, 11, 12),
(4, 13, 14, 15, 16),
(5, 17, 18, 19, 20),
(6, 21, 22, 23, 24),
(7, 25, 26, 27, 28),
(8, 29, 30, 31, 32),
(9, 33, 34, 35, 36),
(10, 37, 38, 39, 40),
(11, 41, 42, 43, 40),
(12, 45, 46, 47, 48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(2) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nombre`) VALUES
(1, 'Juan'),
(2, 'Pedro'),
(3, 'Leo'),
(4, 'Nahuel');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`idEquipo`);

--
-- Indices de la tabla `fase`
--
ALTER TABLE `fase`
  ADD PRIMARY KEY (`idFase`),
  ADD KEY `ronda1` (`ronda1`,`ronda2`,`ronda3`,`ronda4`),
  ADD KEY `ronda2` (`ronda2`),
  ADD KEY `ronda3` (`ronda3`),
  ADD KEY `ronda4` (`ronda4`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`idPartido`),
  ADD KEY `equipo1` (`equipo1`,`equipo2`),
  ADD KEY `equipo2` (`equipo2`);

--
-- Indices de la tabla `pronostico`
--
ALTER TABLE `pronostico`
  ADD PRIMARY KEY (`idPronostico`),
  ADD KEY `IdPartido` (`IdPartido`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `ronda`
--
ALTER TABLE `ronda`
  ADD PRIMARY KEY (`idRonda`),
  ADD KEY `partido1` (`partido1`,`partido2`,`partido3`,`partido4`),
  ADD KEY `partido2` (`partido2`),
  ADD KEY `partido3` (`partido3`),
  ADD KEY `partido4` (`partido4`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `fase`
--
ALTER TABLE `fase`
  MODIFY `idFase` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `idPartido` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `pronostico`
--
ALTER TABLE `pronostico`
  MODIFY `idPronostico` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `ronda`
--
ALTER TABLE `ronda`
  MODIFY `idRonda` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `fase`
--
ALTER TABLE `fase`
  ADD CONSTRAINT `fase_ibfk_1` FOREIGN KEY (`ronda1`) REFERENCES `ronda` (`idRonda`),
  ADD CONSTRAINT `fase_ibfk_2` FOREIGN KEY (`ronda2`) REFERENCES `ronda` (`idRonda`),
  ADD CONSTRAINT `fase_ibfk_3` FOREIGN KEY (`ronda3`) REFERENCES `ronda` (`idRonda`),
  ADD CONSTRAINT `fase_ibfk_4` FOREIGN KEY (`ronda4`) REFERENCES `ronda` (`idRonda`);

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `partido_ibfk_1` FOREIGN KEY (`equipo1`) REFERENCES `equipo` (`idEquipo`),
  ADD CONSTRAINT `partido_ibfk_2` FOREIGN KEY (`equipo2`) REFERENCES `equipo` (`idEquipo`);

--
-- Filtros para la tabla `pronostico`
--
ALTER TABLE `pronostico`
  ADD CONSTRAINT `pronostico_ibfk_1` FOREIGN KEY (`IdPartido`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `pronostico_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `ronda`
--
ALTER TABLE `ronda`
  ADD CONSTRAINT `ronda_ibfk_1` FOREIGN KEY (`partido1`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `ronda_ibfk_2` FOREIGN KEY (`partido2`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `ronda_ibfk_3` FOREIGN KEY (`partido3`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `ronda_ibfk_4` FOREIGN KEY (`partido4`) REFERENCES `partido` (`idPartido`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
