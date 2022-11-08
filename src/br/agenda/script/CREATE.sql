DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;

USE agenda;

CREATE TABLE Usuario (
	cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100),
    senha VARCHAR(10)
);

CREATE TABLE Contato (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    telefone VARCHAR(11),
    cpf VARCHAR(11),
    FOREIGN KEY (cpf) REFERENCES Usuario (cpf)
);