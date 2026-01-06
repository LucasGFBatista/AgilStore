CREATE TABLE tb_categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

INSERT INTO tb_categoria (nome) VALUES
('Smartphones'),
('Laptops'),
('Acessórios'),
('Fones de ouvido'),
('Carregadores'),
('Cabo USB');
