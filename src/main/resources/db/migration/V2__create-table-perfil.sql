create table
  "public"."perfil" (
    "id" serial primary key,
    "nome" VARCHAR(255) not null default NOW()
  )