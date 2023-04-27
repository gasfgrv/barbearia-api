alter table
    "public"."usuario"
add
    constraint "usuario_fkey" foreign key ("perfil") references "public"."perfil" ("id");