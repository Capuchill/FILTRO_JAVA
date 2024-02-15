/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;

DROP DATABASE prueba;
CREATE DATABASE prueba;

use  prueba;

CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `documento` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cliente_documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `factura` (
  `numeroFactura` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `cliente_id` int DEFAULT NULL,
  `total_pagar_iva` float DEFAULT NULL,
  PRIMARY KEY (`numeroFactura`),
  KEY `idx_factura_cliente` (`cliente_id`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `impuesto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `año` year DEFAULT NULL,
  `porcentaje` double(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `producto` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text,
  `precioVenta` decimal(10,2) NOT NULL,
  `precioCompra` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `idx_producto_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `item_factura` (
  `id` int NOT NULL AUTO_INCREMENT,
  `factura_numeroFactura` int DEFAULT NULL,
  `producto_codigo` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_item_factura_factura` (`factura_numeroFactura`),
  KEY `idx_item_factura_producto` (`producto_codigo`),
  CONSTRAINT `item_factura_ibfk_1` FOREIGN KEY (`factura_numeroFactura`) REFERENCES `factura` (`numeroFactura`),
  CONSTRAINT `item_factura_ibfk_2` FOREIGN KEY (`producto_codigo`) REFERENCES `producto` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO cliente(id,nombre,apellido,email,direccion,celular,documento) VALUES('1','\'Juan\'','\'Perez\'','\'jperez@gmail.com\'','\'Calle 123\'','\'31448975\'','\'1001\''),('2','\'Maria\'','\'Lopez\'','\'mlopez@gmail.com\'','\'Avenida 456\'','\'31567890\'','\'1002\''),('3','\'Carlos\'','\'Gomez\'','\'cgomez@gmail.com\'','\'Carrera 789\'','\'31789012\'','\'1003\''),('4','\'Laura\'','\'Martinez\'','\'lmartinez@gmail.com\'','\'Calle 567\'','\'31890123\'','\'1004\''),('5','\'Pedro\'','\'Rodriguez\'','\'prodriguez@gmail.com\'','\'Calle 890\'','\'31901234\'','\'1005\'');

INSERT INTO factura(numeroFactura,fecha,cliente_id,total_pagar_iva) VALUES('1','2024-01-15 10:00:00','1','100'),('2','2024-01-16 11:30:00','2','200'),('3','2024-01-17 14:45:00','3','50'),('4','2024-01-18 09:15:00','4','80'),('5','2024-01-19 16:20:00','5','1000'),('6','2024-01-23 13:10:00','1','500'),('7','2024-01-25 15:30:00','2','15'),('8','2024-01-27 18:00:00','3','233'),('9','2024-01-29 10:45:00','4','399'),('10','2024-01-31 12:25:00','5','1200');

INSERT INTO impuesto(id,año,porcentaje) VALUES('1','2024','25.00');

INSERT INTO producto(codigo,nombre,descripcion,precioVenta,precioCompra) VALUES('1','\'Producto1\'','X\'446573637269706369c3b36e2064656c2050726f647563746f2031\'','50.00','30.00'),('2','\'Producto2\'','X\'446573637269706369c3b36e2064656c2050726f647563746f2032\'','40.00','25.00'),('3','\'Producto3\'','X\'446573637269706369c3b36e2064656c2050726f647563746f2033\'','60.00','35.00'),('4','\'Producto4\'','X\'446573637269706369c3b36e2064656c2050726f647563746f2034\'','70.00','45.00'),('5','\'Producto5\'','X\'446573637269706369c3b36e2064656c2050726f647563746f2035\'','55.00','30.00'),('6','\'Producto18\'','X\'446573637269706369c3b36e2064656c2050726f647563746f203138\'','65.00','40.00'),('7','\'Producto19\'','X\'446573637269706369c3b36e2064656c2050726f647563746f203139\'','80.00','50.00'),('8','\'Producto20\'','X\'446573637269706369c3b36e2064656c2050726f647563746f203230\'','75.00','48.00');
INSERT INTO item_factura(id,factura_numeroFactura,producto_codigo,cantidad,importe) VALUES('1','1','1','2','100.00'),('2','1','2','3','120.00'),('3','2','3','1','60.00'),('4','2','4','2','130.00'),('5','3','5','1','55.00'),('6','8','6','2','120.00'),('7','9','7','3','195.00'),('8','10','8','1','75.00');