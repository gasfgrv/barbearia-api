create table "public"."barbeiro" (
  "id" uuid not null,
  "nome" varchar(255) not null,
  "login" varchar(255) not null,
  "telefone" varchar(255) not null,
  constraint "barbeiro_pkey" primary key ("id")
);