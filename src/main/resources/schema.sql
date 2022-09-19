CREATE TABLE marcas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE veiculos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ano INTEGER,
    created DATETIME,
    descricao VARCHAR(255),
    updated DATETIME,
    veiculo VARCHAR(255),
    vendido BIT,
    marca_id BIGINT,
    PRIMARY KEY (id)
) engine=InnoDB;

ALTER TABLE veiculos
    add constraint FKqrkgge4x5j8cvyobi3rsv4f28
    foreign key (marca_id)
    references marcas (id);