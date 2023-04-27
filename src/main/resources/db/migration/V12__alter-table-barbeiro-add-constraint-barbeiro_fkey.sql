alter table
  "public"."barbeiro"
add
  constraint "barbeiro_fkey" foreign key ("login") references "public"."usuario" ("login");