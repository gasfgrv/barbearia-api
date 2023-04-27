
ALTER TABLE
  "public"."agendamento_servico"
ADD
  CONSTRAINT "agendamentos_fkey" FOREIGN KEY ("agendamento") REFERENCES "public"."agendamento" ("id")