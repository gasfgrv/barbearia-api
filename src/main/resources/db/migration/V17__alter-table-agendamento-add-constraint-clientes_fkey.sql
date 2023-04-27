alter table
  "public"."agendamento"
add
  constraint "clientes_fkey" foreign key ("cliente") references "public"."cliente" ("id")