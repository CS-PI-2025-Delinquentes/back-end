ALTER TABLE "pedido" ADD COLUMN "data_solicitacao" DATE;

update "pedido"
set "data_solicitacao" = '2025-07-16'
where "id" = 1;

INSERT INTO "endereco" ("cep", "rua", "bairro", "numero", "cidade_id")
VALUES
    ('86708330', 'Rua Teque-teque', 'Vila São João', '144', 3),
    ('86040170', 'Ponte Vereador Amélio Viecelli', 'Eldorado', '1085', 7),
    ('86810150', 'Rua Fileto Alves', 'Jardim Esperança', '22', 2),
    ('87083855', 'Rua 48.012', 'Jardim Pilar', '1237', 11),
    ('86010645', 'Praça do Garapeiro', 'Vila Ipiranga', '75', 7),
    ('86605254', 'Rua das Flores', 'Jardim Caviúna', '2005', 15),
    ('87701310', 'Avenida Distrito Federal', 'Centro', '123', 13),
    ('87600970', 'Avenida Presidente Castelo Branco', 'Centro', '12', 12),
    ('87053970', 'Rua Pioneiro Domingos Errerias Ernandes', 'Jardim Fregadolli', '173-A', 11);

INSERT INTO "pedido" ("status", "pessoa_id", "endereco_origem_id", "endereco_destino_id", "data_solicitacao")
VALUES
    ('PENDENTE', 3, 7, 3, '2025-07-17'),
    ('RECUSADO', 4, 10, 4, '2025-08-01'),
    ('ACEITO', 3, 8, 9, '2025-08-03'),
    ('ACEITO', 5, 10, 7, '2025-08-14'),
    ('PENDENTE', 5, 1, 4, '2025-08-21'),
    ('RECUSADO', 5, 1, 1, '2025-08-31'),
    ('PENDENTE', 4, 9, 8, '2025-09-02'),
    ('RECUSADO', 2, 5, 6, '2025-09-05'),
    ('RECUSADO', 3, 11, 2, '2025-09-14');

INSERT INTO "pacote" ("largura", "altura", "comprimento", "peso", "quantidade", "tipo", "pedido_id")
VALUES
    (17.5, 14.2, 1.0, 5, 1, 'CAIXA', 1),
    (1.5, 10.4, 5.0, 1.1, 3, 'SACOLA', 1),
    (0.0, 9.4, 0.0, 0, 1, 'ENVELOPE', 1),
    (2.0, 7.8, 3.3, 0, 1, 'CAIXA', 2),
    (15.0, 3.7, 4.2, 0, 5, 'SACOLA', 3),
    (3.1, 1.1, 15.8, 2, 8, 'ENVELOPE', 4),
    (5.3, 0.0, 0.0, 1, 13, 'CAIXA', 5),
    (9.6, 0.0, 0.0, 14, 5, 'SACOLA', 6),
    (3.4, 2.0, 4.0, 19, 19, 'ENVELOPE', 7),
    (7.8, 4.2, 2.9, 9, 2, 'CAIXA', 8),
    (8.2, 5.0, 1.1, 3, 25, 'SACOLA', 9),
    (1.0, 2.0, 5.0, 2, 39, 'ENVELOPE', 10);
