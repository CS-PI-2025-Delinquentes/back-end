CREATE TABLE IF NOT EXISTS "pessoa" (
    "id" SERIAL PRIMARY KEY,
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "telefone" VARCHAR(11) NOT NULL,
    "senha" VARCHAR(30) NOT NULL,
    "role" VARCHAR(12) NOT NULL DEFAULT 'ROLE_CLIENT'
);

CREATE TABLE IF NOT EXISTS "pessoa_juridica" (
    "id" BIGINT PRIMARY KEY,
    "nome_fantasia" VARCHAR(255) NOT NULL,
    "cnpj" CHAR(14) NOT NULL UNIQUE,
    CONSTRAINT fk_pessoa_juridica_pessoa
        FOREIGN KEY ("id") REFERENCES "pessoa"("id")
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "pessoa_fisica" (
    "id" BIGINT PRIMARY KEY,
    "nome" VARCHAR(100) NOT NULL,
    "cpf" CHAR(11) NOT NULL UNIQUE,
    CONSTRAINT fk_pessoa_fisica_pessoa
    FOREIGN KEY ("id") REFERENCES "pessoa"("id")
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "avaliacao" (
    "id" SERIAL PRIMARY KEY,
    "nota" INT NOT NULL,
    "descricao" TEXT NOT NULL,
    "pessoa_id" BIGINT NOT NULL,
    CONSTRAINT fk_avaliacao_pessoa
    FOREIGN KEY ("pessoa_id") REFERENCES "pessoa"("id")
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "estado" (
    "id" SERIAL PRIMARY KEY,
    "nome" VARCHAR(30) NOT NULL UNIQUE,
    "sigla" CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "cidade" (
    "id" SERIAL PRIMARY KEY,
    "nome" VARCHAR(100) NOT NULL UNIQUE,
    "estado_id" BIGINT NOT NULL,
    CONSTRAINT fk_estado
        FOREIGN KEY ("estado_id") REFERENCES "estado"("id")
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "endereco" (
    "id" SERIAL PRIMARY KEY,
    "cep" CHAR(8) NOT NULL,
    "rua" VARCHAR(255) NOT NULL,
    "bairro" VARCHAR(100) NOT NULL,
    "numero" VARCHAR(10) NOT NULL,
    "cidade_id" BIGINT NOT NULL,
    CONSTRAINT fk_cidade
        FOREIGN KEY ("cidade_id") REFERENCES "cidade"("id")
        ON DELETE CASCADE
);

CREATE TYPE status_pedido AS ENUM ('PENDENTE', 'ACEITO', 'RECUSADO');

CREATE TYPE tipo_pacote AS ENUM ('CAIXA', 'ENVELOPE', 'SACOLA');

CREATE TABLE IF NOT EXISTS "pedido" (
    "id" SERIAL PRIMARY KEY,
    "status" status_pedido NOT NULL DEFAULT 'PENDENTE',
    "pessoa_id" BIGINT NOT NULL,
    "endereco_origem_id" BIGINT NOT NULL,
    "endereco_destino_id" BIGINT NOT NULL,
    CONSTRAINT fk_pedido_pessoa
        FOREIGN KEY ("pessoa_id") REFERENCES "pessoa"("id")
        ON DELETE CASCADE,
    CONSTRAINT fk_endereco_origem
        FOREIGN KEY ("endereco_origem_id") REFERENCES "endereco"("id")
        ON DELETE CASCADE,
    CONSTRAINT fk_endereco_destino
        FOREIGN KEY ("endereco_destino_id") REFERENCES "endereco"("id")
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "pacote" (
    "id" SERIAL PRIMARY KEY,
    "largura" FLOAT NOT NULL,
    "altura" FLOAT NOT NULL,
    "comprimento" FLOAT NOT NULL,
    "peso" FLOAT NOT NULL,
    "quantidade" INT NOT NULL,
    "tipo" tipo_pacote NOT NULL,
    "pedido_id" BIGINT NOT NULL,
    CONSTRAINT fk_pedido
        FOREIGN KEY ("pedido_id") REFERENCES "pedido"("id")
        ON DELETE CASCADE
);
