ALTER TABLE "pessoa" ALTER COLUMN "senha" TYPE VARCHAR(255);

UPDATE "pessoa"
SET "senha" = '$2a$10$I/HIRVegFeKLpcLWYui2pu9vPZ121uvRelvWNaVtFaI3qOZlK71lu' --12345679
WHERE "id" = 1;

UPDATE "pessoa"
SET "senha" = '$2a$10$Oin.62qfWhXdka6Qjs17hOcH8OpM3EPYR5ZuAQSY/BPQn8oIgOKZ2' --12345678
WHERE "id" = 2;

UPDATE "pessoa"
SET "senha" = '$2a$10$ZcPL1C2xP5ISvGaX4ck1oeo1al.4qL1/Iv34fjIRXVaDevprfFM52' --495612912
WHERE "id" = 3;

UPDATE "pessoa"
SET "senha" = '$2a$10$cNHJNgLtDSTOksK0Rzfk5ebUczYrPtaYhj8GC2azcMIGu2QrubSre' --12345678
WHERE "id" = 4;

INSERT INTO "pessoa" ("email", "telefone", "senha", "role")
VALUES ('delinquentes.team@gmail.com', '4499213458', '$2a$10$QwQbKnpS5Ht8Mha4W9AHsO/o9qh/66Xu.iRfLIRkC5ED/OYD/QcQS', 'ROLE_CLIENT'); --pagildev

INSERT INTO "pessoa_juridica" ("id", "nome_fantasia", "cnpj")
VALUES (5, 'Delinquentes', '45202517000100');