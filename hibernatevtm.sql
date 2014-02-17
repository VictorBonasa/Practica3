-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 17-02-2014 a las 21:16:25
-- Versión del servidor: 5.1.41
-- Versión de PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `hibernatevtm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `circuitos`
--

CREATE TABLE IF NOT EXISTS `circuitos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(50) DEFAULT NULL,
  `distancia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `circuitos`
--

INSERT INTO `circuitos` (`id`, `nombre`, `ubicacion`, `distancia`) VALUES
(1, 'circuito1', 'lugarCircuito1', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coches`
--

CREATE TABLE IF NOT EXISTS `coches` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `id_equipos` int(10) unsigned NOT NULL,
  `id_pilotos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_equipos` (`id_equipos`),
  KEY `id_pilotos` (`id_pilotos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `coches`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

CREATE TABLE IF NOT EXISTS `equipos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `fundador` varchar(30) DEFAULT NULL,
  `presidente` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`id`, `nombre`, `fundador`, `presidente`) VALUES
(1, 'Ferrari', 'yo', 'yo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos_sponsors`
--

CREATE TABLE IF NOT EXISTS `equipos_sponsors` (
  `id_equipos` int(10) unsigned NOT NULL,
  `id_sponsors` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_equipos`,`id_sponsors`),
  KEY `id_equipos` (`id_equipos`),
  KEY `id_sponsors` (`id_sponsors`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `equipos_sponsors`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pilotos`
--

CREATE TABLE IF NOT EXISTS `pilotos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `nacionalidad` varchar(30) DEFAULT NULL,
  `dorsal` int(11) DEFAULT NULL,
  `id_equipos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_equipos` (`id_equipos`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `pilotos`
--

INSERT INTO `pilotos` (`id`, `nombre`, `nacionalidad`, `dorsal`, `id_equipos`) VALUES
(1, 'Victor', 'Española', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pilotos_circuitos`
--

CREATE TABLE IF NOT EXISTS `pilotos_circuitos` (
  `id_pilotos` int(10) unsigned NOT NULL,
  `id_circuitos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_pilotos`,`id_circuitos`),
  KEY `id_pilotos` (`id_pilotos`),
  KEY `id_circuitos` (`id_circuitos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `pilotos_circuitos`
--

INSERT INTO `pilotos_circuitos` (`id_pilotos`, `id_circuitos`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruedas`
--

CREATE TABLE IF NOT EXISTS `ruedas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pulgadas` int(11) DEFAULT NULL,
  `tipo_neumatico` varchar(50) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `id_coches` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_coches` (`id_coches`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `ruedas`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sponsors`
--

CREATE TABLE IF NOT EXISTS `sponsors` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `presidente` varchar(50) DEFAULT NULL,
  `web` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `sponsors`
--


--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `coches`
--
ALTER TABLE `coches`
  ADD CONSTRAINT `coches_ibfk_1` FOREIGN KEY (`id_equipos`) REFERENCES `equipos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `coches_ibfk_2` FOREIGN KEY (`id_pilotos`) REFERENCES `pilotos` (`id`);

--
-- Filtros para la tabla `equipos_sponsors`
--
ALTER TABLE `equipos_sponsors`
  ADD CONSTRAINT `equipos_sponsors_ibfk_1` FOREIGN KEY (`id_equipos`) REFERENCES `equipos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `equipos_sponsors_ibfk_2` FOREIGN KEY (`id_sponsors`) REFERENCES `sponsors` (`id`);

--
-- Filtros para la tabla `pilotos`
--
ALTER TABLE `pilotos`
  ADD CONSTRAINT `pilotos_ibfk_1` FOREIGN KEY (`id_equipos`) REFERENCES `equipos` (`id`);

--
-- Filtros para la tabla `pilotos_circuitos`
--
ALTER TABLE `pilotos_circuitos`
  ADD CONSTRAINT `pilotos_circuitos_ibfk_1` FOREIGN KEY (`id_pilotos`) REFERENCES `pilotos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pilotos_circuitos_ibfk_2` FOREIGN KEY (`id_circuitos`) REFERENCES `circuitos` (`id`);

--
-- Filtros para la tabla `ruedas`
--
ALTER TABLE `ruedas`
  ADD CONSTRAINT `ruedas_ibfk_1` FOREIGN KEY (`id_coches`) REFERENCES `coches` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
