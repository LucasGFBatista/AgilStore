CREATE TABLE tb_produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    qnt_estoque INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES tb_categoria(id)
);
