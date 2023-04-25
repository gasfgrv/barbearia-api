create table
  "public"."servico" (
    "id" BIGINT not null,
    "nome" VARCHAR(255) not null,
    "descricao" varchar(255) not null,
    "preco" MONEY not null,
    "duracacao" INTEGER not null,
    "ativo" BOOLEAN not null,
    constraint "servico_pkey" primary key ("id")
  )