create table "public"."servico" (
  "id" bigint not null,
  "nome" varchar(255) not null,
  "descricao" varchar(255) not null,
  "preco" money not null,
  "duracacao" integer not null,
  "ativo" boolean not null,
  constraint "servico_pkey" primary key ("id")
)