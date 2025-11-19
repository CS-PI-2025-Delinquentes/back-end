ALTER TABLE "pedido" ADD COLUMN "observacao" TEXT;

UPDATE "pedido"
set "observacao" = 'Sla, teste descricao'