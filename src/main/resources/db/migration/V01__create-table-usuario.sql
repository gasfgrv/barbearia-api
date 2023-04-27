create table "public"."usuario" (
  "login" varchar(255) not null,
  "senha" varchar(255) not null,
  constraint "usuario_pkey" primary key ("login")
)