-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-07-2025 a las 21:34:39
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restaurantecatering`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idAdministrador` int(11) NOT NULL,
  `Contraseña` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idAdministrador`, `Contraseña`) VALUES
(1071578413, '1071578413'),
(1097728377, '1097728377');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `Codigocategoria` int(11) NOT NULL,
  `Desayuno` varchar(45) NOT NULL,
  `Almuerzo` varchar(45) NOT NULL,
  `Cena` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menú`
--

CREATE TABLE `menú` (
  `IdMenú` int(11) NOT NULL,
  `NombreMenú` varchar(45) NOT NULL,
  `Descripción` varchar(45) NOT NULL,
  `Categorias` varchar(45) NOT NULL,
  `Reservación_idPedido` int(11) NOT NULL,
  `Categorias_Codigocategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `papeleriadereciclaje`
--

CREATE TABLE `papeleriadereciclaje` (
  `idPedido` int(11) NOT NULL,
  `identificacion` int(15) NOT NULL,
  `nombreyapellidos` varchar(30) DEFAULT NULL,
  `fechadeEntrega` enum('Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo') DEFAULT NULL,
  `opcion` int(10) DEFAULT NULL,
  `lugarEntrega` enum('Comedor Platanal','Aph Higabra','Mantenimiento mecanico') DEFAULT NULL,
  `servicio` enum('Desayuno','Almuerzo','Cena') DEFAULT NULL,
  `ceco` varchar(20) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `contratista` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_comidas`
--

CREATE TABLE `plan_comidas` (
  `id` int(11) NOT NULL,
  `dia_semana` enum('Lunes','Martes','Miercoles','Jueves','Viernes','sabado','Domingo') NOT NULL,
  `tipo_comida` enum('Desayuno','Almuerzo','Cena') NOT NULL,
  `opcion` int(11) DEFAULT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `plan_comidas`
--

INSERT INTO `plan_comidas` (`id`, `dia_semana`, `tipo_comida`, `opcion`, `descripcion`) VALUES
(1, 'Lunes', 'Desayuno', 1, 'Huevos revueltos con pan integral y jugo de naranja'),
(2, 'Lunes', 'Desayuno', 2, 'Arepa con queso y chocolate caliente'),
(3, 'Lunes', 'Desayuno', 3, 'Alternativa saludable: Yogur con granola y frutas'),
(4, 'Martes', 'Desayuno', 1, 'Huevos con salchicha, pan y café'),
(5, 'Martes', 'Desayuno', 2, 'Calentado con huevo y arepa'),
(6, 'Martes', 'Desayuno', 3, 'Alternativa saludable: Avena con banano y nueces'),
(7, 'Miercoles', 'Desayuno', 1, 'Huevos pericos con arepa y jugo de guayaba'),
(8, 'Miercoles', 'Desayuno', 2, 'Tamales y chocolate'),
(9, 'Miercoles', 'Desayuno', 3, 'Alternativa saludable: Smoothie de espinaca y frutas'),
(10, 'Jueves', 'Desayuno', 1, 'Huevos cocidos con pan y leche'),
(11, 'Jueves', 'Desayuno', 2, 'Caldo de costilla con arepa'),
(12, 'Jueves', 'Desayuno', 3, 'Alternativa saludable: Cereal integral con fruta'),
(13, 'Viernes', 'Desayuno', 1, 'Huevos rancheros con arepa'),
(14, 'Viernes', 'Desayuno', 2, 'Empanadas con ají y jugo'),
(15, 'Viernes', 'Desayuno', 3, 'Alternativa saludable: Batido de avena con manzana'),
(16, 'sabado', 'Desayuno', 1, 'Huevos al gusto con chocolate y pan'),
(17, 'sabado', 'Desayuno', 2, 'Arepa rellena con carne desmechada'),
(18, 'sabado', 'Desayuno', 3, 'Alternativa saludable: Fruta picada con yogur'),
(19, 'Domingo', 'Desayuno', 1, 'Huevos fritos con salchicha y pan'),
(20, 'Domingo', 'Desayuno', 2, 'Tamal tolimense'),
(21, 'Domingo', 'Desayuno', 3, 'Alternativa saludable: Tostadas integrales con aguacate'),
(22, 'Lunes', 'Almuerzo', 1, 'Bandeja paisa'),
(23, 'Lunes', 'Almuerzo', 2, 'Arroz con pollo y ensalada'),
(24, 'Lunes', 'Almuerzo', 3, 'Alternativa saludable: Pollo a la plancha con arroz integral'),
(25, 'Martes', 'Almuerzo', 1, 'Sopa de lentejas y carne'),
(26, 'Martes', 'Almuerzo', 2, 'Arroz chino criollo'),
(27, 'Martes', 'Almuerzo', 3, 'Alternativa saludable: Ensalada de atún con verduras'),
(28, 'Miercoles', 'Almuerzo', 1, 'Sudado de pollo con arroz y papa'),
(29, 'Miercoles', 'Almuerzo', 2, 'Espaguetis con carne molida'),
(30, 'Miercoles', 'Almuerzo', 3, 'Alternativa saludable: Filete de pescado con ensalada'),
(31, 'Jueves', 'Almuerzo', 1, 'Frijoles con chicharrón'),
(32, 'Jueves', 'Almuerzo', 2, 'Arroz con costilla'),
(33, 'Jueves', 'Almuerzo', 3, 'Alternativa saludable: Pollo al vapor con verduras'),
(34, 'Viernes', 'Almuerzo', 1, 'Lentejas con arroz y huevo'),
(35, 'Viernes', 'Almuerzo', 2, 'Pizza casera'),
(36, 'Viernes', 'Almuerzo', 3, 'Alternativa saludable: Ensalada de quinoa y pollo'),
(37, 'sabado', 'Almuerzo', 1, 'Ajiaco santafereño'),
(38, 'sabado', 'Almuerzo', 2, 'Chuleta valluna con arroz'),
(39, 'sabado', 'Almuerzo', 3, 'Alternativa saludable: Filete de pechuga a la plancha'),
(40, 'Domingo', 'Almuerzo', 1, 'Sancocho de gallina'),
(41, 'Domingo', 'Almuerzo', 2, 'Pasta con albóndigas'),
(42, 'Domingo', 'Almuerzo', 3, 'Alternativa saludable: Vegetales al wok con tofu'),
(43, 'Lunes', 'Cena', 1, 'Sopa de verduras con pan'),
(44, 'Lunes', 'Cena', 2, 'Comida rápida: Hamburguesa con papas'),
(45, 'Lunes', 'Cena', 3, 'Alternativa saludable: Wrap de pollo con lechuga'),
(46, 'Martes', 'Cena', 1, 'Sopa de pollo y arroz'),
(47, 'Martes', 'Cena', 2, 'Comida rápida: Perro caliente y jugo'),
(48, 'Martes', 'Cena', 3, 'Alternativa saludable: Ensalada César'),
(49, 'Miercoles', 'Cena', 1, 'Crema de auyama con tostadas'),
(50, 'Miercoles', 'Cena', 2, 'Comida rápida: Nuggets con papas'),
(51, 'Miercoles', 'Cena', 3, 'Alternativa saludable: Batido de frutas y avena'),
(52, 'Jueves', 'Cena', 1, 'Sopa de espinaca y arroz'),
(53, 'Jueves', 'Cena', 2, 'Comida rápida: Pizza de jamón'),
(54, 'Jueves', 'Cena', 3, 'Alternativa saludable: Sandwich integral de atún'),
(55, 'Viernes', 'Cena', 1, 'Consomé de pollo y pan'),
(56, 'Viernes', 'Cena', 2, 'Comida rápida: Empanadas con ají'),
(57, 'Viernes', 'Cena', 3, 'Alternativa saludable: Arepa con aguacate y huevo cocido'),
(58, 'sabado', 'Cena', 1, 'Sopa de pastas con verduras'),
(59, 'sabado', 'Cena', 2, 'Comida rápida: Hamburguesa doble'),
(60, 'sabado', 'Cena', 3, 'Alternativa saludable: Arroz integral con verduras salteadas'),
(61, 'Domingo', 'Cena', 1, 'Sopa de fideos con pan'),
(62, 'Domingo', 'Cena', 2, 'Comida rápida: Salchipapa'),
(63, 'Domingo', 'Cena', 3, 'Alternativa saludable: Tostadas integrales con aguacate');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion`
--

CREATE TABLE `reservacion` (
  `idpedido` int(11) NOT NULL,
  `cedula` int(20) DEFAULT NULL,
  `Dia_de_reservacion` enum('Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo') DEFAULT NULL,
  `Lugar_entrega` enum('Mantenimiento mecanico Oficinas Verdes','Contenedores Azules Higabra','Edificio Administrativo Higabra','Edificio Mina Higabra','Comedor Platanal','Aph Higabra','Mantenimiento mecanico') DEFAULT NULL,
  `TipoServicio` enum('Desayuno','Almuerzo','Cena') DEFAULT NULL,
  `Opcion` int(10) DEFAULT NULL,
  `Descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `reservacion`
--

INSERT INTO `reservacion` (`idpedido`, `cedula`, `Dia_de_reservacion`, `Lugar_entrega`, `TipoServicio`, `Opcion`, `Descripcion`) VALUES
(38, 1097728377, 'Lunes', 'Aph Higabra', 'Cena', 3, 'Alternativa saludable: Wrap de pollo con lechuga'),
(40, 1097728377, 'Miércoles', 'Aph Higabra', 'Desayuno', 3, 'Alternativa saludable: Tostadas integrales con aguacate'),
(41, 1097728377, 'Lunes', 'Comedor Platanal', 'Desayuno', 3, 'Alternativa saludable: Pollo al vapor con verduras'),
(42, 1073231600, 'Lunes', 'Comedor Platanal', 'Desayuno', 3, 'Alternativa saludable: Yogur con granola y frutas'),
(43, 1128459799, 'Miércoles', 'Comedor Platanal', 'Almuerzo', 3, 'Alternativa saludable: Filete de pescado con ensalada'),
(44, 1128459799, 'Miércoles', 'Comedor Platanal', 'Desayuno', 1, 'Sudado de pollo con arroz y papa'),
(45, 1128459799, 'Lunes', 'Comedor Platanal', 'Desayuno', 1, 'Huevos revueltos con pan integral y jugo de naranja'),
(46, 1128459799, 'Lunes', 'Comedor Platanal', 'Almuerzo', 3, 'Alternativa saludable: Pollo a la plancha con arroz integral'),
(47, 1097728377, 'Domingo', 'Contenedores Azules Higabra', 'Desayuno', 3, 'Alternativa saludable: Tostadas integrales con aguacate'),
(48, 1097728377, 'Lunes', 'Comedor Platanal', 'Almuerzo', 3, 'Alternativa saludable: Pollo a la plancha con arroz integral'),
(49, 1097728377, 'Lunes', 'Aph Higabra', 'Almuerzo', 2, 'Arroz con pollo y ensalada'),
(50, 1097728377, 'Lunes', 'Comedor Platanal', 'Desayuno', 2, 'Arepa con queso y chocolate caliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurante`
--

CREATE TABLE `restaurante` (
  `Nit` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `Dirección` varchar(45) NOT NULL,
  `idAdministrador` int(11) NOT NULL,
  `Usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `NombreCompleto` varchar(45) NOT NULL,
  `Ceco` varchar(20) DEFAULT NULL,
  `Area` varchar(50) DEFAULT NULL,
  `Contratista` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `NombreCompleto`, `Ceco`, `Area`, `Contratista`) VALUES
(3486716, 'WILSON ANTONIO MANCO SEPULVEDA', '2H01160101', 'Social Management', 'VIACOTUR'),
(5135179, 'FERNANDO RAFAEL MARBELLO MEDINA', '2H01010302', 'Ruikuang', 'MINTECK'),
(8163326, 'JUAN GABRIEL TORO OCHOA', '2H01160101', 'Social Management', 'VIACOTUR'),
(8323403, 'DARIO JARAMILLO ESPINOSA', '2H01010302', 'Ruikuang', 'MINTECK'),
(8472464, 'FRANCISCO JAVIER ARIAS YEPES', '2H01010304', 'Electrical Maintenance', 'VIACOTUR'),
(8798998, 'ELKIN ALFONSO HERRERA GOMEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(10184518, 'MARCOS EDWARD MORENO', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(13851893, 'OMAR YESID ROJAS', '2H01010301', 'FIXED EQUIP MAINT ADMIN', 'ZIJIN CONTINENTAL GOLD'),
(14326402, 'JHON GELVER PAEZ RAMIREZ', '2H01070201', 'Health & Safety Admin', 'VIACOTUR'),
(19604220, 'ADOLFO DE LA CRUZ', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(36725843, 'DIANDRA CUELLO TALEDO', '2H01070203', 'Medical Center', 'APREHSI'),
(39621829, 'NOHORA CECILIA BAUTISTA CALDERON', '2H01070203', 'Medical Center', 'APREHSI'),
(43116408, 'PAULA ANDREA ZULETA', '2H01120101', 'Human Resource', 'CONSANITAS'),
(49788575, 'ADRIANA MEDID MEJIA FLOREZ', '2H01060201', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(52714122, 'JEANNI PAOLA SANDOVAL', '2H01070203', 'Medical Center', 'APREHSI'),
(53050905, 'ANGELA FERNANDA RAMIREZ GOMEZ', '2H01070202', 'Health & Safety Activity', 'ADECCO'),
(63537030, 'YENELIDA RIVERA JIMENEZ', '2H01070202', 'Health & Safety Activity', 'ADECCO'),
(70418681, 'JHON MARIO PENAGOS QUINTERO', '2H01070202', 'Health & Safety Activity', 'VIACOTUR'),
(71267323, 'JUAN FELIPE PARRA PALACIOS', '2H01010303', 'MOBILE MAINT ADMIN', 'ACCION DEL CAUCA'),
(71338786, 'JUAN CARLOS TORRES ARENAS', '4H01010101', 'Admón. Exploración', 'ATEMPI SEGURIDAD'),
(71374841, 'RUBEN DARIO HIGUITA URREGO', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(71392959, 'LEON DE JESUS FERNANDEZ PALACIO', '2H01160101', 'Social Management', 'VIACOTUR'),
(72264393, 'JOSE RAFAEL CEBALLOS MEDINA', '2H01090503', 'IT', 'ZOFT SECURITY'),
(72436528, 'ARIEL ENRIQUE MARTIN OROZCO', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(73189206, 'FREDY BALDOVINO MEDINA', '2H01010302', 'Ruikuang', 'MINTECK'),
(73203719, 'WALTER BERMEJO VIVANCO', '2H01090401', 'Transportation', 'INTERMANTING'),
(74020566, 'GUILLERMO PELAYO CAICEDO', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(74189025, 'EDGAR HUMBERTO RINCON CORREA', '2H01070202', 'Health & Safety Activity', 'ADECCO'),
(74382165, 'DIEGO ALBEIRO RODRIGUEZ GUECHA', '112H01A07005', 'TSF OPERATION', 'ACCION DEL CAUCA'),
(77031268, 'ARIEL PEREZ RICO', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(80222666, 'JAMER MAZA RIZZO', 'NO APLICA', 'Equipos moviles', 'ACCION DEL CAUCA'),
(80228565, 'CARLOS IVAN DONCEL RAMIREZ', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(91434593, 'RAMON ANTONIO MEJIA GOMEZ', 'NO APLICA', 'NO APLICA', 'NO APLICA'),
(92536348, 'HERNAN DE JESUS ALEAN CUELLO', '2H01010302', 'Ruikuang', 'MINTECK'),
(94070951, 'VICTOR FERNANDO LOVATO BURBANO', 'NO APLICA', 'Mantenimiento', 'ACCION DEL CAUCA'),
(98534525, 'GUSTAVO ALBERTO OSPINA', '2H01070203', 'Medical Center', 'APREHSI'),
(98540678, 'EDGAR PRESIGA USUGA', '2H01160101', 'Social Management', 'VIACOTUR'),
(98565289, 'CARLOS MARIO VASQUEZ BLANDON', '2H01090401', 'Transportation', 'INTERMANTING'),
(98582731, 'WILSON OVAIDER HIGUITA DAVID', '2H01060201', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(109536566, 'ANDRES ROJAS MARTINEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1000112154, 'MARIA FERNANDA HIGUITA TUBERQUIA', 'NO APLICA', 'Planta', 'ZIJIN CONTINENTAL GOLD'),
(1000112352, 'ROSA INES DURANGO TABORDA', '2H01900201', 'EXPENSE BELONGS TO OTHER SUBSIDIARIES', 'ACCION DEL CAUCA'),
(1000305888, 'ANA MARIA MANCO MEDINA', '2H01030209', 'MERRILL CROWE & REFINERÍA', 'ACCION DEL CAUCA'),
(1000438588, 'GIOVANNI ESTRADA PEREZ', '2H01030202', 'Grinding', 'ZIJIN CONTINENTAL GOLD'),
(1000440748, 'MARIA CAMILA RENTERIA VERGARA', '2H01120201', 'Trainee', 'ZIJIN CONTINENTAL GOLD'),
(1000763543, 'SARA BEDOYA PATIÑO', '2H01070203', 'Medical Center', 'APREHSI'),
(1000887728, 'XIOMARA ANDREA CANO VELEZ', '2H01120101', 'HUMAN RESOURCE', 'ZIJIN CONTINENTAL GOLD'),
(1001385940, 'YESICA ANDREA MANCO RODRIGUEZ', 'NO APLICA', 'Inactiva', 'ZIJIN CONTINENTAL GOLD'),
(1001398901, 'KELLY JOHANA HIGUITA TUBERQUIA', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(1001404158, 'HISVELY ANDREA RUEDA TANGARIFE', 'NO APLICA', 'planta WTP', 'ZIJIN CONTINENTAL GOLD'),
(1001404803, 'LINA ZORAIDA LOPEZ RUEDA', '2H01030210', 'CYANIDE DETOXIFICATION', 'ACCION DEL CAUCA'),
(1001555260, 'CRISTIAN ALONSO GIRALDO MESA', '2H01010302', 'Ruikuang', 'MINTECK'),
(1001744395, 'SERGIO ALEJANDRO TAMAYO USUGA', '2H01030208', 'LEACH & CCD', 'ACCION DEL CAUCA'),
(1001744421, 'ANA KARINA MANCO DAVID', 'NO APLICA', 'El usuario se encuen', 'ZIJIN CONTINENTAL GOLD'),
(1001913214, 'NICOLAS BAUTISTA PAEZ', '2H01010302', 'INHOUSE MAIN SERVICES', 'FLUICONNECTO SAS'),
(1002326362, 'JOSE ANGEL TORRES ESTRADA', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1002693636, 'INGRID KATHERINE CARDENAS MARTINEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1003088628, 'JOSE ANGEL MARTINEZ TRESPALACIOS', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1003289869, 'DIEGO FERNANDO ALVAREZ MARTINEZ', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1003688779, 'OSCAR FERNANDO CABALLERO BOTELLO', '2H01010102', 'Technical Services', 'ZIJIN CONTINENTAL GOLD'),
(1004374364, 'LUIS ALEJANDRO ESPAÑA HERRERA', '2H01010302', 'INHOUSE MAINT SERVICES', 'KALTIRE'),
(1004565654, 'JOHNY STEY DAVID CAMPO', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1005178612, 'MARIO FERNANDO MAHECHA GUARIN', '2H01010302', 'Ruikuang', 'MINTECK'),
(1006030998, 'SOFIA BERNAL RIVERA', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1007104496, 'EINA MARCELA GARCÍA HIGUITA', 'NO APLICA', 'planta WTP', 'ACCION DEL CAUCA'),
(1007310667, 'DIANA SULEIMA HIGUITA SALAS', '2H01090101', 'ADMINISTRACIÓN', 'ACCION DEL CAUCA'),
(1007331394, 'EDWIN ALEXANDER CIFUENTES MANCO', '2H01070203', 'Medical Center', 'APREHSI'),
(1007383722, 'JUAN DAVID CAICEDO RINCON', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1007388503, 'FRANCY YANETH GARCIA CHICA', '2H01030205', 'SULFIDE FLOTATION', 'ACCION DEL CAUCA'),
(1007491289, 'DUMAR ANDRES CHAVARRIA MONSALVE', '4H01010101', 'Admón. Exploración', 'ATEMPI SEGURIDAD'),
(1007524384, 'JORDAN SEBASTIAN JARAMILLO GIL', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1007607722, 'EDWIN JOSE FURNIELES RAMOS', '4H01010101', 'Admón. Exploración', 'ATEMPI SEGURIDAD'),
(1011391313, 'JHON ALEJANDRO HERNANDEZ HERNANDEZ', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(1011686002, 'MARILIN CAROLINA BENITEZ HIGUITA', '2H01030208', 'LEACH & CCD', 'ACCION DEL CAUCA'),
(1011686038, 'EMINSON ARLEY MORENO MORENO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1011686068, 'DAIRON FERNANDO ORTIZ ZAPATA', 'NO APLICA', 'planta WTP', 'ACCION DEL CAUCA'),
(1011686289, 'SANTIAGO RODRIGUEZ OQUENDO', '2H01900201', 'EXPENSE BELONGS TO OTHER SUBSIDIARIES', 'ACCION DEL CAUCA'),
(1013457118, 'LEIDY JOHANA MADRID QUIROZ', '2H01150201', 'WAREHOUSE', 'ACCION DEL CAUCA'),
(1015454511, 'ALDHAIR DUQUE ROMERO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1017257055, 'JUAN CAMILIO ESPITIA GRACIANO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1018346592, 'EDISON ALEXANDER DIAZ FLOREZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1020408648, 'JULIAN VALENCIA DUQUE', '2H01010302', 'Ruikuang', 'MINTECK'),
(1020433206, 'CARLOS EDUARDO VALLEJO GOGORA', '2H01070203', 'Medical Center', 'APREHSI'),
(1020436097, 'SANTIAGO RIVERA SANCHEZ', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1020486275, 'ESTEFANI ANDREA USUGA ESPINOSA', '2H01120101', 'Human Resource', 'COMFENALCO'),
(1022092744, 'ANGELA MARIA GRACIANO MANCO', '2H01020106', 'FORMALIZACION', 'ACCION DEL CAUCA'),
(1022099299, 'HEIDY CATHERINE CANO GUZMAN', '2H01030202', 'GRINDING', 'ZIJIN CONTINENTAL GOLD'),
(1023883210, 'LUIS MIGUEL AGUILLON MEDINA', '2H01090503', 'IT', 'ZOFT SECURITY'),
(1027940906, 'ESTEBAN CORREA ACOSTA', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(1030571954, 'MANUEL DAVID MARTINEZ CRUZ', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(1030654690, 'ROBINSON RESTREPO DAVID', '2H01070203', 'Medical Center', 'APREHSI'),
(1034919456, 'JUAN DAVID TABORDA', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(1034986106, 'VICTOR ALFONSO MUÑOZ CONTRERAS', '2H01120201', 'Trainee', 'ZIJIN CONTINENTAL GOLD'),
(1035223488, 'MARLON ANDREY MADRID', '2H01180101', 'EXPLORATION ADMINISTRATIVE', 'ARUS'),
(1035282312, 'ELKIN MAURICIO MANCO CAMPO', '2H01010209', 'Auxiliary Services', 'ZIJIN CONTINENTAL GOLD'),
(1035282980, 'WILMER EUCARIO PRESIGA GARCIA', '2H01010103', 'Mine Geology', 'ZIJIN CONTINENTAL GOLD'),
(1035283048, 'LUIS FERNANDO OQUENDO USUGA', '2H01900201', 'EXPENSE BELONGS TO OTHER SUBSIDIARIES', 'ACCION DEL CAUCA'),
(1035283086, 'BRAYAN ANDRES DAVID USUGA', '2H01010209', 'SERVICIOS AUXILIARES', 'ACCION DEL CAUCA'),
(1035283760, 'SEBASTIAN DAVID RESTREPO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1035283877, 'CARLOS RODRIGUEZ OQUENDO', '2H01900201', 'EXPENSE BELONGS TO O', 'ACCION DEL CAUCA'),
(1035284048, 'SANDRA LILIANA DAVID SALAS', '2H01120101', 'HUMAN RESOURCE', 'ACCION DEL CAUCA'),
(1035302351, 'VICTOR ALFONSO CAMPOS MONTOYA', '2H01030208', 'LEACH & CCD', 'ACCION DEL CAUCA'),
(1035303026, 'YURY ANDREA VELEZ FLOREZ', '2H01070203', 'Medical Center', 'APREHSI'),
(1035303343, 'GERARDO VARELA GALLEGO', '2H01030213', 'ANCILLARIES', 'ACCION DEL CAUCA'),
(1035303525, 'JHON JAIRO GOEZ BERRIO', '2H01060201', 'Civil Works', 'VIACOTUR'),
(1035304136, 'LUZ DIONY LOPEZ RUEDA', 'NO APLICA', 'planta WTP', 'ACCION DEL CAUCA'),
(1035304838, 'GUINARA ANDREA TUBERQUIA MANCO', '2H01120201', 'Trainee', 'ZIJIN CONTINENTAL GOLD'),
(1035305293, 'YONATAN ALEXIS SEPULVEDA ESCOBAR', '2H01150201', 'WAREHOUSE', 'ACCION DEL CAUCA'),
(1035305499, 'PAULA ANDREA RESTREPO BERRIO', '2H01030213', 'ANCILLARIES', 'ACCION DEL CAUCA'),
(1035305796, 'SERGIO STIVEN MONTOYA JARAMILLO', '2H01010302', 'INHOUSE MAIN SERVICES', 'FLUICONNECTO SAS'),
(1035305867, 'CLAUDIA MARCELA RESTREPO MARTINEZ', '2H01070203', 'Medical Center', 'APREHSI'),
(1035305873, 'MARIA DANIELA DURANGO MANCO', '2H01030213', 'ANCILLARIES', 'ACCION DEL CAUCA'),
(1035423056, 'ANDERSON IVAN HERNANDEZ GARCIA', '2H01060201', 'HEALTH & SAFETY ACTI', 'ACCION DEL CAUCA'),
(1035434684, 'SANDRA DANIELA ARANGO GOMEZ', '2H01120101', 'HUMAN RESOURCE', 'ACCION DEL CAUCA'),
(1035521449, 'ANDRES FELIPE LLANO ALVAREZ', '2H01010302', 'Ruikuang', 'IMOCOM'),
(1035700545, 'ANDRES FELIPE OSORNO TORRES', '2H01040101', 'TECHNICAL SERVICE CE', 'ACCION DEL CAUCA'),
(1035865049, 'JORGE IVAN VALLE JARAMILLO', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(1036132325, 'SERGIO ANDRES CASTRILLÓN BUITRAGO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1036339610, 'ALFREDO ALEJANDRO RESTREPO JIMENEZ', '2H01030401', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(1036671924, 'SARA RESTREPO VALENCIA', '2H01900201', 'EXPENSE BELONGS TO OTHER SUBSIDIARIES', 'ACCION DEL CAUCA'),
(1037238043, 'OSCAR MARIO BENJUMEA ZULUAGA', '2H01120201', 'Trainee - SENA', 'ZIJIN CONTINENTAL GOLD'),
(1037578639, 'MAURICIO HIGUITA GOMEZ', '2H01900201', 'EXPENSE BELONGS TO OTHER SUBSIDIARIES', 'ACCION DEL CAUCA'),
(1037946418, 'ALVARO IVAN GIRALDO GIRALDO', '2H01070202', 'HEALTH & SAFETY ACTI', 'ARL BOLIVAR - BOER E'),
(1038111264, 'JORGE ARMANDO VILORIA HERNANDEZ', '2H01060201', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(1038113065, 'FERNEY BETANCUR MENDOZA', 'NO APLICA', 'Electrical Maintenance', 'ACCION DEL CAUCA'),
(1039474713, 'MANUEL JOSE PEREZ RENGINO', '2H01060201', 'Civil Works', 'ZIJIN CONTINENTAL GOLD'),
(1039683359, 'YEFERSON DARIO RINCON CAÑAS', '2H01010302', 'Ruikuang', 'MINTECK'),
(1040041660, 'CESAR ALEJANDRO GALVIS OCAMPO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1040374944, 'EDISON ARLEY POSADA GAVIRIA', '2H01070203', 'Medical Center', 'APREHSI'),
(1040496703, 'YAIR ANTONIO FLOREZ ARIZA', '2H01010302', 'Ruikuang', 'MINTECK'),
(1041176090, 'MARIA ISABEL CAMPO DAVID', '2H01030209', 'MERRILL CROWE & REFINERÍA', 'ACCION DEL CAUCA'),
(1041176262, 'JAVIER ESNEIDER MANCO LUJAN', 'NO APLICA', 'planta WTP', 'ACCION DEL CAUCA'),
(1041177066, 'YSAMAR ANDREA USUGA LUJAN', '2H01030202', 'Grinding', 'ZIJIN CONTINENTAL GOLD'),
(1042416227, 'CESAR AUGUSTO QUIJANO FERRER', '2H01010302', 'Ruikuang', 'MINTECK'),
(1044505639, 'SEBASTIAN RESTREPO LONDOÑO', '2H01060201', 'Civil Works', 'ACCION DEL CAUCA'),
(1044923165, 'HECTOR JOSE POLO BELTRAN', 'NO APLICA', 'Mantenimiento Mecanico', 'ACCION DEL CAUCA'),
(1045517280, 'ANDERSON DARIO ZAPATA LOPEZ', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GO'),
(1046907435, 'JEISHON FERNEY MORA RODRIGUEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1046954373, 'GERMAN ALONSO TUBERQUIA GUISAO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1047380757, 'WILLIAM JOSE CONTRERAS GAVIRIA', '2H01010302', 'INHOUSE MAIN SERVICES', 'FLUICONNECTO SAS'),
(1047492156, 'ADRIANA BENITEZ MORENO', '2H01070203', 'Medical Center', 'APREHSI'),
(1062805119, 'PEDRO ALONSO DELGADO ALVAREZ', '2H01010302', 'SERVICE IN HOUSE', 'IMOCOM'),
(1063281252, 'JOHAN ALFONSO CASTRO MARQUEZ', 'NO APLICA', 'Tecnico electricista', 'ACCION DEL CAUCA'),
(1063307408, 'RAHOMIR DE JESUS SOTO HOYOS', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(1063485438, 'LEONAR JOSE CADENA ROCHA', '2H01010302', 'INHOUSE MAIN SERVICES', 'FLUICONNECTO SAS'),
(1065659351, 'DIANA MARCELA CALDERIN BERMUDEZ', '2H01070203', 'Medical Center', 'APREHSI'),
(1065847610, 'JAVIER MARTINEZ ANGARITA', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1067951333, 'TATIANA SOFIA LAMBRAÑO DE LA OSSA', '2H01070202', 'Health & Safety Activity', 'ADECCO'),
(1068665370, 'MIGUEL JOSE HERNANDEZ PANTOJA', '4H01010101', 'Admón. Exploración', 'ATEMPI SEGURIDAD'),
(1069475982, 'SALIN GREGORIO ORTEGA SANTANA', '2H01010302', 'INHOUSE MAIN SERVICES', 'FLUICONNECTO SAS'),
(1072527620, 'RICARDO LOPEZ HOYOS', '2H01010209', 'AUXILIARY SERVICES', 'ACCION DEL CAUCA'),
(1073231600, 'CARLOS MAURICIO MENDIVELSO PEDRAZA', '2H01150201', 'Warehouse', 'ACCION DEL CAUCA'),
(1075542111, 'FREDY POLANIA ARDILA', '2H01070203', 'Medical Center', 'APREHSI'),
(1077444554, 'KETTY PALACIO RONAÑA', '2H01070203', 'Medical Center', 'APREHSI'),
(1078858513, 'JHON DARLINSON GOMEZ IBARGUEN', '2H01010302', 'INHOUSE MAINT SERVICES', 'KALTIRE'),
(1079032397, 'YIBER MANUEL BEJARANO PRIETO', '2H01090503', 'IT', 'ZOFT SECURITY'),
(1080012634, 'JORGE CADRAZCO AYALA', '2H01060201', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(1082869899, 'JAMES DE JESUS HUGUITA ESTRADA', 'NO APLICA', 'NO APLICA', 'ACCION DEL CAUCA'),
(1094859201, 'JEISON PEREZ HERRERA', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1094859433, 'NEIDER CAMILO PEREZ GUERRERO', '2H01080401', 'Mining Control', 'ACCION DEL CAUCA'),
(1094932636, 'MONICA JOHANNA CANO CAGUA', '2H01070202', 'Health & Safety Activity', 'ADECCO'),
(1096183925, 'ESTIVEN ANDRES BERNAL GARRO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1097398551, 'CESAR AUGUSTO MEDINA BERRIO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1097728377, 'KATHERINE TOBON ALCARAZ ', '2H01070203', 'Administrativo', 'HIGABRA CATERING'),
(1098763335, 'JUAN JACOBO PENAGOS FIGUEROHA', '2H01090401', 'Transportation', 'INTERMANTING'),
(1105616431, 'HECTOR JOSE ROJAS GARCIA', '2H01030209', 'MERRILL CROWE & REFINERÍA', 'ACCION DEL CAUCA'),
(1110465823, 'HECTOR CAMILO AVILA RAMIREZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1110501283, 'EDWARD CORTEZ PEÑA', 'NO APLICA', 'NO APLICA', 'ZIJIN CONTINENTAL GOLD'),
(1110516578, 'JUAN CAMILO MARTINEZ SANCHEZ', '2H01070203', 'Medical Center', 'APREHSI'),
(1113304108, 'WESLEY CASTAÑEDA GOMEZ', '2H01010201', 'Comunicaciones', 'BECALL'),
(1116922862, 'SEBASTIAN MORENO BOTERO', '2H01090401', 'Transportation', 'INTERMANTING'),
(1118816513, 'JOHAN OROZCO RIVERA', '2H01060201', 'CIVIL WORKS', 'ACCION DEL CAUCA'),
(1119948428, 'PEDRO ELOY MENDEZ CUBILLOS', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1122406113, 'ARNALDO ALCIDES OÑATE MELENDEZ', '2H01080401', 'MINING CONTROL', 'ACCION DEL CAUCA'),
(1128379130, 'LUIS ENRIQUE PEREZ NAVARRO', '2H01070202', 'HEALTH & SAFETY ACTIVITY', 'ARL BOLIVAR - BOER ELITE'),
(1128459799, 'ANYELA LORENA MARTINEZ CARMONA', '2H01070203', 'Medical Center', 'APREHSI'),
(1128478501, 'CAROLINA ZAPATA ZAPATA', '2H01070203', 'Medical Center', 'APREHSI'),
(1129521276, 'JOSE PORTILLA RAIGOZA', 'NO APLICA', 'Auxiliary Services', 'ACCION DEL CAUCA'),
(1140837196, 'WILLIAM POLO JIMENEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1143393284, 'JUAN GILBERTO MORENO TORO', '2H01070203', 'Medical Center', 'APREHSI'),
(1143443203, 'LAURA NUÑEZ RODRIGUEZ', '2H01070203', 'Medical Center', 'APREHSI'),
(1152194270, 'JHONSON MAURICIO PALACIO CORREA', '2H01090401', 'Transportation', 'INTERMANTING'),
(1152207468, 'DANIEL SANCHEZ MOSQUERA', '2H01090504', 'Support &Information', 'ARUS'),
(1152454425, 'WANNER STHIWAR MINOTTA CUESTA', '2H01090504', 'SUPPORT & INFORMATION SYSTEM - SOPORTE & SISTEMA D', 'ARUS'),
(1152695783, 'DANIEL MAURICIO LOAIZA HERRERA', '2H01010302', 'Ruikuang', 'MINTECK'),
(1152697276, 'MAURICIO VALENCIA GIRALDO', '2H01010302', 'Ruikuang', 'MINTECK'),
(1152700616, 'ALEXANDER VERGARA AGUIRRE', '2H01070203', 'Medical Center', 'APREHSI'),
(1152706634, 'SEBASTIAN PINEDA PINEDA', '2H01150201', 'WAREHOUSE', 'ACCION DEL CAUCA'),
(1192765795, 'CRISTIAN JARAMILLO GOEZ', '2H01010302', 'Ruikuang', 'MINTECK'),
(1193129029, 'MARLON ANDRES MESA GUERRERO', '2H01010304', 'Electrical Maintenance', 'ZIJIN CONTINENTAL GOLD'),
(1193151361, 'JUAN FERNANDO DAVID LOPERA', '2H01090504', 'Support &Information System', 'ARUS'),
(1193156156, 'CRISTIAN ALONSO GUERRA RODRIGUEZ', 'NO APLICA', 'Wtp', 'ACCION DEL CAUCA'),
(1235247708, 'NORELIA NATALY NAVARRO GONZALES', '2H01010302', 'Ruikuang', 'MINTECK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_has_reservación`
--

CREATE TABLE `usuario_has_reservación` (
  `Usuario_idUsuario` int(11) NOT NULL,
  `Reservación_idpedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idAdministrador`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`Codigocategoria`);

--
-- Indices de la tabla `menú`
--
ALTER TABLE `menú`
  ADD PRIMARY KEY (`IdMenú`,`Reservación_idPedido`),
  ADD KEY `fk_Menú_Reservación1_idx` (`Reservación_idPedido`),
  ADD KEY `fk_Menú_Categorias1_idx` (`Categorias_Codigocategoria`);

--
-- Indices de la tabla `papeleriadereciclaje`
--
ALTER TABLE `papeleriadereciclaje`
  ADD PRIMARY KEY (`idPedido`);

--
-- Indices de la tabla `plan_comidas`
--
ALTER TABLE `plan_comidas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  ADD PRIMARY KEY (`idpedido`);

--
-- Indices de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  ADD PRIMARY KEY (`Nit`),
  ADD KEY `fk_Restaurante_Administrador_idx` (`idAdministrador`),
  ADD KEY `fk_Restaurante_Usuario1_idx` (`Usuario_idUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `usuario_has_reservación`
--
ALTER TABLE `usuario_has_reservación`
  ADD PRIMARY KEY (`Usuario_idUsuario`),
  ADD KEY `fk_Usuario_has_Reservación_Reservación1` (`Reservación_idpedido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `papeleriadereciclaje`
--
ALTER TABLE `papeleriadereciclaje`
  MODIFY `idPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `plan_comidas`
--
ALTER TABLE `plan_comidas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  MODIFY `idpedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `menú`
--
ALTER TABLE `menú`
  ADD CONSTRAINT `fk_Menú_Categorias1` FOREIGN KEY (`Categorias_Codigocategoria`) REFERENCES `categorias` (`Codigocategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Menú_Reservación1` FOREIGN KEY (`Reservación_idPedido`) REFERENCES `reservacion` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `restaurante`
--
ALTER TABLE `restaurante`
  ADD CONSTRAINT `fk_Restaurante_Administrador` FOREIGN KEY (`idAdministrador`) REFERENCES `administrador` (`idAdministrador`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Restaurante_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario_has_reservación`
--
ALTER TABLE `usuario_has_reservación`
  ADD CONSTRAINT `fk_Usuario_has_Reservación_Reservación1` FOREIGN KEY (`Reservación_idpedido`) REFERENCES `reservacion` (`idpedido`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_Usuario_has_Reservación_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
