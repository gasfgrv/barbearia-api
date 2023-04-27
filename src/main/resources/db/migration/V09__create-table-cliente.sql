CREATE TABLE "public"."cliente" (
  "id" BIGINT NOT NULL,
  "nome" VARCHAR(255) NOT NULL,
  "cpf" varchar(255) NOT NULL,
  "login" VARCHAR(255) NOT NULL,
  "telefone" VARCHAR(255) NOT NULL,
  "sexo" VARCHAR(255) NOT NULL,
  "data_nascimento" DATE NOT NULL,
  CONSTRAINT "cliente_pkey" PRIMARY KEY ("id")
);