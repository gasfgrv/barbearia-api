alter table
  "public"."agendamento_servico"
add
  constraint "agendamentos_fkey" foreign key ("agendamento") references "public"."agendamento" ("id")