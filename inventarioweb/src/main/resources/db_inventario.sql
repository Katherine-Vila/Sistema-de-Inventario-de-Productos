CREATE DATABASE IF NOT EXISTS db_inventario
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_spanish_ci;

USE db_inventario;

CREATE TABLE IF NOT EXISTS CATEGORIA (
    id_categoria   INT          NOT NULL AUTO_INCREMENT,
    nombre         VARCHAR(80)  NOT NULL,
    descripcion    VARCHAR(255) NULL,
    estado         TINYINT(1)   NOT NULL DEFAULT 1,
    fecha_creacion DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (id_categoria),
    CONSTRAINT UQ_CATEGORIA_NOMBRE UNIQUE (nombre)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS PRODUCTO (
    id_producto    INT            NOT NULL AUTO_INCREMENT,
    id_categoria   INT            NOT NULL,
    codigo         VARCHAR(20)    NOT NULL,
    nombre         VARCHAR(150)   NOT NULL,
    descripcion    VARCHAR(500)   NULL,
    precio         DECIMAL(10,2)  NOT NULL,
    stock          INT            NOT NULL DEFAULT 0,
    estado         TINYINT(1)     NOT NULL DEFAULT 1,
    fecha_creacion DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_PRODUCTO PRIMARY KEY (id_producto),
    CONSTRAINT UQ_PRODUCTO_COD UNIQUE (codigo),
    CONSTRAINT FK_PROD_CAT FOREIGN KEY (id_categoria)
        REFERENCES CATEGORIA(id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT CK_PRECIO CHECK (precio >= 0),
    CONSTRAINT CK_STOCK CHECK (stock >= 0)
) ENGINE=InnoDB;

INSERT INTO CATEGORIA (nombre, descripcion) VALUES
('Electronica', 'Dispositivos y componentes electronicos'),
('Papeleria', 'Articulos de oficina y escritura'),
('Herramientas', 'Herramientas manuales y electricas'),
('Alimentos', 'Productos alimenticios no perecederos');

INSERT INTO PRODUCTO (id_categoria, codigo, nombre, descripcion, precio, stock) VALUES
(1, 'ELEC-001', 'Cable HDMI 2m', 'Cable HDMI 4K ultra HD 2 metros', 8.99, 50),
(1, 'ELEC-002', 'Teclado USB', 'Teclado mecanico retroiluminado', 35.00, 20),
(2, 'PAP-001', 'Resma papel A4', 'Resma 500 hojas 75g bond blanco', 4.50, 200),
(2, 'PAP-002', 'Boligrafos x12', 'Caja de 12 boligrafos azules', 2.25, 150),
(3, 'HER-001', 'Destornillador set', 'Juego de 6 destornilladores Phillips', 12.00, 30),
(4, 'ALI-001', 'Cafe molido 250g', 'Cafe molido de origen colombiano', 6.75, 80);
