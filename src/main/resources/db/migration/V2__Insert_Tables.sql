-- Inserção na tabela "pessoa"
INSERT INTO "pessoa" ("email", "telefone", "senha", "role")
VALUES
    ('20230006488@estudantes.ifpr.edu.br', '4499333287', '123456', 'ROLE_ADMIN'),
    ('teste@gmail.com', '44999999999', '123456', 'ROLE_CLIENT'),
    ('menonifpr@gmail.com', '4497108707', '4956129', 'ROLE_DEV'),
    ('gregory16704@gmail.com', '4497206974', '123456', 'ROLE_DEV');

-- Inserção na tabela "pessoa_fisica"
INSERT INTO "pessoa_fisica" ("id", "nome", "cpf")
VALUES
    (1, 'Arthur', '41346974098'),
    (2, 'Cliente Teste', '12345678909'),
    (3, 'Menoni', '37087625098'),
    (4, 'Gregas', '20810329026');

-- Inserção na tabela "avaliacao"
INSERT INTO "avaliacao" ("nota", "descricao", "pessoa_id")
VALUES
    (5, 'Muito bom, top demais!!!', 1);

-- Inserção na tabela "estado"
INSERT INTO "estado" ("nome", "sigla")
VALUES
    ('Parana', 'PR');

-- Inserção na tabela "cidade"
INSERT INTO "cidade" ("nome", "estado_id")
VALUES
    ('Alto Paraná', 1),
    ('Apucarana', 1),
    ('Arapongas', 1),
    ('Cambé', 1),
    ('Castelo Branco', 1),
    ('Jandaia do Sul', 1),
    ('Londrina', 1),
    ('Mandaguaçu', 1),
    ('Mandaguari', 1),
    ('Marialva', 1),
    ('Maringá', 1),
    ('Nova Esperança', 1),
    ('Paranavaí', 1),
    ('Paraíso do Norte', 1),
    ('Rolandia', 1),
    ('Sarandi', 1),
    ('Tamboara', 1);

-- Inserção na tabela "endereco"
INSERT INTO "endereco" ("cep", "rua", "bairro", "numero", "cidade_id")
VALUES
    ('87706388', 'Rua Dom Elizeu de Moraes Pimentel', 'Jardim Guanabara', '377', 13),
    ('87710010', 'Rua Professor Geraldo Longo', 'Jardim São Jorge', '958', 13);

-- Inserção na tabela "pedido"
INSERT INTO "pedido" ("status", "pessoa_id", "endereco_origem_id", "endereco_destino_id")
VALUES
    ('PENDENTE', 2, 1, 2);

-- Inserção na tabela "pacote"
INSERT INTO "pacote" ("largura", "altura", "comprimento", "peso", "quantidade", "tipo", "pedido_id")
VALUES
    (17.5, 20.0, 15.0, 5, 2, 'CAIXA', 1);
