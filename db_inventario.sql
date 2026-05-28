-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 26-05-2026 a las 02:40:16
-- Versión del servidor: 8.4.7
-- Versión de PHP: 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_inventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `UQ_CATEGORIA_NOMBRE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `estado`, `fecha_creacion`) VALUES
(1, 'Electronica', 'Dispositivos y componentes electronicos', 1, '2026-05-21 18:10:01'),
(2, 'Papeleria', 'Articulos de oficina y escritura', 1, '2026-05-21 18:10:01'),
(3, 'Herramientas', 'Herramientas manuales y electricas', 1, '2026-05-21 18:10:01'),
(4, 'Alimentos', 'Productos alimenticios no perecederos', 1, '2026-05-21 18:10:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `id_categoria` int NOT NULL,
  `codigo` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `UQ_PRODUCTO_COD` (`codigo`),
  KEY `FK_PROD_CAT` (`id_categoria`)
) ;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `id_categoria`, `codigo`, `nombre`, `descripcion`, `precio`, `stock`, `estado`, `fecha_creacion`) VALUES
(1, 1, 'ELEC-001', 'Cable HDMI 2m', 'Cable HDMI 4K ultra HD 2 metros', 8.99, 50, 1, '2026-05-21 18:10:01'),
(2, 1, 'ELEC-002', 'Teclado USB', 'Teclado mecanico retroiluminado', 35.00, 20, 1, '2026-05-21 18:10:01'),
(3, 2, 'PAP-001', 'Resma papel A4', 'Resma 500 hojas 75g bond blanco', 4.50, 200, 1, '2026-05-21 18:10:01'),
(4, 2, 'PAP-002', 'Boligrafos x12', 'Caja de 12 boligrafos azules', 2.25, 150, 1, '2026-05-21 18:10:01'),
(5, 3, 'HER-001', 'Destornillador set', 'Juego de 6 destornilladores Phillips', 12.00, 30, 1, '2026-05-21 18:10:01'),
(6, 4, 'ALI-001', 'Cafe molido 250g', 'Cafe molido de origen colombiano', 6.75, 80, 1, '2026-05-21 18:10:01');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_PROD_CAT` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
