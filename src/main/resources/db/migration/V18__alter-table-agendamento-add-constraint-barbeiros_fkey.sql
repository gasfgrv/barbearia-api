alter table
  "public"."agendamento"
add
  constraint "barbeiros_fkey" foreign key ("barbeiro") references "public"."barbeiro" ("id")