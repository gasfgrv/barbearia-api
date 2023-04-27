CREATE TABLE "public"."barbeiro" (
  "id" BIGINT NOT NULL,
  "nome" VARCHAR(255) NOT NULL,
  "login" VARCHAR(255) NOT NULL,
  "telefone" VARCHAR(255) NOT NULL,
  CONSTRAINT "barbeiro_pkey" PRIMARY KEY ("id")
);