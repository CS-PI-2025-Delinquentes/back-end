ALTER TABLE "avaliacao" ADD COLUMN "data_avaliacao" DATE;

update "avaliacao"
set "data_avaliacao" = '2025-09-11'
where "id" = 1;