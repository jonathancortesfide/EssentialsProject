-- Author: Elias Viquez

-- DROP DATABASE Essentials;
drop schema if exists Essentials;
drop user if exists usuario_prueba;

-- Crear la base de datos
CREATE SCHEMA Essentials;

-- Crear el usuario y otorgarle permisos
CREATE USER 'usuario_prueba'@'%' IDENTIFIED BY 'Usuar1o_Clave.';
GRANT ALL PRIVILEGES ON Essentials.* TO 'usuario_prueba'@'%';
FLUSH PRIVILEGES;

-- Seleccionar la base de datos
USE Essentials;


-- Crear tabla Categoria
CREATE TABLE Categories (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Category VARCHAR(150) NOT NULL,
    Description VARCHAR(255)
);


 /*Se crea la tabla de clientes llamada cliente... igual que la clase Cliente */
CREATE TABLE User (
  userId INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  name VARCHAR(20) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  email VARCHAR(75) NULL,
  phoneNumber VARCHAR(15) NULL,
  image varchar(1024),
  active boolean,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


create table role (
  roleId INT NOT NULL AUTO_INCREMENT,
  name varchar(20),
  userId int,
  PRIMARY KEY (roleId),
  foreign key fk_rol_usuario (userId) references user(UserId)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE table Product (
  Id INT NOT NULL AUTO_INCREMENT,
  CategoryId INT,
  Name VARCHAR(30) NOT NULL,  
  Description VARCHAR(1600) NOT NULL, 
  Price DECIMAL(10, 2) NOT NULL,
  Quantity int,  
  Image varchar(1024),
  Active bool,
  OnSale BIT,
  PRIMARY KEY (Id),
  foreign key fk_product_category (CategoryId) references Categories(Id)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE Customers (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    LastName VARCHAR(100),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(100),
    Address VARCHAR(255)
);

CREATE TABLE Invoice (
    Id INT AUTO_INCREMENT PRIMARY KEY,
	CustomerId INT,
    Date DATE NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    Discount  DECIMAL(10, 2) NOT NULL,
    Detail NVARCHAR(200),
    FOREIGN KEY (CustomerId) REFERENCES Customers(Id) ON DELETE SET NULL
);


-- INSERT Categories DATA
INSERT INTO Categories (Category, Description) VALUES ('Sudaderas', 'Ropa de manga larga, cómoda y moderna');
INSERT INTO Categories (Category, Description) VALUES ('Camisas', 'Camisas de diferentes estilos y colores');
INSERT INTO Categories (Category, Description) VALUES ('Lentes', 'Lentes de sol de última moda');
INSERT INTO Categories (Category, Description) VALUES ('Pantalonetas', 'Pantalonetas deportivas y casuales');
INSERT INTO Categories (Category, Description) VALUES ('Gorras', 'Gorras con estilo para toda ocasión');

-- INSERT Customers DATA
INSERT INTO Customers (Name, LastName, PhoneNumber, Email, Address) VALUES ('Pedro', 'López', '5551234567', 'carlos.lopez@example.com', 'Calle Falsa 123');
INSERT INTO Customers (Name, LastName, PhoneNumber, Email, Address) VALUES ('Juan', 'Martínez', '5559876543', 'ana.martinez@example.com', 'Avenida Siempre Viva 742');
INSERT INTO Customers (Name, LastName, PhoneNumber, Email, Address) VALUES ('Jose', 'Pérez', '5554567890', 'luis.perez@example.com', 'Calle 10 de Mayo 456');
INSERT INTO Customers (Name, LastName, PhoneNumber, Email, Address) VALUES ('Maria', 'Gómez', '5557654321', 'maria.gomez@example.com', 'Calle de la Amistad 12');
INSERT INTO Customers (Name, LastName, PhoneNumber, Email, Address) VALUES ('Jorge', 'Hernández', '5552345678', 'jorge.hernandez@example.com', 'Avenida Los Pinos 90');

INSERT INTO Product (CategoryId, Name, Description, Price, Quantity, Image, Active, OnSale)
VALUES
(1, 'Sudadera Essentials Gris', 'Sudadera unisex Essentials color gris, hecha de algodón premium.', 79.99, 50, 'https://example.com/images/sudadera_essentials_gris.jpg', TRUE, 1),
(2, 'Pantalones Essentials Beige', 'Pantalones Essentials color beige con ajuste relajado.', 69.99, 40, 'https://example.com/images/pantalones_essentials_beige.jpg', TRUE, 0),
(3, 'Camiseta Essentials Negra', 'Camiseta unisex Essentials color negro con logotipo minimalista.', 49.99, 100, 'https://example.com/images/camiseta_essentials_negra.jpg', TRUE, 1),
(4, 'Chaqueta Essentials Verde Oli', 'Chaqueta Essentials resistente al viento, ideal para el invierno.', 119.99, 25, 'https://example.com/images/chaqueta_essentials_verde_oliva.jpg', TRUE, 0),
(1, 'Shorts Essentials Gris Claro', 'Shorts Essentials para uso casual o deportivo.', 39.99, 60, 'https://example.com/images/shorts_essentials_gris_claro.jpg', TRUE, 1),
(2, 'Gorra Essentials Negra', 'Gorra Essentials ajustable color negro, estilo urbano.', 29.99, 80, 'https://example.com/images/gorra_essentials_negra.jpg', TRUE, 1),
(3, 'Mochila Essentials Beige', 'Mochila Essentials color beige, perfecta para viajes cortos.', 89.99, 30, 'https://example.com/images/mochila_essentials_beige.jpg', TRUE, 0),
(4, 'Calcetines Essentials Pack 3', 'Pack de 3 calcetines Essentials, mezcla de algodón y elastano.', 19.99, 150, 'https://example.com/images/calcetines_essentials_pack3.jpg', TRUE, 1),
(1, 'Sudadera Essentials Azul', 'Sudadera unisex Essentials color azul con capucha ajustable.', 79.99, 35, 'https://example.com/images/sudadera_essentials_azul.jpg', TRUE, 0),
(2, 'Joggers Essentials Grises', 'Joggers Essentials para entrenamiento o uso casual.', 59.99, 45, 'https://example.com/images/joggers_essentials_grises.jpg', TRUE, 1);

INSERT INTO User (username, password, name, lastName, email, phoneNumber, image, active)
VALUES
('Pedro', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'John', 'Doe', 'jdoe@example.com', '1234567890', 'https://example.com/images/jdoe.jpg', TRUE),
('Juan', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'Juan', 'Smith', 'asmith@example.com', '9876543210', 'https://example.com/images/asmith.jpg', TRUE),
('Jose', '$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO', 'Jose', 'Jones', 'mjones@example.com', '4561237890', 'https://example.com/images/mjones.jpg', TRUE);

insert into role (roleId, name, userId) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2), (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);

-- INSERT Invoices DATA
INSERT INTO Invoice (CustomerId, Date, Total, Discount, Detail) VALUES (1, '2023-11-01', 91.98, 5.00, '1 Sudadera talla M $100');
INSERT INTO Invoice (CustomerId, Date, Total, Discount, Detail) VALUES (2, '2023-11-02', 59.99, 0.00, '2 par Tenis talla 43 $300');
INSERT INTO Invoice (CustomerId, Date, Total, Discount, Detail) VALUES (3, '2023-11-03', 94.97, 8.00, '1 Gorra $20');
INSERT INTO Invoice (CustomerId, Date, Total, Discount, Detail) VALUES (3, '2023-11-04', 24.99, 3.00, '2 Camiseta talla L roja Mod Basic $50, lentes de sol $60');
INSERT INTO Invoice (CustomerId, Date, Total, Discount, Detail) VALUES (3, '2023-11-05', 19.99, 0.00, '1 Pantaloneta gris short talla S $30');


-- SELECTS
SELECT * FROM categories;

SELECT * FROM customers;

SELECT * FROM Invoice;

SELECT * FROM Role;

SELECT * FROM Product;

-- Sin mas inserts curiosos para evitar perder el curso.
 
 


