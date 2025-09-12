ALTER TABLE "avaliacao" ADD COLUMN "data_avaliacao" DATE;

update "avaliacao"
set "data_avaliacao" = '2025-09-11'
where "id" = 1;

INSERT INTO "avaliacao" ("nota", "descricao", "data_avaliacao", "pessoa_id")
VALUES
    (4, 'Serviço excelente e rapido!', '2025-08-12', 2),
    (3, 'Atendeu as expectativas', '2025-09-02', 4),
    (5, 'Muito bom, principalmente a tela de orçamento!', '2025-06-18', 3);
