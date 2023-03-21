create table
  "public"."usuario" (
    "login" VARCHAR(255) not null,
    "senha" VARCHAR(255) not null,
    constraint "usuario_pkey" primary key ("login")
  )