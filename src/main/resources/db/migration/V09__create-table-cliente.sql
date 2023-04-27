create table "public"."cliente" (
  "id" uuid not null,
  "nome" varchar(255) not null,
  "cpf" varchar(255) not null,
  "login" varchar(255) not null,
  "telefone" varchar(255) not null,
  "sexo" varchar(255) not null,
  "data_nascimento" date not null,
  constraint "cliente_pkey" primary key ("id")
);