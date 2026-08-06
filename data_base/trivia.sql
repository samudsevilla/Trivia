-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 06-08-2026 a las 15:57:43
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
-- Base de datos: `trivia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(11) NOT NULL,
  `pregunta` text NOT NULL,
  `opcion1` varchar(255) NOT NULL,
  `opcion2` varchar(255) NOT NULL,
  `opcion3` varchar(255) NOT NULL,
  `opcion_correcta` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`id`, `pregunta`, `opcion1`, `opcion2`, `opcion3`, `opcion_correcta`) VALUES
(1, '¿Cuál de los siguientes lenguajes se ejecuta principalmente en el lado del cliente (navegador web)?', 'Java', 'Python', 'PHP', 'JavaScript'),
(2, '¿Qué estructura de datos sigue el principio LIFO (Last In, First Out)?', 'Cola (Queue)', 'Lista enlazada', 'Árbol binario', 'Pila (Stack)'),
(3, '¿Qué significa SQL en el contexto de bases de datos?', 'Structured Query Language', 'Simple Query Level', 'System Quality Link', 'Structured Quick Language'),
(4, '¿Cuál es el puerto predeterminado utilizado por el protocolo HTTP?', '443', '21', '3306', '80'),
(5, '¿Qué protocolo se utiliza de forma segura para la transferencia de hipertexto en la web?', 'FTP', 'SMTP', 'SSH', 'HTTPS'),
(6, '¿Cuál de los siguientes NO es un principio fundamental de la Programación Orientada a Objetos?', 'Herencia', 'Polimorfismo', 'Compilación', 'Encapsulamiento'),
(7, '¿Qué componente de una computadora se encarga de ejecutar las instrucciones de los programas?', 'Memoria RAM', 'Disco Duro', 'Tarjeta Gráfica', 'CPU (Unidad Central de Procesamiento)'),
(8, '¿Cómo se llama el proceso de encontrar y corregir errores en el código fuente de un software?', 'Compilación', 'Depuración (Debugging)', 'Serialización', 'Despliegue'),
(9, '¿Qué comando de Git se utiliza para descargar un repositorio existente desde un servidor remoto?', 'git push', 'git commit', 'git status', 'git clone'),
(10, '¿Qué tipo de base de datos organiza la información en tablas formadas por filas y columnas?', 'Base de datos NoSQL', 'Base de datos orientada a objetos', 'Base de datos en grafo', 'Base de datos relacional');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `id` int(11) NOT NULL,
  `id_usuario` varchar(20) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `respuesta_dada` varchar(255) NOT NULL,
  `correcta` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `respuestas`
--

INSERT INTO `respuestas` (`id`, `id_usuario`, `id_pregunta`, `respuesta_dada`, `correcta`) VALUES
(1, '13594591', 3, 'Structured Query Language', 0),
(2, '13594591', 6, 'Compilación', 0),
(3, '13594591', 9, 'git push', 0),
(4, '13594591', 8, 'Compilación', 0),
(5, '13594591', 1, 'Python', 0),
(6, '13594591', 2, 'Pila (Stack)', 1),
(7, '13594591', 4, '21', 0),
(8, '13594591', 10, 'Base de datos relacional', 1),
(9, '13594591', 7, 'Tarjeta Gráfica', 0),
(10, '13594591', 5, 'SMTP', 0),
(11, '13594591', 6, 'Compilación', 0),
(12, '13594591', 4, '3306', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `carrera` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `semestre` int(11) NOT NULL,
  `puntaje` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cedula`, `nombre`, `usuario`, `password`, `correo`, `carrera`, `telefono`, `semestre`, `puntaje`) VALUES
('13594591', 'ola que ha ce', 'pollito08', '123456', 'wiki@gmail.com', 'arquitectura', '04127358975', 5, 20);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario` (`id_usuario`),
  ADD KEY `fk_pregunta` (`id_pregunta`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cedula`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `fk_pregunta` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`cedula`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
