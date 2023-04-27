CREATE TABLE "public"."servico" (
  "id" BIGINT NOT NULL,
  "nome" VARCHAR(255) NOT NULL,
  "descricao" varchar(255) NOT NULL,
  "preco" MONEY NOT NULL,
  "duracacao" INTEGER NOT NULL,
  "ativo" BOOLEAN NOT NULL,
  CONSTRAINT "servico_pkey" PRIMARY KEY ("id")
)