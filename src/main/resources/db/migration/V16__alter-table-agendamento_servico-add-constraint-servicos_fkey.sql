alter table
  "public"."agendamento_servico"
add
  constraint "servicos_fkey" foreign key ("servico") references "public"."servico" ("id")