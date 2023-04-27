alter table
  "public"."cliente"
add
  constraint "cliente_fkey" foreign key ("login") references "public"."usuario" ("login");